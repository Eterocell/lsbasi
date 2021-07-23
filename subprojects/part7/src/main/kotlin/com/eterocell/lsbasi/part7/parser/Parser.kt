package com.eterocell.lsbasi.part7.parser

import com.eterocell.lsbasi.part7.components.Token
import com.eterocell.lsbasi.part7.components.Type
import com.eterocell.lsbasi.part7.lexer.Lexer

class Parser(private val lexer: Lexer) {
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
  fun factor(): AST {
    val token = currentToken
    return when (token.type) {
      Type.INTEGER -> {
        eat(Type.INTEGER)
        Num(token)
      }
      Type.LPAREN -> {
        eat(Type.LPAREN)
        val node = expr()
        eat(Type.RPAREN)
        node
      }
      else -> {
        throw Exception("Invalid syntax")
      }
    }
  }

  /**
   * term : factor ((MUL | DIV) factor)*
   */
  fun term(): AST {
    var node = factor()
    var token: Token

    while (currentToken.type == Type.MUL ||
      currentToken.type == Type.DIV
    ) {
      token = currentToken
      when (token.type) {
        Type.MUL -> {
          eat(Type.MUL)
        }
        Type.DIV -> {
          eat(Type.DIV)
        }
        else -> {
          throw Exception("Invalid syntax")
        }
      }
      node = BinOp(node, token, factor())
    }

    return node
  }

  /**
   * expr    : term ((PLUS | MINUS) term)*
   * term    : factor ((MUL | DIV) factor)*
   * factor  : INTEGER | LPAREN expr RPAREN
   */
  fun expr(): AST {
    var node = term()

    while (currentToken.type == Type.PLUS ||
      currentToken.type == Type.MINUS
    ) {
      val token = currentToken
      when (token.type) {
        Type.PLUS -> {
          eat(Type.PLUS)
        }
        Type.MINUS -> {
          eat(Type.MINUS)
        }
        else -> {
          throw Exception("Invalid syntax")
        }
      }

      node = BinOp(node, token, term())
    }

    return node
  }

  fun parse(): AST {
    return expr()
  }
}