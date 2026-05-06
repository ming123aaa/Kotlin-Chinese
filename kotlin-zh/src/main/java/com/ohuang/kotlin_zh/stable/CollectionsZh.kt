package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.映射
import com.ohuang.kotlin_zh.集合
import com.ohuang.kotlin_zh.可变集合
import com.ohuang.kotlin_zh.无

val Collection<*>.数量: 整数
	get() = size

fun Collection<*>.非空(): 布尔 = isNotEmpty()

inline fun <甲, 乙> Iterable<甲>.映射(转换: (甲) -> 乙): 列表<乙> = map(转换)

inline fun <甲> Iterable<甲>.过滤(条件: (甲) -> 布尔): 列表<甲> = filter(条件)

inline fun <甲, 乙> Iterable<甲>.折叠(初始值: 乙, 操作: (累计: 乙, 元素: 甲) -> 乙): 乙 = fold(初始值, 操作)

inline fun <甲, 键> Iterable<甲>.分组(键选择器: (甲) -> 键): 映射<键, 列表<甲>> = groupBy(键选择器)

fun <甲 : Comparable<甲>> Iterable<甲>.排序(): 列表<甲> = sorted()

inline fun <甲, 乙 : Comparable<乙>> Iterable<甲>.按排序(crossinline 键选择器: (甲) -> 乙): 列表<甲> =
	sortedBy { 键选择器(it) }

inline fun <键, 值, 乙> 映射<键, 值>.映射值(转换: (Map.Entry<键, 值>) -> 乙): 列表<乙> = entries.map(转换)

// ========== Set 集合扩展 ==========

val Collection<*>.为空: 布尔
    get() = isEmpty()

fun <甲> 集合<甲>.包含元素(元素: 甲): 布尔 = contains(元素)

fun <甲> 集合<甲>.不包含元素(元素: 甲): 布尔 = !contains(元素)

inline fun <甲> 集合<甲>.存在(条件: (甲) -> 布尔): 布尔 = any(条件)

inline fun <甲> 集合<甲>.所有满足(条件: (甲) -> 布尔): 布尔 = all(条件)

inline fun <甲> 集合<甲>.都不满足(条件: (甲) -> 布尔): 布尔 = none(条件)

fun <甲> 集合<甲>.与(其他: 集合<甲>): 集合<甲> = intersect(其他)

fun <甲> 集合<甲>.或(其他: 集合<甲>): 集合<甲> = union(其他)

fun <甲> 集合<甲>.差集(其他: 集合<甲>): 集合<甲> = subtract(其他)

// ========== MutableSet 可变集合扩展 ==========

fun <甲> 可变集合<甲>.添加(元素: 甲): 布尔 = add(元素)

fun <甲> 可变集合<甲>.移除(元素: 甲): 布尔 = remove(元素)

fun <甲> 可变集合<甲>.添加全部(元素: 集合<甲>): 布尔 = addAll(元素)

fun <甲> 可变集合<甲>.移除全部(元素: 集合<甲>): 布尔 = removeAll(元素)

fun <甲> 可变集合<甲>.保留全部(元素: 集合<甲>): 布尔 = retainAll(元素)

fun <甲> 可变集合<甲>.清空(): 无 = clear()

val <甲> 可变集合<甲>.是否为空: 布尔
    get() = isEmpty()

val <甲> 可变集合<甲>.是否非空: 布尔
    get() = isNotEmpty()
