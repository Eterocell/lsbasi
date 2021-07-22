package com.eterocell.lsbasi.part4.parser

data class Token(
  /**
   * token type: INTEGER, MUL, DIV or EOF
   */
  val type: Type,
  /**
   * token value: non-negative integer value, '*', '/' or null
   */
  val value: String?
)
