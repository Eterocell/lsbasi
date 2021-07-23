package com.eterocell.lsbasi.part7.parser

import com.eterocell.lsbasi.part7.components.Token

class Num(val token: Token) : AST() {
  val value = token.value
}