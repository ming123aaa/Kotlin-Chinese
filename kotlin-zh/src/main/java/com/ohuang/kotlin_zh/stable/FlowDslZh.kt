package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.无

inline fun <甲> 甲.管道(步骤: (甲) -> 甲): 甲 = 步骤(this)

inline fun <甲, 乙> 甲.变换(步骤: (甲) -> 乙): 乙 = 步骤(this)

fun 如果(条件: 布尔, 执行: () -> 无): 否则分支 {
    if (条件) {
        执行()
    }
    return 否则分支(条件)
}

class 否则分支 constructor(private val 命中条件: 布尔) {
    infix fun 否则(执行: () -> 无) {
        if (!命中条件) {
            执行()
        }
    }
}
