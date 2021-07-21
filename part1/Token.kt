package com.eterocell.lsbasi.part1

data class Token(
  /**
   * Token type: INTEGER, PLUS or EOF
   */
  val type: Type,
  /**
   * Token value: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '+' or null
   */
  val value: Char?
)