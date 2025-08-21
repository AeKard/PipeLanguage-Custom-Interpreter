
package frontend._lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.util.regex.Matcher;

public class Lexer {
    String src;
    long pos;

    public Lexer(String src){
        this.src = src;
        pos = 0;
    }

    public ArrayList<Token> Tokenizer(){
        ArrayList<Token> tokens = new ArrayList<>();
        
        List<TokenRule> rules = Arrays.asList(
            new TokenRule(TokenTypes.Identifier, "^[A-za-z_][A-za-z0-9_]*"),
            new TokenRule(TokenTypes.Number, "^[0-9]+"),
            new TokenRule(TokenTypes.BinaryOperator, "^(\\+|\\-|\\*|/)"),
            new TokenRule(TokenTypes.OpenParen, "^\\("),
            new TokenRule(TokenTypes.CloseParen, "^\\)"),
            new TokenRule(TokenTypes.Equals, "^=")
        );

        String input = src;

        while(!input.isEmpty()){
            boolean matched = false;
            for(TokenRule rule : rules){
                Matcher matcher = rule.pattern.matcher(input);
                if(matcher.lookingAt()){
                    String value = matcher.group();
                    tokens.add(new Token(value, rule.type));
                    input = input.substring(matcher.end());
                    matched = true;
                    break;
                }
            }

            if(!matched){
                // IGNORE SPACES FOR NOW OR SOMETHING UNKNOWS IGNORE
                input = input.substring(1);
            }
        }
        tokens.add(new Token("EOF", TokenTypes.EOF));
        return tokens;
    }
}

