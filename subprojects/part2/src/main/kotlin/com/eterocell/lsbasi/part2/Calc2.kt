package com.eterocell.lsbasi.part2

import java.io.EOFException

fun main() {
  while (true) {
    var text: String?
    try {
      print("calc> ")
      text = readLine()!!
    } catch (e: EOFException) {
      break
    }
    val interpreter = Interpreter(text)
    val result = interpreter.expr()
    println(result)
  }
}