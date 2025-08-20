#include "lexer.h"
#include <iostream>
Lexer :: Lexer(const std::string &src){
    this->source = src;
    this->pos = 0;
}

std::vector<Token> Lexer :: tokenize(){
    std::vector<Token> tokens;

    std::vector<TokenRule> rules ={
        {TokenType::Identifier, std::regex("^[A-Za-z_][A-Za-z0-9_]*")},
        {TokenType::Number, std::regex("^[0-9]+")},
        {TokenType::Equals, std::regex("^(=|\\+|\\-|\\*|/)")}
    };
    
    std::string input = source;

    while (!input.empty())
    {
        bool matched = false;

        for(auto& rule : rules){
            std::smatch match;
            if(std::regex_search(input, match, rule.pattern)){
                tokens.push_back({match.str(), rule.type});
                input = input.substr(match.length());
                matched = true;
                break;
            }
        }

        if(!matched){
            // tokens.push_back({std::string(1, input[0]), TokenType::Unknown});
            input = input.substr(1);
        }
    }
    return tokens;
};