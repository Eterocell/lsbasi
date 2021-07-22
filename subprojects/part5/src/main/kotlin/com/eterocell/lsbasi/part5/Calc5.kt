package com.eterocell.lsbasi.part5

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
    val lexer = Lexer(text)
    val interpreter = Interpreter(lexer)
    val result = interpreter.expr()
    println(result)
  }
}