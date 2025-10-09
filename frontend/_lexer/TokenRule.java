package frontend._lexer;
import java.util.regex.Pattern;

public class TokenRule {
    TokenTypes type;
    Pattern pattern;

    public TokenRule(TokenTypes type, String regex){
        this.type = type;
        this.pattern = Pattern.compile(regex);
    }
    @Override
    public String toString(){
        return this.type + "("+this.pattern+")";
    }
}
