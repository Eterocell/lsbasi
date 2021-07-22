package com.eterocell.lsbasi.part4.calc4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InterpreterTestCase {
  private fun makeInterpreter(text: String): Interpreter {
    return Interpreter(Lexer(text))
  }

  @Test
  fun testExpression1() {
    val interpreter = makeInterpreter("7 * 4 / 2")
    val result = interpreter.expr()
    assertEquals(result, 14)
  }

  @Test
  fun testExpression2() {
    val interpreter = makeInterpreter("7 * 4 / 2 * 3")
    val result = interpreter.expr()
    assertEquals(result, 42)
  }

  @Test
  fun testExpression3() {
    val interpreter = makeInterpreter("10 * 4  * 2 * 3 / 8")
    val result = interpreter.expr()
    assertEquals(result, 30)
  }
}