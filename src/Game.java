import Exceptions.*;

import java.util.Scanner;

public class Game {
    public static void playGame(Player player1, Player player2) {
        Player[] players = {player1, player2};
        String firstPlayer = " ";
        Player randomPlayer = players[(int) (Math.random()*2)];
        if(randomPlayer.getName().equals(player1.getName())) firstPlayer = player1.getName();
        if(randomPlayer.getName().equals(player2.getName())) firstPlayer = player2.getName();
        while (true) {
            Board board1 = player1.getBoard();
            Board board2 = player2.getBoard();
            if(firstPlayer.equals(player1.getName())) {
                player1.shoot(player2);
                if (!board1.isEmptyBoard()) {
                    System.out.println(player2.getName() + " победил!");
                    printShips(board1);
                    return;
                }
                if (!board2.isEmptyBoard()) {
                    System.out.println(player1.getName() + " победил!");
                    printShips(board2);
                    return;
                }
                player2.shoot(player1);
            } else if(firstPlayer.equals(player2.getName())) {
                player2.shoot(player1);
                if (!board1.isEmptyBoard()) {
                    System.out.println(player2.getName() + " победил!");
                    printShips(board1);
                    return;
                }
                if (!board2.isEmptyBoard()) {
                    System.out.println(player1.getName() + " победил!");
                    printShips(board2);
                    return;
                }
                player1.shoot(player2);
            }

            /*if (!board1.isEmptyBoard()) {
                System.out.println(player2.getName() + " победил!");
                printShips(board2);
                return;
            }
            if (!board2.isEmptyBoard()) {
                System.out.println(player1.getName() + " победил!");
                printShips(board1);
                return;
            }*/
        }
    }
    public static void putShips(Player player) {
        while(true) {
            try {
                Board board = player.getBoard();
                Scanner scanner = new Scanner(System.in);
                player.getBoard().createBoard();
                System.out.println("Начнём расставлять корабли на поле " + player.getName() + ". Другой игрок, не смотри!");
                System.out.println("Введите координаты четырёхпалубного корабля:");
                String coordinatesForFourth = scanner.nextLine();
                board.fillBoard(coordinatesForFourth, 4);
                System.out.println("Введите координаты трёхпалубного корабля:");
                String coordinatesForThird1 = scanner.nextLine();
                board.fillBoard(coordinatesForThird1, 3);
                System.out.println("Введите координаты трёхпалубного корабля:");
                String coordinatesForThird2 = scanner.nextLine();
                board.fillBoard(coordinatesForThird2, 3);
                System.out.println("Введите координаты двухпалубного корабля:");
                String coordinatesForSecond1 = scanner.nextLine();
                board.fillBoard(coordinatesForSecond1, 2);
                System.out.println("Введите координаты двухпалубного корабля:");
                String coordinatesForSecond2 = scanner.nextLine();
                board.fillBoard(coordinatesForSecond2, 2);
                System.out.println("Введите координаты двухпалубного корабля:");
                String coordinatesForSecond3 = scanner.nextLine();
                board.fillBoard(coordinatesForSecond3, 2);
                System.out.println("Введите координаты однопалубного корабля:");
                String coordinatesForFirst1 = scanner.nextLine();
                board.fillBoard(coordinatesForFirst1, 1);
                System.out.println("Введите координаты однопалубного корабля:");
                String coordinatesForFirst2 = scanner.nextLine();
                board.fillBoard(coordinatesForFirst2, 1);
                System.out.println("Введите координаты однопалубного корабля:");
                String coordinatesForFirst3 = scanner.nextLine();
                board.fillBoard(coordinatesForFirst3, 1);
                System.out.println("Введите координаты однопалубного корабля:");
                String coordinatesForFirst4 = scanner.nextLine();
                board.fillBoard(coordinatesForFirst4, 1);
                printShips(board);
                return;
            } catch (CoordinatesOutOfRangeException e) {
                System.out.println("Координаты от 0 до 9!");
                player.getBoard().createBoard();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное количество координат!");
                player.getBoard().createBoard();
            } catch (IncorrectPositionedCoordinatesException e) {
                System.out.println("Координаты корабля введены некорректно!");
                player.getBoard().createBoard();
            } catch (NotValidShipException e) {
                System.out.println("Введен некорректный корабль!");
                player.getBoard().createBoard();
            } catch (HaloException e) {
                System.out.println("Вокруг корабля должна быть область шириной в одну клетку, в\n" +
                        "которой не может быть других кораблей (ореол\n" +
                        "корабля)");
                player.getBoard().createBoard();
            }
        }
    }
    public static void printShips(Board board) {
        for (int i = 0; i < board.getSample().length; i++) {
            for (int j = 0; j < board.getSample()[i].length; j++)
                System.out.print(board.getSample()[i][j] + " ");
            System.out.println("\n");
        }
    }

}
