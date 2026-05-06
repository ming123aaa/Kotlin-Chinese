package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.无

/**
 * 循环：对应 while(条件) { ... }
 *
 * 用法：
 *   var i = 0
 *   循环({ i < 5 }) { i++ }
 */
inline fun 循环(条件: () -> 布尔, 执行: () -> 无) {
    while (条件()) {
        执行()
    }
}

/**
 * 重复：对应 repeat(次数) { ... }
 *
 * 用法：
 *   重复(3) { println("第 $it 次") }
 */
inline fun 重复(次数: 整数, 执行: (整数) -> 无) = repeat(次数, 执行)

/**
 * 中断信号：用于在 forEach/映射等高阶函数中模拟 break 语义。
 * 配合 捕获中断 使用，外层收到信号后停止处理。
 *
 * 用法：
 *   捕获中断 {
 *       listOf(1, 2, 3).forEach { item ->
 *           if (item == 2) 中断()
 *           println(item)
 *       }
 *   }
 *   // 输出：1
 *
 * 注意：Kotlin 原生 while 循环内请直接使用 break；
 *       此工具仅针对 lambda 中无法使用 break 的场景。
 */
class 中断信号 internal constructor() : Exception()

inline fun 捕获中断(执行: () -> 无) {
    try {
        执行()
    } catch (_: 中断信号) {
        // 捕获后正常退出，等同于 break
    }
}

fun 中断(): Nothing = throw 中断信号()
