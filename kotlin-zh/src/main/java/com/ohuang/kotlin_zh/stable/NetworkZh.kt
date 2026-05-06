package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.可变映射
import com.ohuang.kotlin_zh.字符串
import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.映射
import com.ohuang.kotlin_zh.无
import com.ohuang.kotlin_zh.字节数组
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * 网络响应封装类
 */
data class 网络响应(
    val 状态码: 整数,
    val 内容: 字符串,
    val 消息: 字符串 = "",
    val 头信息: 映射<字符串, 列表<字符串>> = 映射构建()
) {
    val 是否成功: 布尔
        get() = 状态码 in 200..299
    
    val 是否失败: 布尔
        get() = !是否成功
}

/**
 * HTTP 请求方法枚举
 */
enum class HTTP方法 {
    GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS
}

/**
 * 发送 HTTP 请求的 DSL 构建器
 */
class HTTP请求构建器 internal constructor(private val 网址: 字符串) {
    private var 方法: HTTP方法 = HTTP方法.GET
    private var 请求体: 字符串? = null
    private var 请求字节数据: 字节数组? = null
    private var 超时时间: 整数 = 5000 // 默认5秒
    private val 头信息: MutableMap<字符串, 字符串> = mutableMapOf()
    private val 查询参数: MutableMap<字符串, 字符串> = mutableMapOf()
    private var 上传文件: File? = null
    private var 上传字段名: 字符串 = "file"

    /**
     * 设置请求方法
     */
    fun 使用(方法: HTTP方法): HTTP请求构建器 {
        this.方法 = 方法
        return this
    }

    /**
     * 设置请求体
     */
    fun 请求体(内容: 字符串): HTTP请求构建器 {
        this.请求体 = 内容
        return this
    }

    /**
     * 设置请求体（字节数据）
     */
    fun 请求体(数据: 字节数组): HTTP请求构建器 {
        this.请求字节数据 = 数据
        return this
    }

    /**
     * 设置超时时间（毫秒）
     */
    fun 超时(毫秒: 整数): HTTP请求构建器 {
        this.超时时间 = 毫秒
        return this
    }

    /**
     * 添加请求头
     */
    fun 头(键: 字符串, 值: 字符串): HTTP请求构建器 {
        this.头信息[键] = 值
        return this
    }

    /**
     * 添加查询参数
     */
    fun 参数(键: 字符串, 值: 字符串): HTTP请求构建器 {
        this.查询参数[键] = 值
        return this
    }

    /**
     * 设置上传文件
     */
    fun 上传文件(文件路径: 字符串, 字段名: 字符串 = "file"): HTTP请求构建器 {
        this.上传文件 = File(文件路径)
        this.上传字段名 = 字段名
        return this
    }

    /**
     * 设置上传文件（File对象）
     */
    fun 上传文件(文件: File, 字段名: 字符串 = "file"): HTTP请求构建器 {
        this.上传文件 = 文件
        this.上传字段名 = 字段名
        return this
    }

    /**
     * 执行请求并返回响应
     */
    fun 执行(): 网络响应 {
        return try {
            val 完整URL = if (查询参数.isNotEmpty()) {
                val 参数字符串 = 查询参数.entries.joinToString("&") { "${it.key}=${it.value}" }
                "$网址?$参数字符串"
            } else {
                网址
            }

            val 连接 = URL(完整URL).openConnection() as HttpURLConnection
            连接.requestMethod = 方法.name
            连接.connectTimeout = 超时时间
            连接.readTimeout = 超时时间
            连接.instanceFollowRedirects = true

            // 设置请求头
            头信息.forEach { (键, 值) ->
                连接.setRequestProperty(键, 值)
            }

            // 处理文件上传
            if (上传文件 != null && 上传文件!!.exists()) {
                val 边界 = "----WebKitFormBoundary${System.currentTimeMillis()}"
                连接.doOutput = true
                连接.setRequestProperty("Content-Type", "multipart/form-data; boundary=$边界")

                连接.outputStream.use { outputStream ->
                    val 写入器 = outputStream.bufferedWriter()
                    
                    // 写入文件部分
                    写入器.write("--$边界\r\n")
                    写入器.write("Content-Disposition: form-data; name=\"$上传字段名\"; filename=\"${上传文件!!.name}\"\r\n")
                    写入器.write("Content-Type: application/octet-stream\r\n\r\n")
                    写入器.flush()
                    
                    // 写入文件内容
                    上传文件!!.inputStream().use { fileInput ->
                        fileInput.copyTo(outputStream)
                    }
                    
                    写入器.write("\r\n--$边界--\r\n")
                    写入器.flush()
                }
            }
            // 如果有请求体，则输出
            else if (请求体 != null && (方法 == HTTP方法.POST || 方法 == HTTP方法.PUT || 方法 == HTTP方法.PATCH)) {
                连接.doOutput = true
                连接.outputStream.use { os ->
                    os.write(请求体!!.toByteArray(Charsets.UTF_8))
                    os.flush()
                }
            }
            // 如果有字节数据请求体
            else if (请求字节数据 != null && (方法 == HTTP方法.POST || 方法 == HTTP方法.PUT || 方法 == HTTP方法.PATCH)) {
                连接.doOutput = true
                连接.outputStream.use { os ->
                    os.write(请求字节数据!!)
                    os.flush()
                }
            }

            val 状态码 = 连接.responseCode
            val 响应内容 = try {
                连接.inputStream.bufferedReader().use { it.readText() }
            } catch (e: IOException) {
                连接.errorStream?.bufferedReader()?.use { it.readText() } ?: ""
            }

            val 响应头 = mutableMapOf<字符串, 列表<字符串>>()
            for (i in 0 until 连接.headerFields.size) {
                val 键 = 连接.getHeaderFieldKey(i)
                val 值 = 连接.getHeaderField(i)
                if (键 != null && 值 != null) {
                    响应头[键] = listOf(值)
                }
            }

            连接.disconnect()

            网络响应(
                状态码 = 状态码,
                内容 = 响应内容,
                消息 = 连接.responseMessage,
                头信息 = 响应头
            )
        } catch (e: Exception) {
            网络响应(
                状态码 = -1,
                内容 = "",
                消息 = "请求异常: ${e.message}"
            )
        }
    }
}

