package com.eterocell.lsbasi.part7.interpreter

import com.eterocell.lsbasi.part7.lexer.Lexer
import com.eterocell.lsbasi.part7.parser.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class InterpreterTestCase {
  private fun makeInterpreter(text: String): Interpreter {
    return Interpreter(Parser(Lexer(text)))
  }

  @Test
  fun testExpression1() {
    val interpreter = makeInterpreter("3")
    val result = interpreter.interpret()
    assertEquals(result, 3)
  }

  @Test
  fun testExpression2() {
    val interpreter = makeInterpreter("2 + 7 * 4")
    val result = interpreter.interpret()
    assertEquals(result, 30)
  }

  @Test
  fun testExpression3() {
    val interpreter = makeInterpreter("7 - 8 / 4")
    val result = interpreter.interpret()
    assertEquals(result, 5)
  }

  @Test
  fun testExpression4() {
    val interpreter = makeInterpreter("14 + 2 * 3 - 6 / 2")
    val result = interpreter.interpret()
    assertEquals(result, 17)
  }

  @Test
  fun testExpression5() {
    val interpreter = makeInterpreter("7 + 3 * (10 / (12 / (3 + 1) - 1))")
    val result = interpreter.interpret()
    assertEquals(result, 22)
  }

  @Test
  fun testExpression6() {
    val interpreter = makeInterpreter(
      "7 + 3 * (10 / (12 / (3 + 1) - 1)) / (2 + 3) - 5 - 3 + (8)"
    )
    val result = interpreter.interpret()
    assertEquals(result, 10)
  }

  @Test
  fun testExpression7() {
    val interpreter = makeInterpreter("7 + (((3 + 2)))")
    val result = interpreter.interpret()
    assertEquals(result, 12)
  }

  @Test
  fun testExpressionInvalidSyntax() {
    val interpreter = makeInterpreter("10 *")
    assertThrows<Exception> {
      interpreter.interpret()
    }
  }
}