package com.eterocell.lsbasi.part3

class Interpreter constructor(private var text: String) {
  private var pos: Int = 0
  private var currentToken: Token? = null
  private var currentChar: Char? = text[pos]

  // **********************************
  // *           Lexer code           *
  // **********************************/
  /**
   * Advance the 'pos' pointer and set the 'currentChar' variable
   */
  fun advance() {
    this.pos += 1
    if (this.pos > text.length - 1) {
      this.currentChar = null
    } else {
      this.currentChar = this.text[this.pos]
    }
  }

  fun skipWhitespace() {
    while (this.currentChar != null && this.currentChar!!.isWhitespace()) {
      this.advance()
    }
  }

  /**
   * Return a (multi digit) integer consumed from the input.
   */
  fun integer(): String {
    val stringBuilder = StringBuilder()
    while (this.currentChar != null && this.currentChar!!.isDigit()) {
      stringBuilder.append(this.currentChar)
      this.advance()
    }
    return stringBuilder.toString()
  }

  /**
   * Lexical analyzer (also known as scanner or tokenizer)
   *
   * this method is responsible for breaking a sentence
   * apart into tokens. One token at a time
   */
  fun getNextToken(): Token {
    while (this.currentChar != null) {
      if (this.currentChar!!.isWhitespace()) {
        this.skipWhitespace()
        continue
      }

      if (this.currentChar!!.isDigit()) {
        return Token(Type.INTEGER, this.integer())
      }

      if (this.currentChar == '+') {
        this.advance()
        return Token(Type.PLUS, "+")
      }

      if (this.currentChar == '-') {
        this.advance()
        return Token(Type.MINUS, "-")
      }

      throw Exception("Error parsing input")
    }

    return Token(Type.EOF, null)
  }

  // **********************************
  // *   Parser / Interpreter code    *
  // **********************************/
  /**
   * compare the current token type with the passed token
   * type and if they match then "eat" the current token
   * and assign the next token to the this.currentToken,
   * otherwise throw an exception.
   */
  fun eat(tokenType: Type) {
    if (this.currentToken!!.type == tokenType) {
      this.currentToken = this.getNextToken()
    } else {
      throw Exception("Error parsing input")
    }
  }

  /**
   * Return an INTEGER token value.
   */
  fun term(): String? {
    val token = this.currentToken
    this.eat(Type.INTEGER)
    return token!!.value
  }

  /**
   * Arithmetic expression parser / interpreter.
   */
  fun expr(): Int {
    // set current token to the first token taken from the input
    this.currentToken = this.getNextToken()

    var result = this.term()!!.toInt()
    while (this.currentToken!!.type == Type.PLUS ||
      this.currentToken!!.type == Type.MINUS
    ) {
      val token = this.currentToken
      if (token!!.type == Type.PLUS) {
        this.eat(Type.PLUS)
        result += this.term()!!.toInt()
      } else if (token.type == Type.MINUS) {
        this.eat(Type.MINUS)
        result -= this.term()!!.toInt()
      }
    }
    return result
  }
}