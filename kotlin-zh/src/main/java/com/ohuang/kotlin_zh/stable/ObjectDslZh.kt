package com.ohuang.kotlin_zh.stable

import com.ohuang.kotlin_zh.任意值
import com.ohuang.kotlin_zh.可变映射
import com.ohuang.kotlin_zh.映射
import com.ohuang.kotlin_zh.字符串
import com.ohuang.kotlin_zh.无

/**
 * 方法体内提前 返回(值) 时抛出的信号，由 类实例.调用() 捕获。
 */
internal class 返回信号(val 值: 任意值?) : Throwable()

/**
 * 方法作用域：方法体 lambda 的接收者对象。
 *
 * 可用操作：
 *   取("属性名")       — 读取实例属性
 *   设("属性名", 值)   — 修改实例属性
 *   返回(值)           — 提前结束方法并返回指定值
 *   中断()             — 配合 捕获中断 退出内层 forEach 等 lambda
 *
 * 示例：
 *   方法("绝对值") {
 *       val n = 取<Int>("数值")
 *       如果(n >= 0) { 返回(n) }
 *       返回(-n)
 *   }
 */
class 方法作用域 internal constructor(private val 属性: 可变映射<字符串, 任意值?>) {

    @Suppress("UNCHECKED_CAST")
    fun <甲> 取(名称: 字符串): 甲 = 属性[名称] as 甲

    fun 设(名称: 字符串, 值: 任意值?) {
        属性[名称] = 值
    }
    
    @JvmName("设扩展")
    infix fun 字符串.设(值: 任意值?){
        设(this, 值)
    }



    fun 返回(值: 任意值? = null): Nothing = throw 返回信号(值)

    fun 中断(): Nothing = throw 中断信号()
}

/**
 * 类定义 DSL 构建器。通过 中文作用域 { 定义类 { ... } } 创建。
 *
 * 在 lambda 内可调用：
 *   属性("名称", 初始值)          — 声明一个属性
 *   方法("名称") { ... }          — 声明一个方法，接收者为 方法作用域
 *
 * 示例：
 *   val 计数器类 = 定义类 {
 *       属性("值", 0)
 *       方法("加一") { 设("值", 取<Int>("值") + 1) }
 *       方法("取值") { 返回(取<Int>("值")) }
 *   }
 *   val c = 计数器类.创建()
 *   c.调用("加一")
 *   c.调用("加一")
 *   println(c.调用("取值"))  // 2
 */
class 类定义 internal constructor() {
    internal val 属性定义: 可变映射<字符串, 任意值?> = mutableMapOf()
    internal val 方法定义: 可变映射<字符串, 方法作用域.() -> 任意值?> = mutableMapOf()

    fun 属性(名称: 字符串, 初始值: 任意值? = null) {
        属性定义[名称] = 初始值
    }

    fun 方法(名称: 字符串, 实现: 方法作用域.() -> 任意值?) {
        方法定义[名称] = 实现
    }

    fun 字符串.设置属性(初始值: 任意值? = null) {
        属性(this, 初始值)
    }

    fun 字符串.方法实现(实现: 方法作用域.() -> 任意值?) {
        方法(this, 实现)
    }
}

/**
 * 运行时类实例，由 中文作用域 内调用 类定义.调用类() 生成。
 *
 * 可用操作：
 *   取<T>("属性名")          — 读取属性
 *   设("属性名", 值)         — 修改属性
 *   调用("方法名")           — 执行方法，返回方法的返回值
 */
class 类实例 internal constructor(
    private val 属性: 可变映射<字符串, 任意值?>,
    private val 方法: 映射<字符串, 方法作用域.() -> 任意值?>
) {
    @Suppress("UNCHECKED_CAST")
    fun <甲> 取(名称: 字符串): 甲 = 属性[名称] as 甲

    fun 设(名称: 字符串, 值: 任意值?) {
        属性[名称] = 值
    }

    @JvmName("设扩展实例")
    infix fun 字符串.设(值: 任意值?){
        设(this, 值)
    }

    internal fun 调用(名称: 字符串): 任意值? {
        val 实现 = 方法[名称] ?: error("未定义方法：$名称")
        val 作用域 = 方法作用域(属性)
        return try {
            作用域.实现()
        } catch (信号: 返回信号) {
            信号.值
        }
    }
}




fun 定义类(初始化: 类定义.() -> Unit): 类定义 = 类定义().also(初始化)

fun 类定义.调用类(): 类实例 = 类实例(属性定义.toMutableMap(), 方法定义)

fun 类实例.调用方法(名称: 字符串): 任意值? = 调用(名称)





