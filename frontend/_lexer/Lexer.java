
package frontend._lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: FIX my lexer use Stream for fixing boiler plater
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
            new TokenRule(TokenTypes.Const, "^\\bsealed\\b"),
            new TokenRule(TokenTypes.Let, "^\\btap\\b"),
            new TokenRule(TokenTypes.IfStm, "^\\bpipe\\b"),
            new TokenRule(TokenTypes.ElseIf, "^\\bbranch\\b"),
            new TokenRule(TokenTypes.ElseStm, "^\\bdrain\\b"),
            new TokenRule(TokenTypes.SemiColon, "^;"),
            new TokenRule(TokenTypes.Print, "^\\bflow\\b"),
            new TokenRule(TokenTypes.Identifier, "^[A-Za-z_][A-Za-z0-9_]*"),
            new TokenRule(TokenTypes.Comma, "^,"),
            new TokenRule(TokenTypes.StringLiteral, "^\"[^\"]*\""),
            new TokenRule(TokenTypes.StringLiteral, "^'[^']*'"),
            new TokenRule(TokenTypes.Number, "^[0-9]+(\\.[0-9]+)?"),
            new TokenRule(TokenTypes.BinaryOperator, "^(\\+|\\-|\\*|%|/)"),
            new TokenRule(TokenTypes.ComparisonOperator,"^(!=|<=|>=|>|<|==)"),
            new TokenRule(TokenTypes.OpenParen, "^\\("),
            new TokenRule(TokenTypes.CloseParen, "^\\)"),
            new TokenRule(TokenTypes.Equals, "^="),
            new TokenRule(TokenTypes.OpenBrace,"^\\{"),
            new TokenRule(TokenTypes.CloseBrace,"^\\}")
            // new TokenRule(TokenTypes.OpenBracket,"^\\["),
            // new TokenRule(TokenTypes.CloseBracket,"^\\]"),
        );
        Pattern ws = Pattern.compile("^\\s");
        String input = src;

        while(!input.isEmpty()){
            boolean matched = false;
            for(TokenRule rule : rules){
                if(ws.matcher(input).lookingAt()){break;}
                Matcher matcher = rule.pattern.matcher(input);
                // System.out.println(matcher + " : " + input);
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

