package com.eterocell.lsbasi.part7

import com.eterocell.lsbasi.part7.interpreter.Interpreter
import com.eterocell.lsbasi.part7.lexer.Lexer
import com.eterocell.lsbasi.part7.parser.Parser
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
    val parser = Parser(lexer)
    val interpreter = Interpreter(parser)
    val result = interpreter.interpret()
    println(result)
  }
}