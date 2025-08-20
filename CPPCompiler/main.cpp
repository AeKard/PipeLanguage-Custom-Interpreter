#include <iostream>
#include "./interface/lexer.h"

int main(){
    std::string source;
    std::cout << "Enter Expression:";std::getline(std::cin,source);
    Lexer lexer = Lexer(source);
    std::vector<Token> tokened = lexer.tokenize();

    for(auto& token: tokened){
        std::cout << "Value: " << token.value << " | Type: ";
        switch (token.type)
        {
        case TokenType::Identifier:
            std::cout << "Identifier";
            break;
        case TokenType::Equals:
            std::cout << "Equals";
            break;
        case TokenType::Number:
            std::cout << "Number";
            break;
        default:
            std::cout << "unknown";
            break;
        }
        std::cout << "\n";
    }
    std::cout << "=======\n" ;

    return 0;
}

