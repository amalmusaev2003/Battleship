import java.util.Scanner;

public class Player {
    private Board board;
    private String name;

    public Player(Board board, String name) {
        this.board = board;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public void shoot(Player enemy) {
        System.out.println(name + " твой ход: ");
        Scanner scanner = new Scanner(System.in);
        String[] coordinates = scanner.nextLine().split(",");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        boolean Notfound = true;
        Board enemyBoard = enemy.getBoard();
        if (enemyBoard.getSample()[x][y].equals(Cell.SHIPCELL.getImg())) {
            enemyBoard.getSample()[x][y] = Cell.PADDEDCELL.getImg();
            //debugger: Game.printShips(enemyBoard);
            if (enemyBoard.hasPartXUp(x, y)) Notfound = false;
            if (enemyBoard.hasPartXDown(x, y)) Notfound = false;
            if (enemyBoard.hasPartYUp(x, y)) Notfound = false;
            if (enemyBoard.hasPartYDown(x, y)) Notfound = false;
            if (!Notfound) System.out.println("Попал!");
            else System.out.println("Утопил!");
            if(enemy.getBoard().isEmptyBoard())
                shoot(enemy);

        } else {
            System.out.println("Мимо!");
        }
    }
}

