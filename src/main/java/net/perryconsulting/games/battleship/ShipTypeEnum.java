package net.perryconsulting.games.battleship;

public enum ShipTypeEnum {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    final String shipType;
    final int shipLength;
    final int fleetTotalSize = 17;

    ShipTypeEnum(String shipType, int shipLength) {
        this.shipType = shipType;
        this.shipLength = shipLength;
    }

    public String getShipType() {
        return shipType;
    }

    public int getShipLength() {
        return shipLength;
    }

    public int getFleetTotalSize() {
        return fleetTotalSize;
    }

    @Override
    public String toString() {
        return shipType + " (" + shipLength + " cells)";
    }
}
