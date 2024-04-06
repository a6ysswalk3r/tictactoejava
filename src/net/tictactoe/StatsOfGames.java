package net.tictactoe;

public class StatsOfGames {

    private String[] movesOfGames;
    private String[] coordsOfGames;

    private String firstPlayer = "first";
    private String secondPlayes = "second";

    private String playersNumber = "first";
    private byte moveNumber = 0;


    public StatsOfGames(String[] coordsOfGames, String[] movesOfGames) {
        this.coordsOfGames = coordsOfGames;
        this.movesOfGames = movesOfGames;
    }

    public String[] getCoordsOfGames() {
        return coordsOfGames;
    }
    public String[] getMovesOfGames() {
        return movesOfGames;
    }
    public void setMovesOfGames(String[] ch) {
        this.movesOfGames = ch;
    }


    public String getPlayersNumber() {
        return playersNumber;
    }

    public void changeThePlayers() {

        if (playersNumber.equals("first"))
            playersNumber = secondPlayes;
        else
            playersNumber = firstPlayer;
    }


    public void setMoveNumber() {
        moveNumber++;
    }
    public byte getMoveNumber() {
        return moveNumber;
    }


    public byte getIndexCoordinate(String coordinate) {

        for (byte i = 0; i < 9; i++) {
            if (coordsOfGames[i].equals(coordinate))
                return i;
        }
        return -1;
    }

}
