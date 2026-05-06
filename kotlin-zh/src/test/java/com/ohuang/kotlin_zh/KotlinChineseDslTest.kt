package com.ohuang.kotlin_zh

import com.ohuang.kotlin_zh.stable.失败时
import com.ohuang.kotlin_zh.stable.尝试
import com.ohuang.kotlin_zh.stable.当
import com.ohuang.kotlin_zh.stable.如果
import com.ohuang.kotlin_zh.stable.管道
import com.ohuang.kotlin_zh.stable.成功时
import com.ohuang.kotlin_zh.stable.重试
import com.ohuang.kotlin_zh.stable.为假时
import com.ohuang.kotlin_zh.stable.数量
import com.ohuang.kotlin_zh.stable.条目数
import com.ohuang.kotlin_zh.stable.循环
import com.ohuang.kotlin_zh.stable.重复
import com.ohuang.kotlin_zh.stable.打印行
import com.ohuang.kotlin_zh.stable.列出
import com.ohuang.kotlin_zh.stable.包含值
import com.ohuang.kotlin_zh.stable.包含键
import com.ohuang.kotlin_zh.stable.可变列出
import com.ohuang.kotlin_zh.stable.集
import com.ohuang.kotlin_zh.stable.可变集
import com.ohuang.kotlin_zh.stable.包含元素
import com.ohuang.kotlin_zh.stable.不包含元素
import com.ohuang.kotlin_zh.stable.存在
import com.ohuang.kotlin_zh.stable.所有满足
import com.ohuang.kotlin_zh.stable.都不满足
import com.ohuang.kotlin_zh.stable.与
import com.ohuang.kotlin_zh.stable.或
import com.ohuang.kotlin_zh.stable.差集
import com.ohuang.kotlin_zh.stable.添加
import com.ohuang.kotlin_zh.stable.移除
import com.ohuang.kotlin_zh.stable.添加全部
import com.ohuang.kotlin_zh.stable.移除全部
import com.ohuang.kotlin_zh.stable.保留全部
import com.ohuang.kotlin_zh.stable.清空
import com.ohuang.kotlin_zh.stable.是否为空
import com.ohuang.kotlin_zh.stable.是否非空
import com.ohuang.kotlin_zh.stable.为空
import com.ohuang.kotlin_zh.stable.可变映射构建
import com.ohuang.kotlin_zh.stable.构建字符串
import com.ohuang.kotlin_zh.stable.中断
import com.ohuang.kotlin_zh.stable.捕获中断
import com.ohuang.kotlin_zh.stable.定义类
import com.ohuang.kotlin_zh.stable.调用方法
import com.ohuang.kotlin_zh.stable.调用类
import com.ohuang.kotlin_zh.stable.返回
import com.ohuang.kotlin_zh.stable.放入
import com.ohuang.kotlin_zh.stable.映射键变换
import com.ohuang.kotlin_zh.stable.映射值变换
import com.ohuang.kotlin_zh.stable.映射构建
import com.ohuang.kotlin_zh.stable.逐项
import com.ohuang.kotlin_zh.stable.转小写
import com.ohuang.kotlin_zh.stable.转文本
import com.ohuang.kotlin_zh.stable.为真时
import com.ohuang.kotlin_zh.stable.到值
import com.ohuang.kotlin_zh.stable.合并
import com.ohuang.kotlin_zh.stable.连接为文本
import com.ohuang.kotlin_zh.stable.追加行
import com.ohuang.kotlin_zh.stable.切分
import com.ohuang.kotlin_zh.stable.去空白
import com.ohuang.kotlin_zh.stable.包含
import com.ohuang.kotlin_zh.stable.按值排序
import com.ohuang.kotlin_zh.stable.按键排序
import com.ohuang.kotlin_zh.stable.取值
import com.ohuang.kotlin_zh.stable.取值或计算
import com.ohuang.kotlin_zh.stable.取值或默认
import com.ohuang.kotlin_zh.stable.移除键
import com.ohuang.kotlin_zh.stable.非空
import com.ohuang.kotlin_zh.stable.非空映射
import com.ohuang.kotlin_zh.stable.非空白
import com.ohuang.kotlin_zh.stable.筛选项
import com.ohuang.kotlin_zh.stable.排序
import com.ohuang.kotlin_zh.stable.映射
import com.ohuang.kotlin_zh.stable.若无则放入
import com.ohuang.kotlin_zh.stable.值列表
import com.ohuang.kotlin_zh.stable.键列表
import com.ohuang.kotlin_zh.stable.为空时
import com.ohuang.kotlin_zh.stable.非空时
import com.ohuang.kotlin_zh.stable.过滤
import com.ohuang.kotlin_zh.stable.若为空
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KotlinChineseDslTest {
    @Test
    fun `类型别名可用`() {
        val 文本: 字符串 = "你好"
        val 数值: 整数 = 7
        val 开关: 布尔 = true

        assertEquals("你好", 文本)
        assertEquals(7, 数值)
        assertTrue(开关)
    }

    @Test
    fun `集合扩展行为正确`() {
        val 数据 = listOf(3, 1, 2, 4)
        val 结果 = 数据
            .过滤 { it % 2 == 0 }
            .映射 { it * 10 }
            .排序()

        assertEquals(listOf(20, 40), 结果)
    }

    @Test
    fun `字符串扩展行为正确`() {
        val 文本 = "  Kotlin DSL  "

        assertTrue(文本.包含("dsl", 忽略大小写 = true))
        assertTrue(文本.非空白())
        assertEquals("Kotlin DSL", 文本.去空白())
        assertEquals("kotlin dsl", 文本.去空白().转小写())
        assertEquals(listOf("Kotlin", "DSL"), 文本.去空白().切分(" "))
    }

    @Test
    fun `基础中文内建扩展可用`() {
        val 列表 = listOf("a", "b")
        assertTrue(列表.非空())
        assertEquals(2, 列表.数量)

        // 只验证 API 可调用，不校验控制台输出内容。
        打印行("中文打印测试")
    }

    @Test
    fun `中文构造器与文本构建可用`() {
        val 列表 = 列出("甲", "乙", "丙")
        val 可变 = 可变列出(1, 2)
        可变 += 3

        assertEquals(listOf("甲", "乙", "丙"), 列表)
        assertEquals(listOf(1, 2, 3), 可变)
        assertEquals("甲 | 乙 | 丙", 列表.连接为文本(" | "))

        val 文本 = 构建字符串 {
            追加行("第一行")
            追加行("第二行")
        }
        assertTrue(文本.startsWith("第一行"))
        assertTrue(123.转文本().isNotEmpty())
    }

    @Test
    fun `中文集合构造器可用`() {
        // 测试不可变集合
        val 测试集合 = 集(1, 2, 3, 2, 1)  // 重复元素会被去重
        assertEquals(setOf(1, 2, 3), 测试集合)
        assertEquals(3, 测试集合.size)
        assertTrue(2 in 测试集合)

        // 测试可变集合
        val 测试可变集合 = 可变集("a", "b")
        测试可变集合.add("c")
        测试可变集合.add("a")  // 重复元素不会被添加
        assertEquals(mutableSetOf("a", "b", "c"), 测试可变集合)
        assertEquals(3, 测试可变集合.size)
        assertTrue("b" in 测试可变集合)
        
        // 测试空集合
        val 空测试集合 = 集<Int>()
        assertTrue(空测试集合.isEmpty())
        
        val 空测试可变集合 = 可变集<String>()
        assertTrue(空测试可变集合.isEmpty())
    }

    @Test
    fun `中文集合DSL方法可用`() {
        // 测试不可变集合的查询方法
        val 数字集 = 集(1, 2, 3, 4, 5)
        
        assertTrue(数字集.包含元素(3))
        assertFalse(数字集.包含元素(10))
        assertTrue(数字集.不包含元素(10))
        
        // 测试条件判断
        assertTrue(数字集.存在 { it > 4 })
        assertFalse(数字集.所有满足 { it > 3 })
        assertTrue(数字集.都不满足 { it > 10 })
        
        // 测试集合运算
        val 偶数集 = 集(2, 4, 6, 8)
        val 交集结果 = 数字集.与(偶数集)
        assertEquals(集(2, 4), 交集结果)
        
        val 并集结果 = 数字集.或(集(5, 6, 7))
        assertEquals(集(1, 2, 3, 4, 5, 6, 7), 并集结果)
        
        val 差集结果 = 数字集.差集(集(3, 4, 5))
        assertEquals(集(1, 2), 差集结果)
        
        // 测试为空属性
        assertTrue(集<Int>().为空)
        assertFalse(数字集.为空)
    }

    @Test
    fun `中文可变集合DSL方法可用`() {
        // 测试可变集合的修改方法
        val 可变集 = 可变集(1, 2, 3)
        
        // 添加元素
        assertTrue(可变集.添加(4))
        assertEquals(可变集(1, 2, 3, 4), 可变集)
        
        // 添加已存在的元素
        assertFalse(可变集.添加(2))
        assertEquals(4, 可变集.size)
        
        // 移除元素
        assertTrue(可变集.移除(1))
        assertEquals(可变集(2, 3, 4), 可变集)
        
        // 移除不存在的元素
        assertFalse(可变集.移除(10))
        
        // 批量操作
        val 新元素 = 集(5, 6)
        可变集.添加全部(新元素)
        assertEquals(5, 可变集.size)  // {2, 3, 4, 5, 6}
        
        可变集.移除全部(集(2, 3))
        assertEquals(可变集(4, 5, 6), 可变集)
        
        可变集.保留全部(集(5, 6))
        assertEquals(可变集(5, 6), 可变集)
        
        // 清空
        可变集.清空()
        assertTrue(可变集.是否为空)
        assertFalse(可变集.是否非空)
        
        // 重新添加测试非空
        可变集.添加(1)
        assertFalse(可变集.是否为空)
        assertTrue(可变集.是否非空)
    }

    @Test
    fun `Map 中文 DSL 可用`() {
        val 基础映射 = 映射构建(
            "姓名" 到值 "阿离",
            "等级" 到值 "7"
        )
        assertTrue(基础映射.非空映射())
        assertEquals(2, 基础映射.条目数)
        assertTrue(基础映射.包含键("姓名"))
        assertTrue(基础映射.包含值("7"))
        assertEquals("阿离", 基础映射.取值("姓名"))

        val 可变 = 可变映射构建("a" 到值 1)
        可变.放入("b", 2)
        可变.移除键("a")
        assertEquals(1, 可变.条目数)
        assertEquals(2, 可变.取值("b"))

        val 键列表结果 = 基础映射.键列表().排序()
        val 值列表结果 = 基础映射.值列表().排序()
        assertEquals(listOf("姓名", "等级").sorted(), 键列表结果)
        assertEquals(listOf("7", "阿离").sorted(), 值列表结果)

        val 转换后 = 基础映射.映射值变换 { 键, 值 -> "$键=$值" }
        assertEquals("姓名=阿离", 转换后["姓名"])
        assertEquals("等级=7", 转换后["等级"])
    }

    @Test
    fun `Map 高级中文 DSL 可用`() {
        val 原映射 = 映射构建("b" 到值 2, "a" 到值 1, "c" 到值 3)

        val 累加结果 = StringBuilder()
        原映射.逐项 { 键, 值 ->
            累加结果.append("$键:$值;")
        }
        assertTrue(累加结果.toString().contains("a:1"))

        val 筛选后 = 原映射.筛选项 { _, 值 -> 值 >= 2 }
        assertEquals(2, 筛选后.条目数)
        assertFalse(筛选后.包含键("a"))

        val 按键结果 = 原映射.按键排序().键列表()
        assertEquals(listOf("a", "b", "c"), 按键结果)

        val 按值结果 = 原映射.按值排序().值列表()
        assertEquals(listOf(1, 2, 3), 按值结果)

        val 键变换后 = 原映射.映射键变换 { 键, _ -> "键_$键" }
        assertTrue(键变换后.包含键("键_a"))
        assertEquals(1, 键变换后.取值("键_a"))

        val 合并后 = 原映射 合并 映射构建("d" 到值 4)
        assertEquals(4, 合并后.条目数)
        assertEquals(4, 合并后.取值("d"))

        assertEquals(2, 原映射.取值或默认("b", 0))
        assertEquals(9, 原映射.取值或默认("x", 9))
        assertEquals(3, 原映射.取值或计算("c") { 100 })
        assertEquals(100, 原映射.取值或计算("x") { 100 })

        val 缓存 = 可变映射构建("k" 到值 1)
        assertEquals(1, 缓存.若无则放入("k") { 9 })
        assertEquals(2, 缓存.若无则放入("n") { 2 })
        assertEquals(2, 缓存.取值("n"))
    }

    @Test
    fun `空值和布尔扩展行为正确`() {
        var 非空触发 = 0
        var 为空触发 = 0
        var 真触发 = 0
        var 假触发 = 0

        val 名称: String? = "kotlin"
        val 空名称: String? = null

        名称.非空时 { 非空触发++ }
        空名称.为空时 { 为空触发++ }
        true.为真时 { 真触发++ }
        false.为假时 { 假触发++ }

        assertEquals(1, 非空触发)
        assertEquals(1, 为空触发)
        assertEquals(1, 真触发)
        assertEquals(1, 假触发)
        assertEquals("默认", 空名称.若为空(默认值 = { "默认" }, 变换 = { it }))
    }

    @Test
    fun `DSL 管道与条件可用`() {
        var 命中Else = false

        val 结果 = " Kotlin ".管道 { it.trim() }
        如果(结果.length < 3) {
            // no-op
        } 否则 {
            命中Else = true
        }

        assertEquals("Kotlin", 结果)
        assertTrue(命中Else)
        assertFalse(结果.isBlank())
    }

    @Test
    fun `控制与结果 DSL 可用`() {
        var 尝试次数 = 0
        var 成功值 = ""
        var 失败触发 = false

        val 分支结果 = 当(2) { if (it > 1) "大于一" else "不大于一" }
        val 结果 = 尝试 {
            重试(次数 = 3) {
                尝试次数++
                if (尝试次数 < 3) error("暂时失败")
                "完成"
            }
        }

        结果
            .成功时 { 成功值 = it }
            .失败时 { 失败触发 = true }

        assertEquals("大于一", 分支结果)
        assertEquals(3, 尝试次数)
        assertEquals("完成", 成功值)
        assertFalse(失败触发)
    }

    @Test
    fun `循环 DSL 行为正确`() {
        var 计数 = 0
        循环({ 计数 < 5 }) { 计数++ }
        assertEquals(5, 计数)
    }

    @Test
    fun `重复 DSL 行为正确`() {
        val 记录 = mutableListOf<Int>()
        重复(4) { i -> 记录 += i }
        assertEquals(listOf(0, 1, 2, 3), 记录)
    }

    @Test
    fun `捕获中断模拟 break 行为`() {
        val 收集 = mutableListOf<Int>()
        捕获中断 {
            listOf(1, 2, 3, 4, 5).forEach { item ->
                if (item == 3) 中断()
                收集 += item
            }
        }
        assertEquals(listOf(1, 2), 收集)
    }

    @Test
    fun `返回辅助函数行为正确`() {
        val 值 = run { 返回(42) }
        assertEquals(42, 值)
    }

    @Test
    fun `定义类基本属性与方法`() {
        val 狗类 = 定义类 {
            属性("名字", "旺财")
            方法("叫") { 返回("${取<String>("名字")}：汪汪！") }
        }
        val 狗 = 狗类.调用类()
        assertEquals("旺财：汪汪！", 狗.调用方法("叫"))
    }

    @Test
    fun `定义类可修改属性`() {
        val 计数器类 = 定义类 {
            属性("值", 0)
            方法("加一") { 设("值", 取<Int>("值") + 1) }
            方法("取值") { 返回(取<Int>("值")) }
        }
        val c = 计数器类.调用类()
        c.调用方法("加一")
        c.调用方法("加一")
        assertEquals(2, c.调用方法("取值"))
    }

    @Test
    fun `方法作用域返回提前退出`() {
        val 绝对值类 = 定义类 {
            属性("数值", 0)
            方法("绝对值") {
                val n = 取<Int>("数值")
                如果(n >= 0) { 返回(n) }
                返回(-n)
            }
        }
        val 正 = 绝对值类.调用类()
        正.设("数值", 5)
        assertEquals(5, 正.调用方法("绝对值"))

        val 负 = 绝对值类.调用类()
        负.设("数值", -3)
        assertEquals(3, 负.调用方法("绝对值"))
    }

    @Test
    fun `方法作用域内捕获中断`() {
        val 查找类 = 定义类 {
            属性("列表", listOf(1, 2, 3, 4, 5))
            方法("找第一个偶数") {
                var 结果: Int? = null
                捕获中断 {
                    取<List<Int>>("列表").forEach { n ->
                        if (n % 2 == 0) {
                            结果 = n
                            中断()
                        }
                    }
                }
                返回(结果)
            }
        }
        val 实例 = 查找类.调用类()
        assertEquals(2, 实例.调用方法("找第一个偶数"))
    }
}
