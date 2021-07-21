package com.eterocell.lsbasi.part3


data class Token(
  /**
   * Token type: INTEGER, PLUS, MINUS or EOF
   */
  val type: Type,
  /**
   * Token value: non-negative integer value, '+', '-', or null
   */
  val value: String?
)