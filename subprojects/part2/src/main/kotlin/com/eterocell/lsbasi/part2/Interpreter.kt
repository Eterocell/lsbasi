package com.eterocell.lsbasi.part2

class Interpreter constructor(private var text: String) {
  private var pos: Int = 0
  private var currentToken: Token? = null
  private var currentChar: Char? = text[pos]

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
   * Parser / Interpreter
   *
   * expr -> INTEGER PLUS INTEGER
   * expr -> INTEGER MINUS INTEGER
   */
  fun expr(): Int {
    // set current token to the first token taken from the input
    this.currentToken = this.getNextToken()

    // we expect the current token to be a single-digit integer
    val left = this.currentToken
    this.eat(Type.INTEGER)

    // we expect the current token to be either a '+' or '-'
    val op = this.currentToken
    if (op!!.type == Type.PLUS) {
      this.eat(Type.PLUS)
    } else {
      this.eat(Type.MINUS)
    }

    // we expect the current token to be an integer
    val right = this.currentToken
    this.eat(Type.INTEGER)

    // after the above call the this.currentToken is set to
    // EOF token

    // at this point either the INTEGER PLUS INTEGER or
    // the INTEGER MINUS INTEGER sequence of tokens
    // has been successfully found and the method can just
    // return the result of adding or subtracting two integers,
    // thus effectively interpreting client input
    return if (op.type == Type.PLUS) {
      left!!.value!!.toInt() + right!!.value!!.toInt()
    } else {
      left!!.value!!.toInt() - right!!.value!!.toInt()
    }
  }
}