package com.eterocell.lsbasi.part4.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ParserTestCase {
  private fun makeParser(text: String): Parser {
    return Parser(Lexer(text))
  }

  @Test
  fun testExpression1() {
    val parser = makeParser("7")
    parser.parse()
  }

  @Test
  fun testExpression2() {
    val parser = makeParser("7 * 4 / 2")
    parser.parse()
  }

  @Test
  fun testExpression3() {
    val parser = makeParser("7 * 4 / 2 * 3")
    parser.parse()
  }

  @Test
  fun testExpression4() {
    val parser = makeParser("10 * 4  * 2 * 3 / 8")
    parser.parse()
  }

  @Test
  fun testExpressionInvalidSyntax() {
    val parser = makeParser("10 *")
    assertThrows<Exception> {
      parser.parse()
    }
  }
}