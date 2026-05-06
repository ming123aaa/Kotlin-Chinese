package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.可变列表
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.无
import com.ohuang.kotlin_zh.任意值
import com.ohuang.kotlin_zh.双精度
import com.ohuang.kotlin_zh.字符串
import com.ohuang.kotlin_zh.数组
import com.ohuang.kotlin_zh.映射
import kotlin.collections.flatten

/**
 * 创建数组
 * 
 * 用法：
 *   val 数组 = 数组构建(1, 2, 3, 4, 5)
 */
inline fun <reified 甲> 数组构建(vararg 元素: 甲): 数组<甲> = arrayOf(*元素)

/**
 * 创建指定大小的数组
 * 
 * 用法：
 *   val 数组 = 创建数组(5) { it * 2 }  // [0, 2, 4, 6, 8]
 */
inline fun <reified 甲> 创建数组(大小: 整数, 初始化: (整数) -> 甲): 数组<甲> {
    return Array(大小) { 初始化(it) }
}

/**
 * 获取数组长度
 */
val <甲> 数组<甲>.长度: 整数
    get() = size

/**
 * 检查数组是否为空
 */
val <甲> 数组<甲>.为空: 布尔
    get() = isEmpty()

/**
 * 检查数组是否非空
 */
val <甲> 数组<甲>.非空: 布尔
    get() = isNotEmpty()

/**
 * 获取数组第一个元素
 */
fun <甲> 数组<甲>.首个(): 甲 {
    return first()
}

/**
 * 获取数组最后一个元素
 */
fun <甲> 数组<甲>.末尾(): 甲 {
    return last()
}

/**
 * 检查数组是否包含指定元素
 */
fun <甲> 数组<甲>.包含元素(元素: 甲): 布尔 {
    return contains(element = 元素)
}

/**
 * 查找元素在数组中的索引
 */
fun <甲> 数组<甲>.查找索引(元素: 甲): 整数 {
    return indexOf(element = 元素)
}

/**
 * 判断元素是否在数组中
 */
infix fun <甲> 甲.存在于(数组: 数组<甲>): 布尔 {
    return 数组.contains(this)
}

/**
 * 过滤数组元素
 */
inline fun <甲> 数组<甲>.过滤(条件: (甲) -> 布尔): 列表<甲> {
    return filter(条件)
}

/**
 * 映射数组元素
 */
inline fun <甲, 乙> 数组<甲>.映射(转换: (甲) -> 乙): 列表<乙> {
    return map(transform = 转换)
}

/**
 * 对数组每个元素执行操作
 */
inline fun <甲> 数组<甲>.逐项(操作: (甲) -> 无) {
    forEach(action = 操作)
}

/**
 * 带索引遍历数组
 */
inline fun <甲> 数组<甲>.逐项带索引(操作: (整数, 甲) -> 无) {
    forEachIndexed(action = 操作)
}

/**
 * 数组排序
 */
fun <甲 : Comparable<甲>> 数组<甲>.排序(): 列表<甲> {
    return sorted()
}

/**
 * 按条件排序数组
 */
inline fun <甲, 乙 : Comparable<乙>> 数组<甲>.按排序(crossinline 键选择器: (甲) -> 乙): 列表<甲> {
    return sortedBy(selector = 键选择器)
}

/**
 * 反转数组
 */
fun <甲> 数组<甲>.反转(): 列表<甲> {
    return reversed()
}

/**
 * 截取数组片段
 */
fun <甲> 数组<甲>.切片(起始: 整数, 结束: 整数): 列表<甲> {
    return slice(indices = 起始 until 结束)
}

/**
 * 取数组前N个元素
 */
fun <甲> 数组<甲>.取前(数量: 整数): 列表<甲> {
    return take(n = 数量)
}

/**
 * 取数组后N个元素
 */
fun <甲> 数组<甲>.取后(数量: 整数): 列表<甲> {
    return takeLast(n = 数量)
}

/**
 * 丢弃数组前N个元素
 */
fun <甲> 数组<甲>.丢弃前(数量: 整数): 列表<甲> {
    return drop(n = 数量)
}

/**
 * 丢弃数组后N个元素
 */
fun <甲> 数组<甲>.丢弃后(数量: 整数): 列表<甲> {
    return dropLast(n = 数量)
}

/**
 * 数组去重
 */
fun <甲> 数组<甲>.去重(): 列表<甲> {
    return distinct()
}

/**
 * 数组转列表
 */
fun <甲> 数组<甲>.转列表(): 列表<甲> {
    return toList()
}

/**
 * 数组转可变列表
 */
fun <甲> 数组<甲>.转可变列表(): 可变列表<甲> {
    return toMutableList()
}

/**
 * 连接数组元素为字符串
 */
fun <甲> 数组<甲>.连接为文本(
    分隔符: CharSequence = ", ",
    前缀: CharSequence = "",
    后缀: CharSequence = ""
): 字符串 {
    return joinToString(separator = 分隔符, prefix = 前缀, postfix = 后缀)
}

/**
 * 折叠数组（归约）
 */
inline fun <甲, 乙> 数组<甲>.折叠(初始值: 乙, 操作: (累计: 乙, 元素: 甲) -> 乙): 乙 {
    return fold(initial = 初始值, operation = 操作)
}

/**
 * 数组求和（数值类型）
 */
fun 数组<整数>.求和(): 整数 {
    return sum()
}

/**
 * 数组求平均值（数值类型）
 */
fun 数组<整数>.平均值(): 双精度 {
    return average()
}

/**
 * 数组最大值
 */
fun <甲 : Comparable<甲>> 数组<甲>.最大值(): 甲 {
    return max()
}

/**
 * 数组最小值
 */
fun <甲 : Comparable<甲>> 数组<甲>.最小值(): 甲 {
    return min()
}

/**
 * 检查数组是否所有元素都满足条件
 */
inline fun <甲> 数组<甲>.所有满足(条件: (甲) -> 布尔): 布尔 {
    return all(predicate = 条件)
}

/**
 * 检查数组是否存在满足条件的元素
 */
inline fun <甲> 数组<甲>.存在(条件: (甲) -> 布尔): 布尔 {
    return any(predicate = 条件)
}

/**
 * 检查数组是否没有元素满足条件
 */
inline fun <甲> 数组<甲>.都不满足(条件: (甲) -> 布尔): 布尔 {
    return none(predicate = 条件)
}

/**
 * 统计满足条件的元素数量
 */
inline fun <甲> 数组<甲>.计数(条件: (甲) -> 布尔 = { true }): 整数 {
    return count(predicate = 条件)
}

/**
 * 分组数组元素
 */
inline fun <甲, 键> 数组<甲>.分组(键选择器: (甲) -> 键): 映射<键, 列表<甲>> {
    return groupBy(keySelector = 键选择器)
}

/**
 * 关联数组元素为Map
 */
inline fun <甲, 键, 值> 数组<甲>.关联(
    键选择器: (甲) -> 键,
    值选择器: (甲) -> 值
): 映射<键, 值> {
    return associate { 键选择器(it) to 值选择器(it) }
}


/**
 * 合并两个数组
 */
infix fun <甲> 数组<甲>.合并(其他: 数组<甲>): 列表<甲> {
    return this.toList() + 其他.toList()
}

/**
 * 填充数组
 */
inline fun <甲> 数组<甲>.填充(默认值: () -> 甲): 无 {
    fill(element = 默认值())
}
