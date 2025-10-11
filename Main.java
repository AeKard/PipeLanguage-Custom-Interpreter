
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import runtime.values.BooleanVal;
import runtime.values.NullVal;
import runtime.Interpreter;
import runtime.Environement;

import frontend.ASTNodeTypes.Program;
import frontend._lexer.Lexer;
import frontend.Parser;

public class Main {

    private static void declareBuiltins(Environement env) {
        env.declareReserveKeyword("true", new BooleanVal(true), true);
        env.declareReserveKeyword("false", new BooleanVal(false), true);
        env.declareReserveKeyword("null", new NullVal(null), true);
    }

    private static void runSourceFile(String path) {
        System.out.println("=== Running Pipe Language ===");

        Environement env = new Environement(null);
        declareBuiltins(env);

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser();
            Interpreter interpreter = new Interpreter();

            System.out.println("\n---- TOKEN TYPES ----\n");
            var tokens = lexer.Tokenizer();

            System.out.println("\n---- AST Tree Representation ----\n");
            Program program = parser.produceAST(tokens);
            System.out.println(program);

            System.out.println("\n---- CLI Evaluation ----\n");
            interpreter.evaluate(program, env);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Interpreter Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }

    public static void main(String[] args) {
        String path = (args.length > 0) ? args[0] : "source.mpipe";
        runSourceFile(path);
    }
}
