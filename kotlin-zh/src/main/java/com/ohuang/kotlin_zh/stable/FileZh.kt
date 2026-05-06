package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.字符串
import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.无
import com.ohuang.kotlin_zh.长整数
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

/**
 * 文件信息封装类
 */
data class 文件信息(
    val 路径: 字符串,
    val 名称: 字符串,
    val 大小: 长整数,
    val 是否为文件: 布尔,
    val 是否为目录: 布尔,
    val 是否存在: 布尔,
    val 最后修改时间: 长整数
) {
    fun 扩展名(): 字符串 {
        return 名称.substringAfterLast('.', "")
    }
    
    fun 不含扩展名(): 字符串 {
        return 名称.substringBeforeLast('.')
    }
}

/**
 * 检查文件或目录是否存在
 */
fun 文件存在(路径: 字符串): 布尔 {
    return File(路径).exists()
}

/**
 * 检查是否为文件
 */
fun 是文件(路径: 字符串): 布尔 {
    return File(路径).isFile
}

/**
 * 检查是否为目录
 */
fun 是目录(路径: 字符串): 布尔 {
    return File(路径).isDirectory
}

/**
 * 创建目录（包括父目录）
 */
fun 创建目录(路径: 字符串): 布尔 {
    return File(路径).mkdirs()
}

/**
 * 删除文件或目录
 */
fun 删除文件(路径: 字符串): 布尔 {
    return File(路径).delete()
}

/**
 * 获取文件信息
 */
fun 获取文件信息(路径: 字符串): 文件信息? {
    val 文件 = File(路径)
    if (!文件.exists()) return null
    
    return 文件信息(
        路径 = 文件.absolutePath,
        名称 = 文件.name,
        大小 = 文件.length(),
        是否为文件 = 文件.isFile,
        是否为目录 = 文件.isDirectory,
        是否存在 = true,
        最后修改时间 = 文件.lastModified()
    )
}

/**
 * 读取文本文件内容
 */
fun 读取文本文件(路径: 字符串, 编码: 字符串 = "UTF-8"): 字符串 {
    return try {
        File(路径).readText(Charset.forName(编码))
    } catch (e: IOException) {
        ""
    }
}

/**
 * 写入文本文件
 */
fun 写入文本文件(路径: 字符串, 内容: 字符串, 编码: 字符串 = "UTF-8", 追加: 布尔 = false): 布尔 {
    return try {
        File(路径).writeText(内容, Charset.forName(编码))
        true
    } catch (e: IOException) {
        false
    }
}

/**
 * 追加文本到文件
 */
fun 追加到文件(路径: 字符串, 内容: 字符串, 编码: 字符串 = "UTF-8"): 布尔 {
    return try {
        File(路径).appendText(内容, Charset.forName(编码))
        true
    } catch (e: IOException) {
        false
    }
}

/**
 * 读取文件的行列表
 */
fun 读取文件行(路径: 字符串, 编码: 字符串 = "UTF-8"): 列表<字符串> {
    return try {
        File(路径).readLines(Charset.forName(编码))
    } catch (e: IOException) {
        emptyList()
    }
}

/**
 * 列出目录下的文件和子目录
 */
fun 列出目录内容(路径: 字符串): 列表<字符串> {
    return try {
        File(路径).listFiles()?.map { it.absolutePath } ?: emptyList()
    } catch (e: SecurityException) {
        emptyList()
    }
}

/**
 * 获取当前工作目录
 */
fun 当前目录(): 字符串 {
    return System.getProperty("user.dir")
}

/**
 * 获取用户主目录
 */
fun 用户主目录(): 字符串 {
    return System.getProperty("user.home")
}

/**
 * 获取临时目录
 */
fun 临时目录(): 字符串 {
    return System.getProperty("java.io.tmpdir")
}

/**
 * 文件操作 DSL 构建器
 */
class 文件操作构建器 internal constructor(private val 路径: 字符串) {
    private var 编码: 字符串 = "UTF-8"
    private var 自动创建目录: 布尔 = false

    /**
     * 设置文件编码
     */
    fun 使用编码(编码: 字符串): 文件操作构建器 {
        this.编码 = 编码
        return this
    }

    /**
     * 设置自动创建父目录
     */
    fun 自动创建目录(): 文件操作构建器 {
        this.自动创建目录 = true
        return this
    }

    /**
     * 读取文件内容
     */
    fun 读取(): 字符串 {
        return 读取文本文件(路径, 编码)
    }

    /**
     * 写入文件内容
     */
    fun 写入(内容: 字符串): 布尔 {
        if (自动创建目录) {
            File(路径).parentFile?.mkdirs()
        }
        return 写入文本文件(路径, 内容, 编码)
    }

    /**
     * 追加内容到文件
     */
    fun 追加(内容: 字符串): 布尔 {
        if (自动创建目录) {
            File(路径).parentFile?.mkdirs()
        }
        return 追加到文件(路径, 内容, 编码)
    }

    /**
     * 获取文件信息
     */
    fun 信息(): 文件信息? {
        return 获取文件信息(路径)
    }

    /**
     * 删除文件
     */
    fun 删除(): 布尔 {
        return 删除文件(路径)
    }

    /**
     * 检查文件是否存在
     */
    fun 存在(): 布尔 {
        return 文件存在(路径)
    }
}

/**
 * 文件操作的入口函数
 * 
 * 用法：
 *   文件操作("path/to/file.txt") {
 *       使用编码("GBK")
 *       自动创建目录()
 *       写入("Hello World")
 *   }
 */
fun 文件操作(路径: 字符串, 操作: 文件操作构建器.() -> 无 = {}): 文件操作构建器 {
    return 文件操作构建器(路径).also(操作)
}

/**
 * 简化读取文件
 */
fun 读取文件(路径: 字符串): 字符串 {
    return 读取文本文件(路径)
}

/**
 * 简化写入文件
 */
fun 写入文件(路径: 字符串, 内容: 字符串): 布尔 {
    return 写入文本文件(路径, 内容)
}
