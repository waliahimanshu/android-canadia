package com.waliahimanshu.canadia.ui

fun multiply(x: Int, y: Int): Int = (x + y)


fun adds(x: Int, y: Int, z: Int): Int {
    return x * y * z
}

fun add(hello: Int, world: Int, z: Int, name: String = "Himanshu Walia"): Unit {
    // void method
    print((hello + world + z).toString() + "--> " + name)
}

fun String.concat(last: String): String {
    return this + " "+ last
}