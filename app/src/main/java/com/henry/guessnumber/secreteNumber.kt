package com.henry.guessnumber

import java.util.*


class SecreteNumber{
    val secrete  = Random().nextInt(10)+1
    var count = 0
    fun validate(number: Int):Int{
        count++
        return number - secrete;
    }

}

fun main() {
    val secreteNumber = SecreteNumber()
    println(secreteNumber.secrete)
    println("${secreteNumber.validate(2)} , count:  ${secreteNumber.count}")
}