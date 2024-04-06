package net.tictactoe;

import java.util.Scanner;
import java.lang.String;


public class Main {

    // МЕТОД ДЛЯ ВЫВОДА ИГРОВОГО ПОЛЯ НА ЭКРАН
    public static void displayPlayerMove(StatsOfGames stats) {

        byte g = 0;
        byte u = -3;

        for (byte i = 0; i < 1; i++) {
            System.out.println("   A  B  C  ");
            for (byte j = 1; j < 4; j++) {
                System.out.print(j + " ");
                u += 3;
                g += 3;
                for (byte k = u; k < g; k++) {
                    System.out.print("|" + stats.getMovesOfGames()[k] + "|");
                }
                System.out.print("\n");
            }
        }
    }

    public static void main(String[] args) {

        // Заполняю массив координатами
        String[] coordinates = {
                "a1", "b1", "c1",
                "a2", "b2", "c2",
                "a3", "b3", "c3"
        };

        // Массив ходов, которые делают игроки
        String[] moves = {
                "-", "-", "-",
                "-", "-", "-",
                "-", "-", "-"
        };

        // Создал объект, чтобы было проще работать с массивами выше
        StatsOfGames stats = new StatsOfGames(coordinates, moves);

//        displayPlayerMove(stats);
        Scanner scPlayersMove = new Scanner(System.in);

        while (checkBeforeTheMove(scPlayersMove, stats)) {
            ;
        }

    }

    // МЕТОД ОБРАБОТКИ ИСКЛЮЧЕНИЙ, КОТОРЫЕ МОГУТ БЫТЬ ВЫЗВАНЫ ИГРОКАМИ
    public static boolean checkBeforeTheMove(Scanner scPlayersMove, StatsOfGames stats) {


//        System.out.print(stats.getMovesOfGames()[0] + stats.getMovesOfGames()[1] + stats.getMovesOfGames()[2]);

        if (stats.getMoveNumber() == 9) {
            System.out.println("\nIt's a draw!");
            displayPlayerMove(stats);
            System.exit(0);
        }
        else if (stats.getMoveNumber() > 4) {
            checkForAWinOrADraw(stats.getMovesOfGames(), stats);
        }

        displayPlayerMove(stats);
        System.out.print("The " + stats.getPlayersNumber() + " player's move: ");
        String playersMove = scPlayersMove.nextLine().toLowerCase();

        // ПЕРВАЯ ПРОВЕРКА ВЫЗОВОМ isThisACoordinateOrNot
        while (!isThisACoordinateOrNot(stats.getCoordsOfGames(), playersMove)) {
            System.out.print("The input is not a coordinate. Try again, for example (b2): ");
            playersMove = scPlayersMove.nextLine().toLowerCase();
        }

        // ВТОРАЯ ПРОВЕРКА ВЫЗОВОМ isTheCoordinateClosedOrNot
        while (!isTheCoordinateClosedOrNot(stats.getMovesOfGames(), playersMove, stats)) {
            System.out.println("The cell is already occupied. Try again");
            return true; // ВЕРНЁТСЯ ИСТИНА, ЧТО ПРИВЕДЁТ К ВОЗВРАТУ К ГЛАВНОЙ ФУКНЦИИ, КОТОРАЯ ПОВТОРНО ВЫЗОВЕТ thePlayerMove ДЛЯ ПРОВЕРКИ
        }
        thePlayerMove(stats.getMovesOfGames(), playersMove, stats);
        return true;
    }

    // МЕТОД РЕАЛИЗУЮЩИЙ ХОД ИГРОКОВ
    public static void thePlayerMove(String[] allPlayersMoves, String coordinateForMove, StatsOfGames stats) {

        byte theCellForTheMove = stats.getIndexCoordinate(coordinateForMove);
        if (stats.getPlayersNumber().equals("first")) {
            allPlayersMoves[theCellForTheMove] = "x";
            stats.setMovesOfGames(allPlayersMoves);
            stats.changeThePlayers();
        }
        else {
            allPlayersMoves[theCellForTheMove] = "o";
            stats.setMovesOfGames(allPlayersMoves);
            stats.changeThePlayers();
        }
        stats.setMoveNumber();
    }

