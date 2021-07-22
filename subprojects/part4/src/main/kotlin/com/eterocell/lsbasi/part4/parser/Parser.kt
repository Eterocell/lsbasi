package com.eterocell.lsbasi.part4.parser

class Parser(private val lexer: Lexer) {
  // set current token to the first token taken from the input
  private var currentToken = lexer.getNextToken()

  /**
   * compare the current token type with the passed token
   * type and if they match then "eat" the current token
   * and assign the next token to the this.currentToken,
   * otherwise throw an exception.
   */
  fun eat(tokenType: Type) {
    if (currentToken.type == tokenType) {
      currentToken = lexer.getNextToken()
    } else {
      throw Exception("Invalid syntax")
    }
  }

  /**
   * Parse integer.
   *
   * factor : INTEGER
   */
  fun factor() {
    eat(Type.INTEGER)
  }

  /**
   * Arithmetic expression parser
   *
   * Grammar:
   *
   * expr   : factor ((MUL | DIV) factor)*
   * factor : INTEGER
   */
  fun expr() {
    factor()

    while (currentToken.type == Type.MUL ||
      currentToken.type == Type.DIV
    ) {
      val token = currentToken
      if (token.type == Type.MUL) {
        eat(Type.MUL)
        factor()
      } else {
        eat(Type.DIV)
        factor()
      }
    }
  }

  fun parse() {
    expr()
  }
}