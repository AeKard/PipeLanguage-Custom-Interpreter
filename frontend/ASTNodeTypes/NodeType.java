package frontend.ASTNodeTypes;

public enum NodeType{
    Program, // stms

    WhileStm, //Stms
    BreakStm,
    ContinueStm,

    If, //Stms
    Print, // Stms
    Block, // Stms
    AssignmentExpr, // Stms
    ReturnStm, // Stms
    
    NumericalLiteral, //expression
    Identifier, //expression
    BinaryExpr, // expression
    VarDecleration, //expression
    StringLiteral, //expression
    FunctionCall,
    FunctionDec,

};


