package com.eterocell.lsbasi.part2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CalcTestCase {

  private fun makeInterpreter(text: String): Interpreter {
    return Interpreter(text)
  }

  @Test
  fun testLexerInteger() {
    val lexer = makeInterpreter("234")
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.INTEGER)
    assertEquals(token.value!!.toInt(), 234)
  }

  @Test
  fun testLexerPlus() {
    val lexer = makeInterpreter("+")
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.PLUS)
    assertEquals(token.value, "+")
  }

  @Test
  fun testLexerMinus() {
    val lexer = makeInterpreter("-")
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.MINUS)
    assertEquals(token.value, "-")
  }

  @Test
  fun testLexerEof() {
    val lexer = makeInterpreter("-")
    lexer.getNextToken()
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.EOF)
  }

  @Test
  fun testLexerWhitespace() {
    val lexer = makeInterpreter("  23")
    val token = lexer.getNextToken()
    assertEquals(token.type, Type.INTEGER)
    assertEquals(token.value, "23")
  }

  @Test
  fun testLexerAddition() {
    val lexer = makeInterpreter("2+3")

    var token = lexer.getNextToken()
    assertEquals(token.type, Type.INTEGER)
    assertEquals(token.value, "2")

    token = lexer.getNextToken()
    assertEquals(token.type, Type.PLUS)
    assertEquals(token.value, "+")

    token = lexer.getNextToken()
    assertEquals(token.type, Type.INTEGER)
    assertEquals(token.value, "3")

    token = lexer.getNextToken()
    assertEquals(token.type, Type.EOF)
  }

  @Test
  fun testLexerSubtraction() {
    val lexer = makeInterpreter(" 27   -  7  ")

    var token = lexer.getNextToken()
    assertEquals(token.type, Type.INTEGER)
    assertEquals(token.value, "27")

    token = lexer.getNextToken()
    assertEquals(token.type, Type.MINUS)
    assertEquals(token.value, "-")

    token = lexer.getNextToken()
    assertEquals(token.type, Type.INTEGER)
    assertEquals(token.value, "7")

    token = lexer.getNextToken()
    assertEquals(token.type, Type.EOF)
  }

  @Test
  fun testInterpreterAddition() {
    val interpreter = makeInterpreter(" 23 + 7")
    val result = interpreter.expr()
    assertEquals(result, 30)
  }

  @Test
  fun testInterpreterSubtraction() {
    val interpreter = makeInterpreter(" 27   -  7  ")
    val result = interpreter.expr()
    assertEquals(result, 20)
  }
}