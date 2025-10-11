package frontend._lexer;

public class Token{
    String value;
    TokenTypes type;
    int line = 0;
    public Token(String value, TokenTypes type, int line){
        this.value = value;
        this.type = type;
        this.line = line;
    }

    public TokenTypes getTokenType(){return type;}
    public String getValue(){return value;}
    public int getLine(){return line;}
    @Override
    public String toString(){
        return this.type + " (\'"+this.value+"\') : "+line+"\n";
    }
};