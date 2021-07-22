package com.eterocell.lsbasi.part4.calc4

class Lexer constructor(
  // client string input, e.g. "3 * 5", "12 / 3 * 4", etc.
  private var text: String
) {
  // this.pos is an index into this.text
  private var pos: Int = 0
  private var currentChar: Char? = text[pos]

  /**
   * Advance the 'pos' pointer and set the 'currentChar' variable
   */
  fun advance() {
    pos += 1
    currentChar = if (pos > text.length - 1) {
      null
    } else {
      text[pos]
    }
  }

  fun skipWhitespace() {
    while (currentChar != null && currentChar!!.isWhitespace()) {
      advance()
    }
  }

  /**
   * Return a (multi digit) integer consumed from the input
   */
  fun integer(): String {
    val stringBuilder = StringBuilder();
    while (currentChar != null && currentChar!!.isDigit()) {
      stringBuilder.append(currentChar)
      advance()
    }
    return stringBuilder.toString()
  }

  /**
   * Lexical analyzer (also known as scanner or tokenizer)
   *
   * This method is responsible for breaking a sentence apart
   * into tokens. One token at a time
   */
  fun getNextToken(): Token {
    while (currentChar != null) {

      if (currentChar!!.isWhitespace()) {
        skipWhitespace()
        continue
      }

      if (currentChar!!.isDigit()) {
        return Token(Type.INTEGER, integer())
      }

      if (currentChar == '*') {
        advance()
        return Token(Type.MUL, "*")
      }

      if (currentChar == '/') {
        advance()
        return Token(Type.DIV, "/")
      }

      throw Exception("Invalid character")
    }

    return Token(Type.EOF, null)
  }
}