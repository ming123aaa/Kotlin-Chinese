package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.可变列表
import com.ohuang.kotlin_zh.集合
import com.ohuang.kotlin_zh.可变集合
import com.ohuang.kotlin_zh.任意值
import com.ohuang.kotlin_zh.字符串
import com.ohuang.kotlin_zh.无

fun <甲> 列出(vararg 元素: 甲): 列表<甲> = listOf(*元素)

fun <甲> 可变列出(vararg 元素: 甲): 可变列表<甲> = mutableListOf(*元素)

fun <甲> 集(vararg 元素: 甲): 集合<甲> = setOf(*元素)

fun <甲> 可变集(vararg 元素: 甲): 可变集合<甲> = mutableSetOf(*元素)

inline fun 构建字符串(构建: StringBuilder.() -> 无): 字符串 = buildString(构建)

fun StringBuilder.追加行(内容: 任意值?): StringBuilder = appendLine(内容)

fun Iterable<*>.连接为文本(
    分隔符: CharSequence = ", ",
    前缀: CharSequence = "",
    后缀: CharSequence = ""
): 字符串 = joinToString(separator = 分隔符, prefix = 前缀, postfix = 后缀)

fun 任意值?.转文本(): 字符串 = this.toString()
