package com.eterocell.lsbasi.part1

class Interpreter constructor(private var text: String) {
  private var pos: Int = 0
  private var currentToken: Token? = null

  /**
   * Lexical analyzer (also known as scanner or tokenizer)
   *
   * this method is responsible for breaking a sentence
   * apart into tokens. One token at a time
   */
  private fun getNextToken(): Token {
    val text = this.text

    // is this.pos index past the end of the this.text?
    // if so, then return EOF token because there is no more
    // input left to convert into tokens
    if (this.pos > text.length - 1) {
      return Token(Type.EOF, null)
    }

    // get a character at the position this.pos and decide
    // what token to create based on the single character
    val currentChar = text[this.pos]

    // if the character is a digit then convert it to integer.
    // create an INTEGER token, increment this.pos index
    // to point to the next character after the digits,
    // and return the INTEGER token
    if (currentChar.isDigit()) {
      val token = Token(Type.INTEGER, currentChar)
      this.pos += 1
      return token
    }

    if (currentChar == '+') {
      val token = Token(Type.PLUS, currentChar)
      this.pos += 1
      return token
    }

    throw Exception("Error parsing input")
  }

  /**
   * compare the current token type with the passed token
   * type and if they match then "eat" the current token
   * and assign the next token to the this.currentToken,
   * otherwise throw an exception.
   */
  private fun eat(tokenType: Type) {
    if (this.currentToken!!.type == tokenType) {
      this.currentToken = this.getNextToken()
    } else {
      throw Exception("Error parsing input")
    }
  }

  /**
   * expr -> INTEGER PLUS INTEGER
   */
  fun expr(): Int {
    // set current token to the first token taken from the input
    this.currentToken = this.getNextToken()

    // we expect the current token to be a single-digit integer
    val left = this.currentToken
    this.eat(Type.INTEGER)

    // we expect the current token to be a '+' token
    val op = this.currentToken
    this.eat(Type.PLUS)

    // we expect the current token to be a single-digit integer
    val right = this.currentToken
    this.eat(Type.INTEGER)

    // after the above call the this.currentToken is set to
    // EOF token

    // at this point INTEGER PLUS INTEGER sequence of tokens
    // has been successfully found and the method can just
    // return the result of adding two integers, thus
    // effectively interpreting client input
    return left!!.value.toString().toInt() + right!!.value.toString().toInt()
  }
}