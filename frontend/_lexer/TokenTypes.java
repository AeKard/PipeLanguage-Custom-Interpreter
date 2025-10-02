package frontend._lexer;

public enum TokenTypes{
    Number, 
    StringLiteral,
    Identifier, 

    Let, // tap
    Const, // sealed
    Fn, // faucet
    ReturnStm, // spill
    Print, // flow
    IfStm, // pipe
    ElseIfStm, //branch
    ElseStm, //drain

    WhileStm, //cycle

    BinaryOperator,
    ComparisonOperator,
    NotEqual,
    EqualEqual,
    LessThan,
    LessEqual,
    GreaterThan,
    GreaterEqual,

    Equals, 

    Dot,
    Colon,
    SemiColon,
    Comma,
    OpenParen,
    CloseParen, 
    OpenBrace,
    CloseBrace,

    Comments,
    EOF,
};
