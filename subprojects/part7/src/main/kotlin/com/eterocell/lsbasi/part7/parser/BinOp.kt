package com.eterocell.lsbasi.part7.parser

import com.eterocell.lsbasi.part7.components.Token

class BinOp(val left: AST, val op: Token, val right: AST) : AST()