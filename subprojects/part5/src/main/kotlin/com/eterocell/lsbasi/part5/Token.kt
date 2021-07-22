package com.eterocell.lsbasi.part5

data class Token(
  /**
   * token type: INTEGER, PLUS, MINUS, MUL, DIV or EOF
   */
  val type: Type,
  /**
   * token value: non-negative integer value, '+', '-', '*', '/' or null
   */
  val value: String?
)
