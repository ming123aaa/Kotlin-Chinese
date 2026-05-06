package com.ohuang.kotlin_zh.stable



/**
 * 「返回」：用于在带接收者的 lambda 或 run 块中以中文语义明确结束并返回值。
 *
 * 在普通函数中，直接使用 Kotlin 原生 return 即可，无法替换。
 * 此辅助函数仅用于 inline lambda 的「提前返回值」表达场景：
 *
 * 用法：
 *   val 结果 = run { 返回(42) }
 */
fun <甲> 返回(值: 甲): 甲 = 值
