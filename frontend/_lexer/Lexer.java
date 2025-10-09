
package frontend._lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String src;
    private final List<TokenRule> rules = Arrays.asList(
        new TokenRule(TokenTypes.Comment, "//[^\n]*"),
        new TokenRule(TokenTypes.Const, "\\bsealed\\b"),
        new TokenRule(TokenTypes.WhileStm, "\\bcycle\\b"),
        new TokenRule(TokenTypes.Fn, "\\bfaucet\\b"),
        new TokenRule(TokenTypes.ReturnStm, "\\bspill\\b"),
        new TokenRule(TokenTypes.ContinueStm, "\\bflush\\b"),
        new TokenRule(TokenTypes.BreakStm, "\\bclog\\b"),
        new TokenRule(TokenTypes.LogicOp, "\\b(and|or)\\b"),
        new TokenRule(TokenTypes.Let, "\\btap\\b"),
        new TokenRule(TokenTypes.IfStm, "\\bpipe\\b"),
        new TokenRule(TokenTypes.ElseIfStm, "\\bbranch\\b"),
        new TokenRule(TokenTypes.ElseStm, "\\bdrain\\b"),
        new TokenRule(TokenTypes.SemiColon, ";"),
        new TokenRule(TokenTypes.Print, "\\bflow\\b"),
        new TokenRule(TokenTypes.Number, "[0-9]+(\\.[0-9]+)?"),
        new TokenRule(TokenTypes.Identifier, "[A-Za-z_][A-Za-z0-9_]*"),
        new TokenRule(TokenTypes.Comma, ","),
        new TokenRule(TokenTypes.StringLiteral, "(\"[^\"]*\"|'[^']*')"),
        new TokenRule(TokenTypes.BinaryOperator, "(\\+|\\-|\\*|%|/)"),
        new TokenRule(TokenTypes.ComparisonOperator,"(!=|<=|>=|>|<|==)"),
        new TokenRule(TokenTypes.OpenParen, "\\("),
        new TokenRule(TokenTypes.CloseParen, "\\)"),
        new TokenRule(TokenTypes.Equals, "="),
        new TokenRule(TokenTypes.OpenBrace,"\\{"),
        new TokenRule(TokenTypes.CloseBrace,"\\}")
    );
    private final Pattern COMBINED_PATTERN = build_combined_pattern(rules);
    
    public Lexer(String src){
        this.src = src;
    }
    private static Pattern build_combined_pattern(List<TokenRule> rules){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rules.size(); i++){
            TokenRule rule = rules.get(i);
            if(i > 0) sb.append("|");
            sb.append("(?<").append(rule.type.toString()).append(">").append(rule.pattern.pattern()).append(")");
        }
        return Pattern.compile(sb.toString());
    }
    public ArrayList<Token> Tokenizer() {
        ArrayList<Token> tokens = new ArrayList<>();
        Matcher matcher = COMBINED_PATTERN.matcher(src);
        // Checks for valid tokens "add it into the token array "return token array""
        while (matcher.find()) {
            boolean isComment = false;

            for (TokenRule rule : rules) {
                String value = matcher.group(rule.type.toString());
                if (value != null) {

                    if (rule.type == TokenTypes.Comment) {
                        int nextLine = src.indexOf('\n', matcher.end());
                        if (nextLine == -1) {
                            return tokens;
                        }
                        matcher.region(nextLine + 1, src.length());
                        isComment = true;
                        break;
                    }
                    System.out.println(rule.type + "(" + value + ")");
                    tokens.add(new Token(value, rule.type));
                    break;
                }
            }

            if (isComment) continue;
        }  
        // EOF for end of line
        tokens.add(new Token("EOF", TokenTypes.EOF));
        return tokens;
    }   

}

