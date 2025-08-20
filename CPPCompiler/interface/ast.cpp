#ifndef AST_H
#define AST_H

#include <string>

enum class NodeType{
    Program,
    NumericalLiterals,
    BinaryExpr,
    identifier
};

class Stms{
    NodeType kind;

};

class Program : private Stms{

};
#endif