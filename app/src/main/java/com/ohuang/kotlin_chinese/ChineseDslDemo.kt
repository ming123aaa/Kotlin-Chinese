package com.ohuang.kotlin_chinese

import com.ohuang.kotlin_zh.stable.与
import com.ohuang.kotlin_zh.stable.为真时
import com.ohuang.kotlin_zh.stable.切分
import com.ohuang.kotlin_zh.stable.列出
import com.ohuang.kotlin_zh.stable.包含元素
import com.ohuang.kotlin_zh.stable.去空白
import com.ohuang.kotlin_zh.stable.可变列出
import com.ohuang.kotlin_zh.stable.可变集
import com.ohuang.kotlin_zh.stable.失败时
import com.ohuang.kotlin_zh.stable.如果
import com.ohuang.kotlin_zh.stable.存在
import com.ohuang.kotlin_zh.stable.定义类
import com.ohuang.kotlin_zh.stable.尝试
import com.ohuang.kotlin_zh.stable.当
import com.ohuang.kotlin_zh.stable.成功时
import com.ohuang.kotlin_zh.stable.或
import com.ohuang.kotlin_zh.stable.打印
import com.ohuang.kotlin_zh.stable.打印行
import com.ohuang.kotlin_zh.stable.数量
import com.ohuang.kotlin_zh.stable.映射
import com.ohuang.kotlin_zh.stable.是否为空
import com.ohuang.kotlin_zh.stable.构建字符串
import com.ohuang.kotlin_zh.stable.添加
import com.ohuang.kotlin_zh.stable.添加全部
import com.ohuang.kotlin_zh.stable.清空
import com.ohuang.kotlin_zh.stable.移除
import com.ohuang.kotlin_zh.stable.管道
import com.ohuang.kotlin_zh.stable.调用方法
import com.ohuang.kotlin_zh.stable.调用类
import com.ohuang.kotlin_zh.stable.转小写
import com.ohuang.kotlin_zh.stable.转文本
import com.ohuang.kotlin_zh.stable.过滤
import com.ohuang.kotlin_zh.stable.连接为文本
import com.ohuang.kotlin_zh.stable.追加行
import com.ohuang.kotlin_zh.stable.重复
import com.ohuang.kotlin_zh.stable.重试
import com.ohuang.kotlin_zh.stable.集
import com.ohuang.kotlin_zh.stable.非空
import com.ohuang.kotlin_zh.stable.非空白
import com.ohuang.kotlin_zh.列表
import com.ohuang.kotlin_zh.字符串
import com.ohuang.kotlin_zh.整数


fun 狗类()=定义类{
    属性("年龄",1)

    方法("狗叫"){
        重复(取<整数>("年龄")) { 索引 ->
            打印("汪")
        }
    }

}

fun 示例(): 列表<字符串> {
    val 原始数据 = 列出(" kotlin ", "java", "")
    val 清理后 = 原始数据
        .过滤 { it.非空白() }
        .映射 { it.去空白() }

    (清理后.非空())
        .为真时 { 打印行("有效条目: ${清理后.数量}") }

    如果(清理后.数量 > 1) {
        打印行("命中 if 分支")
    } 否则 {
        打印行("命中 else 分支")
    }

    return "Kotlin 中文 DSL".管道 { it.转小写() }.切分(" ")
}

fun 集合示例() {
    // 不可变集合 - 自动去重
    val 唯一数字 = 集(1, 2, 3, 2, 1, 4, 3)
    打印行("唯一数字: $唯一数字")  // 输出: [1, 2, 3, 4]
    
    // 可变集合
    val 标签 = 可变集("Kotlin", "Android")
    标签.添加("Java")
    标签.添加("Kotlin")  // 重复，不会添加
    打印行("标签: $标签")  // 输出: [Kotlin, Android, Java]
    
    // 集合查询
    打印行("包含Kotlin: ${标签.包含元素("Kotlin")}")
    打印行("存在长度>5: ${标签.存在 { it.length > 5 }}")
    
    // 集合运算
    val 数字集 = 集(1, 2, 3, 4, 5)
    val 偶数集 = 集(2, 4, 6, 8)
    val 交集 = 数字集.与(偶数集)
    打印行("交集: $交集")  // 输出: [2, 4]
    
    val 并集 = 数字集.或(集(5, 6, 7))
    打印行("并集: $并集")  // 输出: [1, 2, 3, 4, 5, 6, 7]
    
    // 可变集合操作
    val 可变标签 = 可变集("A", "B", "C")
    可变标签.移除("B")
    打印行("移除后: $可变标签")  // 输出: [A, C]
    
    可变标签.添加全部(集("D", "E"))
    打印行("批量添加后: $可变标签")  // 输出: [A, C, D, E]
    
    可变标签.清空()
    打印行("清空后是否为空: ${可变标签.是否为空}")  // 输出: true
}

fun 说明文本(): 字符串 {
    val 列表结果 = 示例()
    val 分支结果 =
        当(列表结果.数量) { 大小 -> if (大小 > 2) "分支: 多条结果" else "分支: 结果较少" }
    val 类示例结果 = 中文类示例()

    val 重试结果 = 尝试 {
        重试(次数 = 3) { "重试成功" }
    }

    val 日志 = 可变列出<字符串>()
    重试结果
        .成功时 { 日志 += "结果: $it" }
        .失败时 { 日志 += "失败: ${it.message}" }

    return 构建字符串 {
        追加行("示例输出: ${列表结果.连接为文本()}")
        追加行(分支结果)
        追加行("中文类示例: $类示例结果")
        追加行(日志.连接为文本())
    }
}

fun 中文类示例(): 字符串 {
    var 输出: 字符串 = ""

    val 角色类 = 定义类 {
        "名字".设置属性("阿离")
        "能量".设置属性(1)
        "升级".方法实现 {
            val 当前能量 = 取<整数>("能量")
            设("能量", 当前能量 + 1)
            返回(取<整数>("能量"))
        }
        "介绍".方法实现 {
            返回("${取<字符串>("名字")} 能量=${取<整数>("能量")}")
        }
    }
    val 角色 = 角色类.调用类()
    角色.调用方法("升级")
    角色.调用方法("升级")
    输出 = 角色.调用方法("介绍").转文本()

    return 输出
}


