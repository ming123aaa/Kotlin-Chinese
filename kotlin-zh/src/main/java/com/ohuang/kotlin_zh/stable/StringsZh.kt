package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.布尔
import com.ohuang.kotlin_zh.整数
import com.ohuang.kotlin_zh.字符串

fun CharSequence?.为空或空串(): 布尔 = this.isNullOrEmpty()

fun CharSequence?.为空白或空(): 布尔 = this.isNullOrBlank()

fun CharSequence.非空白(): 布尔 = this.isNotBlank()

fun 字符串.包含(片段: CharSequence, 忽略大小写: 布尔 = false): 布尔 = contains(片段, 忽略大小写)

fun 字符串.替换全部(旧值: 字符串, 新值: 字符串, 忽略大小写: 布尔 = false): 字符串 =
    replace(oldValue = 旧值, newValue = 新值, ignoreCase = 忽略大小写)

fun 字符串.切分(分隔符: 字符串, 忽略大小写: 布尔 = false, 限制: 整数 = 0): 列表<字符串> =
    split(delimiters = arrayOf(分隔符), ignoreCase = 忽略大小写, limit = 限制)

fun 字符串.去空白(): 字符串 = trim()

fun 字符串.转小写(): 字符串 = lowercase()
