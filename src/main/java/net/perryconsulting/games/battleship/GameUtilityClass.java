package net.perryconsulting.games.battleship;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class GameUtilityClass {
    private GameUtilityClass() {
        throw new IllegalStateException("Utility class");
    }

    static final int GRID_SIZE = 10;
    static final Scanner INPUT = new Scanner(System.in);
    static final PrintWriter OUTPUT = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), StandardCharsets.US_ASCII), 512), true);
}
