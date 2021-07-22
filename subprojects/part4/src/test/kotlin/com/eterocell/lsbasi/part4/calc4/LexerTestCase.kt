package com.eterocell.lsbasi.part4.calc4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LexerTestCase {
  private fun makeLexer(text: String): Lexer {
    return Lexer(text)
  }

  @Test
  fun testLexerInteger() {
    val lexer = makeLexer("234")
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.INTEGER)
    assertEquals(token.value!!.toInt(), 234)
  }

  @Test
  fun testLexerMul() {
    val lexer = makeLexer("*")
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.MUL)
    assertEquals(token.value, "*")
  }

  @Test
  fun testLexerDiv() {
    val lexer = makeLexer("/")
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.DIV)
    assertEquals(token.value, "/")
  }
}