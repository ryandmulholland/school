// Main.java
// Student: Ryan Mulholland
// Project 1 - CMSC 330
// Date: 2025-06-10
// Entry point for loading and rendering a scene

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String sceneFileName;
        File sceneFile;
        Scene scene;

        JFileChooser choice = new JFileChooser(new File("."));
        int option = choice.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            sceneFile = choice.getSelectedFile();
        } else {
            System.out.print("Enter scene file name or a single space to choose file from window: ");
            sceneFileName = stdin.nextLine();
            sceneFile = new File(sceneFileName);
        }

        try {
            Parser parser = new Parser(sceneFile);
            scene = parser.parseScene();
            scene.draw();
        } catch (SyntaxError e) {
            System.out.println("Syntax Error: " + e.getMessage());
        } catch (LexicalError e) {
            System.out.println("Lexical Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }
}
