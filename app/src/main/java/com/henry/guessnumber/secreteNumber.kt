package com.henry.guessnumber

import java.util.*


class SecreteNumber{
    var secrete  = Random().nextInt(10)+1
    var count = 0
    fun validate(number: Int):Int{
        count++
        return number - secrete;
    }
    fun reset(){
        secrete  = Random().nextInt(10)+1
         count = 0
    }
}

fun main() {
    val secreteNumber = SecreteNumber()
    println(secreteNumber.secrete)
    println("${secreteNumber.validate(2)} , count:  ${secreteNumber.count}")
}