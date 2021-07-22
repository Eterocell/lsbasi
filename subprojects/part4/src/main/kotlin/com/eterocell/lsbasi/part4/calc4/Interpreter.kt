package com.eterocell.lsbasi.part4.calc4

class Interpreter constructor(private val lexer: Lexer) {
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
   * Return an INTEGER token value
   *
   * factor : INTEGER
   */
  fun factor(): Int {
    val token = currentToken
    eat(Type.INTEGER)
    return token.value!!.toInt()
  }

  /**
   * Arithmetic expression parser / interpreter
   *
   * expr   : factor ((MUL | DIV) factor)*
   * factor : INTEGER
   */
  fun expr(): Int {
    var result = factor()

    while (currentToken.type == Type.MUL ||
      currentToken.type == Type.DIV
    ) {
      val token = currentToken
      if (token.type == Type.MUL) {
        eat(Type.MUL)
        result *= factor()
      } else if (token.type == Type.DIV) {
        eat(Type.DIV)
        result /= factor()
      }
    }

    return result
  }
}