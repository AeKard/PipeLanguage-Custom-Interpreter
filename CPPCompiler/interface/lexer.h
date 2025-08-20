#ifndef LEXER_H
#define LEXER_H

#include <string>
#include <vector>
#include <regex>

enum class TokenType {
    Identifier,
    Number,
    Equals,
    OpenParen,
    CloseParen,
    BinaryOperator,
    Let,

    Unknown
};

struct Token {
    std::string value;
    TokenType type;
};

struct TokenRule {
    TokenType type;
    std::regex pattern;
};

class Lexer {
private:
    std::string source;
    size_t pos;

public:
    Lexer(const std::string &src);
    std::vector<Token> tokenize();
};

#endif 
