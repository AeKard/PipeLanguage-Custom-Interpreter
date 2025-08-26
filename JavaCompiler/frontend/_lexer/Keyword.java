package frontend._lexer;

import java.util.Map;
public class Keyword {
    public static final Map<String, TokenTypes> KEYWORDS = Map.of(
        "let", TokenTypes.Let,
        "fn", TokenTypes.Fn,
        "const", TokenTypes.Const
    );
}
