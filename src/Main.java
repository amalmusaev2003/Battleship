public class Main {
    public static void main(String[] args) {
        Board board1 = new Board(new String[10][10]);
        Board board2 = new Board(new String[10][10]);
        Player player1 = new Player(board1,"Player1");
        Player player2 = new Player(board2, "Player2");
        Game.putShips(player1);
        Game.putShips(player2);
        Game.playGame(player1, player2);
    }
}