package net.perryconsulting.games.battleship;

import java.util.ArrayList;
import java.util.List;

import static net.perryconsulting.games.battleship.GameUtilityClass.GRID_SIZE;
import static net.perryconsulting.games.battleship.GameUtilityClass.OUTPUT;

public class Main {

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
        OUTPUT.println("The game starts!\n");
        Player currentPlayer = players.getFirst();
        boolean isGameOver = false;
        while (!isGameOver) {
            isGameOver = playerTurn(currentPlayer);
        }
    }

    private static boolean playerTurn(Player currentPlayer) {
        boolean didPlayerWin = true;
        OUTPUT.println(currentPlayer.getOwnBoard());
        OUTPUT.println("Take a shot!\n");
        char shotResult = currentPlayer.fireAShot();
        OUTPUT.println(currentPlayer.getOwnBoard());
        if (shotResult == GameBoard.SHIP_HIT) {
            OUTPUT.println("\nYou hit a ship!\n");
        } else if (shotResult == GameBoard.SHIP_MISSED) {
            OUTPUT.println("\nYou missed!\n");
        }
        return didPlayerWin;
    }
}
