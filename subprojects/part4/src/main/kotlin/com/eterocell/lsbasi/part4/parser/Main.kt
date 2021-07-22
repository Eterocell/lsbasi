package com.eterocell.lsbasi.part4.parser

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
    val parser = Parser(Lexer(text))
    parser.parse()
  }
}