package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.结果
import com.ohuang.kotlin_zh.无

inline fun <甲, 乙> 当(目标: 甲, 分支: (甲) -> 乙): 乙 = 分支(目标)

inline fun <甲> 尝试(操作: () -> 甲): 结果<甲> = runCatching(操作)

inline fun <甲> 结果<甲>.成功时(操作: (甲) -> 无): 结果<甲> = onSuccess(操作)

inline fun <甲> 结果<甲>.失败时(操作: (Throwable) -> 无): 结果<甲> = onFailure(操作)

fun <甲> 重试(次数: 整数, 操作: () -> 甲): 甲 {
    require(次数 > 0) { "重试次数必须大于 0" }

    var 最后异常: Throwable? = null
    repeat(次数) {
        try {
            return 操作()
        } catch (异常: Throwable) {
            最后异常 = 异常
        }
    }

    throw (最后异常 ?: IllegalStateException("重试失败且未捕获异常"))
}
