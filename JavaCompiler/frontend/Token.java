package frontend;

public class Token{
    String value;
    TokenTypes type;

    public Token(String value, TokenTypes type){
        this.value = value;
        this.type = type;
    }

    public TokenTypes getTokenType(){return type;}
    public String getValue(){return value;}
};