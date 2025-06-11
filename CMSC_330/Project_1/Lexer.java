import java.io.*;
import java.util.*;

public class Lexer {

    private Scanner input;
    private Token token;
    private String identifier;
    private int number;
    private String string;

    public Lexer(File file) throws IOException, LexicalError {
        input = new Scanner(file);
        input.useDelimiter("");
        nextToken();
    }

    public Token getToken() {
        return token;
    }

    public void skipToken(Token expected) throws LexicalError {
        if (token != expected) {
            throw new LexicalError(0, "Expected token: " + expected + " but found: " + token);
        }
        nextToken();
    }

    public boolean checkToken(Token... options) {
        for (Token option : options) {
            if (token == option) return true;
        }
        return false;
    }

    public String getIdentifier() throws LexicalError {
        if (token != Token.IDENTIFIER) {
            throw new LexicalError(0, "Identifier expected.");
        }
        String result = identifier;
        nextToken();
        return result;
    }

    public int getNumber() throws LexicalError {
        if (token != Token.NUMBER) {
            System.out.println("DEBUG: Bad token when expecting number: " + token);
            throw new LexicalError(0, "Number expected.");
        }
        int result = number;
        nextToken();
        return result;
    }

    public String getString() throws LexicalError {
        if (token != Token.STRING) {
            throw new LexicalError(0, "String expected.");
        }
        String result = string;
        nextToken();
        return result;
    }

    private void nextToken() throws LexicalError {
        while (input.hasNext("\\s")) input.next(); // skip whitespace
        if (!input.hasNext()) {
            token = Token.EOF;
            System.out.println("DEBUG: token = EOF");
            return;
        }

        String next = input.next();
        System.out.println("DEBUG: next char = '" + next + "'");

        if (next.equals("(")) {
            token = Token.LEFT_PAREN;
        } else if (next.equals(")")) {
            token = Token.RIGHT_PAREN;
        } else if (next.equals(",")) {
            token = Token.COMMA;
        } else if (next.equals(";")) {
            token = Token.SEMICOLON;
        } else if (next.equals(".")) {
            token = Token.DOT;
        } else if (next.equals("\"")) {
            string = "";
            while (input.hasNext() && !input.hasNext("\"")) {
                string += input.next();
            }
            if (input.hasNext("\"")) input.next(); // consume closing quote
            token = Token.STRING;
        } else if (next.matches("[0-9]")) {
            StringBuilder numberBuilder = new StringBuilder(next);
            while (input.hasNext("[0-9]")) {
                numberBuilder.append(input.next());
            }
            number = Integer.parseInt(numberBuilder.toString());
            token = Token.NUMBER;
        } else if (next.matches("[A-Za-z]")) {
            StringBuilder ident = new StringBuilder(next);
            while (input.hasNext("[A-Za-z_]")) {
                ident.append(input.next());
            }
            identifier = ident.toString();
            token = keywordToToken(identifier);
        } else {
            System.out.println("DEBUG: Unrecognized input after token: '" + next + "'");
            throw new LexicalError(0, "Unexpected character: " + next);
        }

        System.out.println("DEBUG: token = " + token);
    }

    private Token keywordToToken(String word) {
        switch (word.toLowerCase()) {
            case "scene": return Token.SCENE;
            case "end": return Token.END;
            case "color": return Token.COLOR;
            case "at": return Token.AT;
            case "height": return Token.HEIGHT;
            case "width": return Token.WIDTH;
            case "offset": return Token.OFFSET;
            case "sides": return Token.SIDES;
            case "radius": return Token.RADIUS;
            case "righttriangle": return Token.RIGHT_TRIANGLE;
            case "rectangle": return Token.RECTANGLE_;
            case "isosceles": return Token.ISOSCELES;
            case "parallelogram": return Token.PARALLELOGRAM;
            case "regularpolygon": return Token.REGULAR_POLYGON;
            case "text": return Token.TEXT;
            default: return Token.IDENTIFIER;
        }
    }
}
