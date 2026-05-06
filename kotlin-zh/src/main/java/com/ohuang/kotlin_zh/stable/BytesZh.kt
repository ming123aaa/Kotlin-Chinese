package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.字符串
import com.ohuang.kotlin_zh.字节
import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.无
import com.ohuang.kotlin_zh.字节数组
import java.nio.charset.Charset

/**
 * 创建字节数组
 * 
 * 用法：
 *   val 字节数组 = 字节数组构建(72, 101, 108, 108, 111)  // "Hello" 的 ASCII
 */
fun 字节数组构建(vararg 元素: 字节): 字节数组 = byteArrayOf(*元素)

/**
 * 从字符串创建字节数组
 * 
 * 用法：
 *   val 字节数组 = 字符串转字节("Hello")
 */
fun 字符串转字节(文本: 字符串, 编码: 字符串 = "UTF-8"): 字节数组 {
    return 文本.toByteArray(Charset.forName(编码))
}

/**
 * 字节数组转字符串
 * 
 * 用法：
 *   val 文本 = 字节转字符串(字节数组)
 */
fun 字节转字符串(字节数组: 字节数组, 编码: 字符串 = "UTF-8"): 字符串 {
    return 字节数组.toString(Charset.forName(编码))
}

/**
 * 从十六进制字符串创建字节数组
 * 
 * 用法：
 *   val 字节数组 = 十六进制转字节("48656C6C6F")  // "Hello"
 */
fun 十六进制转字节(十六进制: 字符串): 字节数组 {
    require(十六进制.length % 2 == 0) { "十六进制字符串长度必须为偶数" }
    
    val 长度 = 十六进制.length / 2
    val 结果 = 字节数组(长度)
    
    for (i in 0 until 长度) {
        val 索引 = i * 2
        val 字节值 = 十六进制.substring(索引, 索引 + 2).toInt(16)
        结果[i] = 字节值.toByte()
    }
    
    return 结果
}

/**
 * 字节数组转十六进制字符串
 * 
 * 用法：
 *   val 十六进制 = 字节转十六进制(字节数组)
 */
fun 字节转十六进制(字节数组: 字节数组): 字符串 {
    return 字节数组.joinToString("") { 
        String.format("%02X", it.toInt() and 0xFF) 
    }
}

/**
 * 从Base64字符串创建字节数组
 * 
 * 用法：
 *   val 字节数组 = Base64转字节("SGVsbG8=")
 */

@Suppress("NewApi")
fun Base64转字节(base64: 字符串): 字节数组 {
    return java.util.Base64.getDecoder().decode(base64)
}

/**
 * 字节数组转Base64字符串
 * 
 * 用法：
 *   val base64 = 字节转Base64(字节数组)
 */
@Suppress("NewApi")
fun 字节转Base64(字节数组: 字节数组): 字符串 {
    return java.util.Base64.getEncoder().encodeToString(字节数组)
}

/**
 * 获取字节数组长度
 */
val 字节数组.字节长度: 整数
    get() = size

/**
 * 检查字节数组是否为空
 */
val 字节数组.字节为空: 布尔
    get() = isEmpty()

/**
 * 检查字节数组是否非空
 */
val 字节数组.字节非空: 布尔
    get() = isNotEmpty()

/**
 * 截取字节数组片段
 */
fun 字节数组.字节切片(起始: 整数, 结束: 整数): 字节数组 {
    return copyOfRange(fromIndex = 起始, toIndex = 结束)
}


/**
 * 比较两个字节数组是否相等
 */
infix fun 字节数组.字节等于(其他: 字节数组): 布尔 {
    return contentEquals(其他)
}

/**
 * 字节数组转列表
 */
fun 字节数组.字节转列表(): 列表<字节> {
    return toList()
}

/**
 * 对每个字节执行操作
 */
inline fun 字节数组.逐字节(操作: (字节) -> 无) {
    forEach(action = 操作)
}

/**
 * 带索引遍历字节数组
 */
inline fun 字节数组.逐字节带索引(操作: (整数, 字节) -> 无) {
    forEachIndexed(action = 操作)
}

/**
 * 映射字节数组
 */
inline fun 字节数组.映射字节(转换: (字节) -> 字节): 字节数组 {
    return map(transform = 转换).toByteArray()
}

/**
 * 过滤字节数组
 */
inline fun 字节数组.过滤字节(条件: (字节) -> 布尔): 字节数组 {
    return filter(predicate = 条件).toByteArray()
}

/**
 * 查找字节在数组中的位置
 */
fun 字节数组.查找字节(目标: 字节): 整数 {
    return indexOf(element = 目标)
}

/**
 * 检查是否包含指定字节
 */
fun 字节数组.包含字节(目标: 字节): 布尔 {
    return contains(element = 目标)
}

/**
 * 反转字节数组
 */
fun 字节数组.反转字节(): 字节数组 {
    return reversedArray()
}

/**
 * 填充字节数组
 */
fun 字节数组.填充字节(值: 字节): 无 {
    fill(element = 值)
}

/**
 * 复制字节数组
 */
fun 字节数组.复制字节(): 字节数组 {
    return copyOf()
}

/**
 * 调整字节数组大小
 */
fun 字节数组.调整大小(新大小: 整数): 字节数组 {
    return copyOf(newSize = 新大小)
}

/**
 * 计算字节数组的哈希码
 */
fun 字节数组.字节哈希(): 整数 {
    return contentHashCode()
}

/**
 * 字节数组转整数（大端序）
 * 
 * 用法：
 *   val 整数 = 字节数组.字节数组转整数()
 */
fun 字节数组.字节数组转整数(): 整数 {

    require(this.size >= 4) { "字节数组长度至少为4" }
    return ((this[0].toInt() and 0xFF) shl 24) or
           ((this[1].toInt() and 0xFF) shl 16) or
           ((this[2].toInt() and 0xFF) shl 8) or
           (this[3].toInt() and 0xFF)
}

inline fun  字节数组.所有(断言: (字节) -> 布尔): 布尔 {

    return all(断言)
}

/**
 * 整数转字节数组（大端序）
 * 
 * 用法：
 *   val 字节数组 = 整数转字节数组(123456)
 */
fun 整数转字节数组(数值: 整数): 字节数组 {
    return 字节数组构建(
        ((数值 shr 24) and 0xFF).toByte(),
        ((数值 shr 16) and 0xFF).toByte(),
        ((数值 shr 8) and 0xFF).toByte(),
        (数值 and 0xFF).toByte()
    )
}

/**
 * 创建指定大小的字节数组
 */
fun 创建字节数组(大小: 整数, 初始值: 字节 = 0): 字节数组 {
    return 字节数组(size = 大小, init = { 初始值 })
}

/**
 * 生成随机字节数组
 */
fun 随机字节数组(大小: 整数): 字节数组 {
    val 随机 = java.util.Random()
    val 结果 = 字节数组(大小)
    随机.nextBytes(结果)
    return 结果
}


