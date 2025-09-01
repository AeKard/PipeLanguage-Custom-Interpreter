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
    StringLiteral,
    Identifier, //

    //KeyWord
    Let,
    Const,
    Fn,
    Print,
    //Grouping * Operator
    BinaryOperator, //
    Equals, //
    Dot,
    Colon,
    SemiColon,
    Comma,
    OpenParen, //
    CloseParen, //
    // OpenBrace,//
    // CloseBrace,//
    // OpenBracket,//
    // CloseBracket,//

    UNKNOWS,
    EOF,
};
