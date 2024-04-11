package net.perryconsulting.games.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final int GRID_SIZE = 10;
    static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        shallWePlayAGame();
        System.exit(0);
    }

    private static void shallWePlayAGame() {
        List<Player> players = preparePlayers();
        startGame(players);
    }

    private static List<Player> preparePlayers() {
        Player playerOne = new Player("Bud", GRID_SIZE);
        Player playerTwo = new Player("Lou", GRID_SIZE);
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        // players.add(playerTwo);
        for (Player player : players) {
            player.readyPlayer();
        }
        return players;
    }

    private static void startGame(List<Player> players) {
        System.out.println("The game starts!\n");
        Player currentPlayer = players.get(0);
        boolean isGameOver = false;
        while (!isGameOver) {
            isGameOver = playerTurn(currentPlayer);
        }
    }

    private static boolean playerTurn(Player currentPlayer) {
        boolean didPlayerWin = true;
        System.out.println(currentPlayer.getOwnBoard());
        System.out.println("Take a shot!\n");
        char shotResult = currentPlayer.fireAShot();
        System.out.println(currentPlayer.getOwnBoard());
        if (shotResult == GameBoard.SHIP_HIT) {
            System.out.println("\nYou hit a ship!\n");
        } else if (shotResult == GameBoard.SHIP_MISSED) {
            System.out.println("\nYou missed!\n");
        }
        return didPlayerWin;
    }
}
