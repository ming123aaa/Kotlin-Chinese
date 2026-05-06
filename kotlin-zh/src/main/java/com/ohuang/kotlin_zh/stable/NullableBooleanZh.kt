package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.无

inline fun <甲> 甲?.非空时(操作: (甲) -> 无): 甲? {
    if (this != null) {
        操作(this)
    }
    return this
}

inline fun <甲> 甲?.为空时(操作: () -> 无): 甲? {
    if (this == null) {
        操作()
    }
    return this
}

inline fun <甲, 乙> 甲?.若为空(默认值: () -> 乙, 变换: (甲) -> 乙): 乙 =
    this?.let(变换) ?: 默认值()

inline fun 布尔.为真时(操作: () -> 无): 布尔 {
    if (this) {
        操作()
    }
    return this
}

inline fun 布尔.为假时(操作: () -> 无): 布尔 {
    if (!this) {
        操作()
    }
    return this
}

inline fun <甲> 布尔.则(当真: () -> 甲, 当假: () -> 甲): 甲 = if (this) 当真() else 当假()
