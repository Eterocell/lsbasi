package com.eterocell.lsbasi.part7.interpreter

import com.eterocell.lsbasi.part7.components.Type
import com.eterocell.lsbasi.part7.parser.AST
import com.eterocell.lsbasi.part7.parser.BinOp
import com.eterocell.lsbasi.part7.parser.Num
import com.eterocell.lsbasi.part7.parser.Parser

class Interpreter constructor(private val parser: Parser) {

  fun visit(node: AST): Int {
    when (node) {
      is Num -> {
        return visitNum(node)
      }
      is BinOp -> {
        return visitBinOp(node)
      }
    }
    throw IllegalArgumentException("Invalid syntax")
  }

  fun visitNum(node: Num): Int {
    when (node.token.type) {
      Type.INTEGER -> {
        return node.token.value!!.toInt()
      }
      else -> {
        throw IllegalArgumentException("Invalid syntax")
      }
    }
  }

  fun visitBinOp(node: BinOp): Int {
    val leftVal = visit(node.left)
    val rightVal = visit(node.right)

    when (node.op.type) {
      Type.PLUS -> {
        return leftVal + rightVal
      }
      Type.MINUS -> {
        return leftVal - rightVal
      }
      Type.MUL -> {
        return leftVal * rightVal
      }
      Type.DIV -> {
        return leftVal / rightVal
      }
      else -> {
        throw Exception("Invalid syntax")
      }
    }
  }

  fun interpret(): Int {
    val tree = parser.parse()
    return visit(tree)
  }
}