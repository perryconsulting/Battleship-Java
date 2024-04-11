package net.perryconsulting.games.battleship;

import java.util.ArrayList;

import static net.perryconsulting.games.battleship.GameBoardUtilityClass.*;
import static net.perryconsulting.games.battleship.GameUtilityClass.INPUT;
import static net.perryconsulting.games.battleship.GameUtilityClass.OUTPUT;

public class Player {
    private final GameBoard ownBoard;
    private final GameBoard opponentBoard;
    private final ArrayList<Ship> ownShips;
    private final String playerName;

    public Player(String playerName, int gridSize) {
        this.ownBoard = new GameBoard(gridSize);
        this.opponentBoard = new GameBoard(gridSize);
        this.playerName = playerName;
        this.ownShips = new ArrayList<>();
    }

    public void readyPlayer() {
        OUTPUT.println(this.getOwnBoard());
        OUTPUT.println();
        this.placeOwnShips();
    }

    private void placeOwnShips() {
        for (ShipTypeEnum shipType : ShipTypeEnum.values()) {
            OUTPUT.println("Enter the coordinates of the " + shipType + ":");
            boolean validPlacement;
            do {
                validPlacement = this.tryPlaceShips(shipType);
            } while (!validPlacement);
        }
    }

    private boolean tryPlaceShips(ShipTypeEnum shipType) {
        final int row1;
        final int col1;
        final int row2;
        final int col2;

        String shipStart = INPUT.next().toUpperCase();
        String shipEnd = INPUT.next().toUpperCase();
        INPUT.nextLine(); // Consume hidden newline character

        try {
            row1 = this.getOwnBoard().getRow(shipStart);
            col1 = this.getOwnBoard().getColumn(shipStart);
            row2 = this.getOwnBoard().getRow(shipEnd);
            col2 = this.getOwnBoard().getColumn(shipEnd);

            if (validShipOrientation(row1, row2, col1, col2)) {
                if (validShipSize(row1, row2, col1, col2, shipType)) {
                    if (validNotAdjoiningShip(row1, row2, col1, col2, this.getOwnBoard(), GameBoard.SHIP_PLACE)) {
                        Ship newShip = new Ship(row1, row2, col1, col2, shipType);
                        placeShip(newShip, this.getOwnBoard());
                        this.addShip(newShip);
                        OUTPUT.println();
                        OUTPUT.println(this.getOwnBoard());
                        return true;
                    } else {
                        OUTPUT.println("Error! Ships cannot be next to one another. Try again: ");
                    }
                } else {
                    OUTPUT.println("Error! Wrong length of the " + shipType + ". Try again: ");
                }
            } else {
                OUTPUT.println("Error! Ships cannot be diagonal. Try again: ");
            }
        } catch (IndexOutOfBoundsException e) {
            OUTPUT.println("Error! Coordinates are outside of the game board.");
        } catch (NumberFormatException e) {
            OUTPUT.println("Error! Invalid coordinates.");
        }
        return false;
    }

    public void addShip(Ship newShip) {
        this.ownShips.add(newShip);
    }

    public char fireAShot() {
        boolean isValidShot = false;
        int row = 0;
        int col = 0;

        while(!isValidShot) {
            String shotCoordinates = INPUT.nextLine().toUpperCase();
            row = this.getOwnBoard().getRow(shotCoordinates);
            col = this.getOwnBoard().getColumn(shotCoordinates);
            if (validBoardLocation(this.getOwnBoard(), row, col)) {
                char shotResult = this.getOwnBoard().getCoordinate(row, col);
                if (shotResult == GameBoard.FOG_OF_WAR) {
                    this.getOwnBoard().setCoordinate(GameBoard.SHIP_MISSED, row, col);
                } else if (shotResult == GameBoard.SHIP_PLACE) {
                    Ship ship = this.identifyShipByLocation(row, col);
                    if (ship != null) {
                        ship.setShipRemainingLife(ship.getShipRemainingLife() - 1);
                    }
                    this.getOwnBoard().setCoordinate(GameBoard.SHIP_HIT, row, col);
                }
                isValidShot = true;
            } else {
                    OUTPUT.println("\nError! You entered the wrong coordinates! Try again:\n");
            }
        }
        return this.getOwnBoard().getCoordinate(row, col);
    }

    private Ship identifyShipByLocation(int row, int col) {
        for (Ship ship : this.ownShips) {
            boolean isRow = row >= ship.getRowMinimum() && row <= ship.getRowMaximum();
            boolean isColumn = col >= ship.getColMinimum() && col <= ship.getColMaximum();
            if (isRow && isColumn) {
                return ship;
            }
        }
        return null;
    }

    public GameBoard getOwnBoard() {
        return this.ownBoard;
    }

    public GameBoard getOpponentBoard() {
        return this.opponentBoard;
    }

    public String getPlayerName() {
        return this.playerName;
    }
}
