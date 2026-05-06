package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.可变映射
import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.映射
import com.ohuang.kotlin_zh.键值对
import com.ohuang.kotlin_zh.无

infix fun <键, 值> 键.到值(值: 值): 键值对<键, 值> = this to 值

fun <键, 值> 映射构建(vararg 条目: 键值对<键, 值>): 映射<键, 值> = mapOf(*条目)

fun <键, 值> 可变映射构建(vararg 条目: 键值对<键, 值>): 可变映射<键, 值> = mutableMapOf(*条目)

val 映射<*, *>.条目数: 整数
    get() = size

fun 映射<*, *>.非空映射(): 布尔 = isNotEmpty()

fun <键, 值> 映射<键, 值>.取值(键名: 键): 值? = this[键名]

fun <键, 值> 映射<键, 值>.取值或默认(键名: 键, 默认值: 值): 值 = getOrDefault(键名, 默认值)

inline fun <键, 值> 映射<键, 值>.取值或计算(键名: 键, 默认值: () -> 值): 值 =
    this[键名] ?: 默认值()

fun <键, 值> 映射<键, 值>.包含键(键名: 键): 布尔 = containsKey(键名)

fun <键, 值> 映射<键, 值>.包含值(值内容: 值): 布尔 = containsValue(值内容)

fun <键, 值> 可变映射<键, 值>.放入(键名: 键, 值内容: 值): 值? = put(键名, 值内容)

inline fun <键, 值> 可变映射<键, 值>.若无则放入(键名: 键, 默认值: () -> 值): 值 =
    getOrPut(键名, 默认值)

fun <键, 值> 可变映射<键, 值>.移除键(键名: 键): 值? = remove(键名)

fun <键, 值> 映射<键, 值>.键列表(): 列表<键> = keys.toList()

fun <键, 值> 映射<键, 值>.值列表(): 列表<值> = values.toList()

inline fun <键, 值, 新值> 映射<键, 值>.映射值变换(转换: (键, 值) -> 新值): 映射<键, 新值> =
    mapValues { (键名, 值内容) -> 转换(键名, 值内容) }

inline fun <键, 值> 映射<键, 值>.逐项(操作: (键, 值) -> 无): 无 {
    forEach { (键名, 值内容) -> 操作(键名, 值内容) }
}

inline fun <键, 值> 映射<键, 值>.筛选项(条件: (键, 值) -> 布尔): 映射<键, 值> =
    filter { (键名, 值内容) -> 条件(键名, 值内容) }

fun <键 : Comparable<键>, 值> 映射<键, 值>.按键排序(): 映射<键, 值> =
    entries.sortedBy { it.key }.associate { it.toPair() }

fun <键, 值 : Comparable<值>> 映射<键, 值>.按值排序(): 映射<键, 值> =
    entries.sortedBy { it.value }.associate { it.toPair() }

inline fun <键, 值, 新键> 映射<键, 值>.映射键变换(转换: (键, 值) -> 新键): 映射<新键, 值> =
    entries.associate { (键名, 值内容) -> 转换(键名, 值内容) to 值内容 }

infix fun <键, 值> 映射<键, 值>.合并(另一个: 映射<键, 值>): 映射<键, 值> = this + 另一个
