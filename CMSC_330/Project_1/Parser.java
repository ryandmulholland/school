import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class Parser {
    private Lexer lexer;
    private Scene scene;

    public Parser(File file) throws IOException, LexicalError {
        lexer = new Lexer(file);
    }

    public Scene parseScene() throws IOException, LexicalError, SyntaxError {
        lexer.skipToken(Token.SCENE);
        String name = lexer.getIdentifier();
        int[] size = parseNumberList();
        scene = new Scene(name, size[1], size[0]);
        parseImages();
        lexer.skipToken(Token.END);
        lexer.skipToken(Token.DOT);
        return scene;
    }

    private void parseImages() throws IOException, LexicalError, SyntaxError {
        while (lexer.checkToken(
            Token.RIGHT_TRIANGLE, Token.RECTANGLE_,
            Token.ISOSCELES, Token.PARALLELOGRAM,
            Token.REGULAR_POLYGON, Token.TEXT)) {
            parseImage();
        }
    }

    private void parseImage() throws IOException, LexicalError, SyntaxError {
        if (lexer.checkToken(Token.RIGHT_TRIANGLE)) {
            lexer.skipToken(Token.RIGHT_TRIANGLE);
            Color color = parseColor();
            int[] pos = parsePoint();
            lexer.skipToken(Token.HEIGHT);
            int height = lexer.getNumber();
            lexer.skipToken(Token.WIDTH);
            int width = lexer.getNumber();
            lexer.skipToken(Token.SEMICOLON);
            scene.addImage(new RightTriangle(color, new Point(pos[0], pos[1]), height, width));

        } else if (lexer.checkToken(Token.RECTANGLE_)) {
            lexer.skipToken(Token.RECTANGLE_);
            Color color = parseColor();
            int[] pos = parsePoint();
            lexer.skipToken(Token.HEIGHT);
            int height = lexer.getNumber();
            lexer.skipToken(Token.WIDTH);
            int width = lexer.getNumber();
            lexer.skipToken(Token.SEMICOLON);
            scene.addImage(new Rectangle(color, new Point(pos[0], pos[1]), height, width));

        } else if (lexer.checkToken(Token.ISOSCELES)) {
            lexer.skipToken(Token.ISOSCELES);
            int[] rgb = parseRGB();
            int[] pos = parsePoint();
            lexer.skipToken(Token.HEIGHT);
            int height = lexer.getNumber();
            lexer.skipToken(Token.WIDTH);
            int width = lexer.getNumber();
            lexer.skipToken(Token.SEMICOLON);
            scene.addImage(new IsoscelesTriangle(rgb, pos, height, width));

        } else if (lexer.checkToken(Token.PARALLELOGRAM)) {
            lexer.skipToken(Token.PARALLELOGRAM);
            int[] rgb = parseRGB();
            lexer.skipToken(Token.AT);
            int[] upperLeft = parseNumberList();
            int[] lowerRight = parseNumberList();
            lexer.skipToken(Token.OFFSET);
            int offset = lexer.getNumber();
            lexer.skipToken(Token.SEMICOLON);
            scene.addImage(new Parallelogram(rgb, upperLeft, lowerRight, offset));

        } else if (lexer.checkToken(Token.REGULAR_POLYGON)) {
            lexer.skipToken(Token.REGULAR_POLYGON);
            int[] rgb = parseRGB();
            int[] center = parsePoint();
            lexer.skipToken(Token.SIDES);
            int sides = lexer.getNumber();
            lexer.skipToken(Token.RADIUS);
            int radius = lexer.getNumber();
            lexer.skipToken(Token.SEMICOLON);
            scene.addImage(new RegularPolygon(rgb, center, sides, radius));

        } else if (lexer.checkToken(Token.TEXT)) {
            lexer.skipToken(Token.TEXT);
            int[] rgb = parseRGB();
            int[] pos = parsePoint();
            String str = lexer.getString();
            lexer.skipToken(Token.SEMICOLON);
            scene.addImage(new Text(rgb, pos, str));

        } else {
            throw new SyntaxError(0, "Unexpected image type.");
        }
    }

    private Color parseColor() throws IOException, LexicalError {
        lexer.skipToken(Token.COLOR);
        int[] rgb = parseNumberList();
        return new Color(rgb[0], rgb[1], rgb[2]);
    }

    private int[] parseRGB() throws IOException, LexicalError {
        lexer.skipToken(Token.COLOR);
        return parseNumberList();
    }

    private int[] parsePoint() throws IOException, LexicalError {
        lexer.skipToken(Token.AT);
        return parseNumberList();
    }

    private int[] parseNumberList() throws IOException, LexicalError {
        lexer.skipToken(Token.LEFT_PAREN);
        List<Integer> nums = new ArrayList<>();
        nums.add(lexer.getNumber());
        while (lexer.checkToken(Token.COMMA)) {
            lexer.skipToken(Token.COMMA);
            nums.add(lexer.getNumber());
        }
        lexer.skipToken(Token.RIGHT_PAREN);
        return nums.stream().mapToInt(i -> i).toArray();
    }
}
