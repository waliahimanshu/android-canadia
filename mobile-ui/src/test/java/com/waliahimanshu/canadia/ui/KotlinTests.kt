package com.waliahimanshu.canadia.ui

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class KotlinTests {

    @Test
    fun print() {
        // single line comment
        /**
         * multi line
         */
        print("Hello world")
    }

    @Test
    fun type() {
        val name = "Himanshu Walia"
        print(name)
    }

    @Test
    fun function() {
        println(add(5, 2))
        println(multiply(8, 4))

        //name func param
        add(hello = 2, world = 4, z = 5, name = "ha")
    }

    @Test
    fun extensionFun() {
        val first = "Himanshu"
        print(first.concat("Walia"))
    }

    fun subtract(x: Int, y: Int): Int = (x + y)


}

/**
 * a function is first class citizen, and can exist outside method.
 */
fun add(x: Int, y: Int): Int {
    return x + y
}

/**
 * by default classes are final, and can not be inherited
 * use open to extent it.
 */
open class X {

}


class Y : X() {

}