    // ЭТИМ МЕТОДОМ ПРОВЕРЯЕТСЯ, ДОСТУПЕН ЛИ КВАДРАТИК НА ИГРОВОМ ПОЛЕ ИЛИ УЖЕ ЗАНЯТ
    public static boolean isTheCoordinateClosedOrNot(String[] arrayWithAllPlayersMoves, String coordinateForCheck, StatsOfGames stats) {

        byte indexCoordinate = stats.getIndexCoordinate(coordinateForCheck);
        if (arrayWithAllPlayersMoves[indexCoordinate].equals("o") || arrayWithAllPlayersMoves[indexCoordinate].equals("x"))
            return false;
        return true;

    }

    // ЭТИМ МЕТОДОМ Я ПРОВЕРЯЮ, ЯВЛЯЕТСЯ ЛИ КООРДИНАТА СУЩЕСТВУЮЩЕЙ В СПИСКЕ КООРДИНАТ ИГРОВОГО ПОЛЯ
    public static boolean isThisACoordinateOrNot(String[] arrayWithAllCoordinates, String theCoordinateBeingChecked) {

        for (byte i = 0; i < 9; i++) {
            if (arrayWithAllCoordinates[i].equals(theCoordinateBeingChecked)) // ЕСЛИ КООРДИНАТЫ СОВПАДАЮТ, ТО ЦИКЛ ЗАВЕРШАЕТСЯ
                return true;
        }
        return false;
    }

    // МЕТОД ПРОВЕРКИ КОМБИНАЦИЙ ПОБЕДЫ!
    public static void checkForAWinOrADraw(String[] allPlayersMoves, StatsOfGames stats) {
        if (allPlayersMoves[0] != "-" && allPlayersMoves[0].equals(allPlayersMoves[3]) && allPlayersMoves[3].equals(allPlayersMoves[6])) {
            endGame(stats);
        }
        else if (allPlayersMoves[1] != "-" && allPlayersMoves[1].equals(allPlayersMoves[4]) && allPlayersMoves[4].equals(allPlayersMoves[7])) {
            endGame(stats);
        }
        else if (allPlayersMoves[2] != "-" && allPlayersMoves[2].equals(allPlayersMoves[5]) && allPlayersMoves[5].equals(allPlayersMoves[8])) {
            endGame(stats);
        }
        else if (allPlayersMoves[0] != "-" && allPlayersMoves[0].equals(allPlayersMoves[1]) && allPlayersMoves[1].equals(allPlayersMoves[2])) {
            endGame(stats);
        }
        else if (allPlayersMoves[3] != "-" && allPlayersMoves[3].equals(allPlayersMoves[4]) && allPlayersMoves[4].equals(allPlayersMoves[5])) {
            endGame(stats);
        }
        else if (allPlayersMoves[6] != "-" && allPlayersMoves[6].equals(allPlayersMoves[7]) && allPlayersMoves[7].equals(allPlayersMoves[8])) {
            endGame(stats);
        }
        else if (allPlayersMoves[0] != "-" && allPlayersMoves[0].equals(allPlayersMoves[4]) && allPlayersMoves[4].equals(allPlayersMoves[8])) {
            endGame(stats);
        }
        else if (allPlayersMoves[0] != "-" && allPlayersMoves[2].equals(allPlayersMoves[4]) && allPlayersMoves[4].equals(allPlayersMoves[6])) {
            endGame(stats);
        }
    }

    public static void testDisplay(String[] testArray) {

        for (byte i = 0; i < 9; i++) {
            System.out.print(testArray[i] + " ");
        }
        System.out.print("\n");
    }

    public static void endGame(StatsOfGames stats) {
        stats.changeThePlayers();
        System.out.println("The " + stats.getPlayersNumber() + " player won!");
        displayPlayerMove(stats);
        System.exit(0);
    }

}


