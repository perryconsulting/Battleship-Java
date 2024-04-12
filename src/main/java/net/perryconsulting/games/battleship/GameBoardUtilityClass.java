package net.perryconsulting.games.battleship;

import java.util.ArrayList;

import static net.perryconsulting.games.battleship.GameUtilityClass.OUTPUT;

class GameBoardUtilityClass {
    private GameBoardUtilityClass() {
        throw new IllegalStateException("Utility Class");
    }

    public static void placeShip(Ship ship, GameBoard gameBoard) {
        for (int row = ship.getRowMinimum(); row <= ship.getRowMaximum(); row++) {
            for (int col = ship.getColMinimum(); col <= ship.getColMaximum(); col++) {
                gameBoard.setShipInPlace(row, col, GameBoard.SHIP_PLACE);
            }
        }
    }

    public static boolean validShipOrientation(int row1, int row2, int col1, int col2) {
        return row1 == row2 || col1 == col2;
    }

    public static boolean validShipSize(int row1, int row2, int col1, int col2, ShipTypeEnum shiptype) {
        boolean checkHorizontalPlacement = row1 == row2 && Math.abs(col1 - col2) == shiptype.getShipLength() - 1;
        boolean checkVerticalPlacement = col1 == col2 && Math.abs(row1 - row2) == shiptype.getShipLength() - 1;
        return checkVerticalPlacement || checkHorizontalPlacement;
    }

    public static boolean validNotAdjoiningShip(int row1, int row2, int col1, int col2, GameBoard ownBoard, char ownShip) {
        for(int row = Math.min(row1, row2) - 1; row <= Math.max(row1, row2) + 1; row++) {
            for(int col = Math.min(col1, col2) - 1; col <= Math.max(col1, col2) + 1; col++) {
                if (validBoardLocation(ownBoard, row, col) && ownBoard.getCoordinate(row, col) == ownShip) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validBoardLocation(GameBoard ownBoard, int row, int col) {
        return row >= 0 && row < ownBoard.getBoardArrayLength() && col >= 0 && col < ownBoard.getBoardArrayLength();
    }

    // Early testing method marked for removal
    public static void listShipCoordinates(GameBoard gameBoard) {
        ArrayList<String> shipCoordinates = new ArrayList<>();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; ++col) {
                if (gameBoard.getCoordinate(row, col) == GameBoard.SHIP_PLACE) {
                    shipCoordinates.add((char) (row + 65) + String.valueOf(col + 1));
                }
            }
        }
        for (String shipCoordinate : shipCoordinates) {
            OUTPUT.println(shipCoordinate + " ");
        }
    }
}
