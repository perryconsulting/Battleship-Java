package net.perryconsulting.games.battleship;

import java.util.Arrays;

public class GameBoard {
    public static final char FOG_OF_WAR = '~';
    public static final char SHIP_PLACE = 'O';
    public static final char SHIP_HIT = 'X';
    public static final char SHIP_MISSED = 'M';
    private final char[][] board;

    public GameBoard(int gridSize) {
        this.board = new char[gridSize][gridSize];
        for(int i = 0; i < gridSize; i++) {
            Arrays.fill(this.board[i], FOG_OF_WAR);
        }
    }

    public int getRow(String coordinate) {
        return coordinate.charAt(0) - 65;
    }

    public int getColumn(String coordinate) {
        return Integer.parseInt(coordinate.substring(1)) - 1;
    }

    public char getCoordinate(int row, int col) {
        return this.board[row][col];
    }

    public int getBoardArrayLength() {
        return this.board.length;
    }

    public void setShipInPlace(int row, int col, char ownShip) {
        this.board[row][col] = ownShip;
    }

    public void setCoordinate(char shotResult, int row, int col) {
        this.board[row][col] = shotResult;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for(int i = 0; i < this.board.length; i++) {
            sb.append(" ").append(i + 1);
        }
        sb.append("\n");
        for(int i = 0; i < this.board.length; i++) {
            sb.append((char)('A' + i));
            for(int j = 0; j < this.board.length; j++) {
                sb.append(" ").append(this.board[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