/**
 * 发起 HTTP 请求的入口函数
 * 
 * 用法：
 *   val 响应 = HTTP请求("https://api.example.com/data") {
 *       使用(HTTP方法.GET)
 *       头("Authorization", "Bearer token")
 *       超时(3000)
 *   }.执行()
 */
fun HTTP请求(网址: 字符串, 配置: HTTP请求构建器.() -> 无 = {}): HTTP请求构建器 {
    return HTTP请求构建器(网址).also(配置)
}

/**
 * 简化的 GET 请求
 */
fun GET请求(网址: 字符串): 网络响应 {
    return HTTP请求(网址) { 使用(HTTP方法.GET) }.执行()
}

/**
 * 简化的 POST 请求
 */
fun POST请求(网址: 字符串, 数据: 字符串): 网络响应 {
    return HTTP请求(网址) {
        使用(HTTP方法.POST)
        请求体(数据)
        头("Content-Type", "application/json")
    }.执行()
}

/**
 * 下载文件到指定路径
 * 
 * 用法：
 *   下载文件("https://example.com/file.zip", "downloads/file.zip")
 */
fun 下载文件(网址: 字符串, 保存路径: 字符串, 超时: 整数 = 30000): 布尔 {
    return try {
        val 连接 = URL(网址).openConnection() as HttpURLConnection
        连接.requestMethod = "GET"
        连接.connectTimeout = 超时
        连接.readTimeout = 超时
        连接.instanceFollowRedirects = true

        val 状态码 = 连接.responseCode
        if (状态码 !in 200..299) {
            连接.disconnect()
            return false
        }

        // 创建父目录
        val 文件 = File(保存路径)
        文件.parentFile?.mkdirs()

        // 下载文件
        连接.inputStream.use { 输入流 ->
            FileOutputStream(文件).use { 输出流 ->
                输入流.copyTo(输出流)
            }
        }

        连接.disconnect()
        true
    } catch (e: Exception) {
        false
    }
}

/**
 * 下载文件为字节数组
 * 
 * 用法：
 *   val 数据 = 下载文件为字节("https://example.com/image.png")
 */
fun 下载文件为字节(网址: 字符串, 超时: 整数 = 30000): 字节数组? {
    return try {
        val 连接 = URL(网址).openConnection() as HttpURLConnection
        连接.requestMethod = "GET"
        连接.connectTimeout = 超时
        连接.readTimeout = 超时
        连接.instanceFollowRedirects = true

        val 状态码 = 连接.responseCode
        if (状态码 !in 200..299) {
            连接.disconnect()
            return null
        }

        val 结果 = 连接.inputStream.use { it.readBytes() }
        连接.disconnect()
        结果
    } catch (e: Exception) {
        null
    }
}

/**
 * 上传文件（简化版）
 * 
 * 用法：
 *   val 响应 = 上传文件("https://api.example.com/upload", "path/to/file.pdf")
 */
fun 上传文件(网址: 字符串, 文件路径: 字符串, 字段名: 字符串 = "file"): 网络响应 {
    return HTTP请求(网址) {
        使用(HTTP方法.POST)
        上传文件(文件路径, 字段名)
    }.执行()
}
