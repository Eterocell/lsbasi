package com.eterocell.lsbasi.part5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class InterpreterTestCase {
  private fun makeInterpreter(text: String): Interpreter {
    return Interpreter(Lexer(text))
  }

  @Test
  fun testExpression1() {
    val interpreter = makeInterpreter("3")
    val result = interpreter.expr()
    assertEquals(result, 3)
  }

  @Test
  fun testExpression2() {
    val interpreter = makeInterpreter("2 + 7 * 4")
    val result = interpreter.expr()
    assertEquals(result, 30)
  }

  @Test
  fun testExpression3() {
    val interpreter = makeInterpreter("7 - 8 / 4")
    val result = interpreter.expr()
    assertEquals(result, 5)
  }

  @Test
  fun testExpression4() {
    val interpreter = makeInterpreter("14 + 2 * 3 - 6 / 2")
    val result = interpreter.expr()
    assertEquals(result, 17)
  }

  @Test
  fun testExpressionInvalidSyntax() {
    val interpreter = makeInterpreter("10 *")
    assertThrows<Exception> {
      interpreter.expr()
    }
  }
}