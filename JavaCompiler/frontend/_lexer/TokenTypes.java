package frontend._lexer;

public enum TokenTypes{
    //Statements
    // Program,
    // VarDecleration,
    // FunctionDecleration,    
    // //Expression
    // AssignmentExpr,
    // MemberExpr,
    // CallExpr,

    //Literals Type
    Number, //
    Identifier, //

    //KeyWord
    Let,
    Const,
    Fn,

    //Grouping * Operator
    BinaryOperator, //
    Equals, //
    Dot,
    Colon,
    SemiColon,
    OpenParen, //
    CloseParen, //
    OpenBrace,//
    CloseBrace,//
    OpenBracket,//
    CloseBracket,//

    UNKNOWS,
    EOF,
};
