package com.eterocell.lsbasi.part6

class Interpreter constructor(private val lexer: Lexer) {
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
   * factor : INTEGER | LPAREN expr RPAREN
   */
  fun factor(): Int {
    val token = currentToken
    if (token.type == Type.INTEGER) {
      eat(Type.INTEGER)
      return token.value!!.toInt()
    } else if (token.type == Type.LPAREN) {
      eat(Type.LPAREN)
      val result = expr()
      eat(Type.RPAREN)
      return result
    }
    throw Exception("Invalid syntax")
  }

  /**
   * term : factor ((MUL | DIV) factor)*
   */
  fun term(): Int {
    var result = factor()!!.toInt()

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

  /**
   * Arithmetic expression parser / interpreter
   *
   * calc> 7 + 3 * (10 / (12 / (3 + 1) - 1))
   * 22
   *
   * expr    : term ((PLUS | MINUS) term)*
   * term    : factor ((MUL | DIV) factor)*
   * factor  : INTEGER | LPAREN expr RPAREN
   */
  fun expr(): Int {
    var result = term()

    while (currentToken.type == Type.PLUS ||
      currentToken.type == Type.MINUS
    ) {
      val token = currentToken
      if (token.type == Type.PLUS) {
        eat(Type.PLUS)
        result += term()
      } else if (token.type == Type.MINUS) {
        eat(Type.MINUS)
        result -= term()
      }
    }

    return result
  }
}