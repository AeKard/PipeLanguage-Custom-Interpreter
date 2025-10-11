
package frontend._lexer;

import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private ArrayList<String> src;
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

    public Lexer(BufferedReader src){
        this.src = build_String(src);
    }
    
    public ArrayList<Token> Tokenizer() {
        ArrayList<Token> tokens = new ArrayList<>();
        int line = 1;
        for (String currentLine : src) {
            Matcher matcher = COMBINED_PATTERN.matcher(currentLine);
            while (matcher.find()) {
                boolean isComment = false;
                for (TokenRule rule : rules) {
                    String value = matcher.group(rule.type.toString());
                    if (value != null) {
                        if (rule.type == TokenTypes.Comment) {
                            isComment = true;
                            break;
                        }

                        System.out.println("Line :" + line +" | "+ rule.type + "(" + value + ")");
                        tokens.add(new Token(value, rule.type, line));
                        break;
                    }
                }
                if (isComment) break;
            }
            line++;
        }

        // EOF token
        tokens.add(new Token("EOF", TokenTypes.EOF, line));
        return tokens;
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
    private static ArrayList<String> build_String(BufferedReader src){
        ArrayList<String> stringLines = new ArrayList<>();
        String tmp;
        try {
            while((tmp = src.readLine()) != null){
                stringLines.add(tmp);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return stringLines;
    }   
}

