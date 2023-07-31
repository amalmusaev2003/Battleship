import Exceptions.CoordinatesOutOfRangeException;
import Exceptions.HaloException;
import Exceptions.IncorrectPositionedCoordinatesException;
import Exceptions.NotValidShipException;

public class Board {
    private String[][] sample;
    public Board(String[][] sample) {
        this.sample = sample;
    }
    public String[][] getSample() {
        return sample;
    }

    public void createBoard() {
        for(int i = 0; i < sample.length; i++) {
            for(int j = 0; j < sample[i].length; j++)
                sample[i][j] = Cell.EMPTYCELL.getImg();
        }
    }
    public void printHalos(int x, int y) {
        if(x+1 <= 9)
            if (sample[x+1][y].equals(Cell.EMPTYCELL.getImg())) sample[x+1][y] = Cell.HALOCELL.getImg();
        if(x-1 >= 0)
            if(sample[x-1][y].equals(Cell.EMPTYCELL.getImg())) sample[x-1][y] = Cell.HALOCELL.getImg();
        if(y+1 <= 9) {
            if(x-1 >= 0)
                if(sample[x-1][y+1].equals(Cell.EMPTYCELL.getImg())) sample[x-1][y+1] = Cell.HALOCELL.getImg();
            if(x+1 <= 9)
                if(sample[x+1][y+1].equals(Cell.EMPTYCELL.getImg())) sample[x+1][y+1] = Cell.HALOCELL.getImg();
            if(sample[x][y+1].equals(Cell.EMPTYCELL.getImg())) sample[x][y+1] = Cell.HALOCELL.getImg();
        }
        if(y-1 >= 0) {
            if(sample[x][y-1].equals(Cell.EMPTYCELL.getImg())) sample[x][y-1] = Cell.HALOCELL.getImg();
            if(x-1 >= 0)
                if(sample[x-1][y-1].equals(Cell.EMPTYCELL.getImg())) sample[x-1][y-1] = Cell.HALOCELL.getImg();
            if(x+1 <= 9)
                if(sample[x+1][y-1].equals(Cell.EMPTYCELL.getImg())) sample[x+1][y-1] = Cell.HALOCELL.getImg();
        }
    }
    public void fillBoard(String input, int deck) throws
            IncorrectPositionedCoordinatesException, NotValidShipException, HaloException,
            CoordinatesOutOfRangeException {
        String[] coordinates = input.split(";");
        int[] amountOfX = new int[deck];
        int[] amountOfY = new int[deck];
        boolean flag1 = true, flag2 = true;
        if(coordinates.length != deck) throw new IllegalArgumentException();

        for(int i = 0; i < coordinates.length; i++) {
            String[] coordinate = coordinates[i].split(",");
            int x = Integer.parseInt(coordinate[0]);
            int y = Integer.parseInt(coordinate[1]);
            amountOfX[i] = x;
            amountOfY[i] = y;
            if ((x > 9 || x < 0) || (y > 9 || y < 0)) throw new CoordinatesOutOfRangeException();
            if(sample[x][y].equals(Cell.SHIPCELL.getImg())) throw new IncorrectPositionedCoordinatesException();
            if(sample[x][y].equals(Cell.HALOCELL.getImg())) throw new HaloException();
        }
        //Check valid or not are ships positioned
        if(deck > 1) {
            int compareValueX = amountOfX[0];
            int compareValueY = amountOfY[0];
            for (int elem : amountOfX)
                if (elem != compareValueX) flag1 = false;
            for (int elem : amountOfY)
                if (elem != compareValueY) flag2 = false;
            if (flag1 == flag2) throw new NotValidShipException();

            if (!flag1) {
                int diff = 0;
                for (int i = 0; i < amountOfX.length - 1; i++) {
                    diff = amountOfX[i + 1] - amountOfX[i];
                    if (diff != 1 && diff != -1) throw new NotValidShipException();
                }
                //we print halos for vertical ships
                for(int l = 0; l < amountOfX.length; l++) printHalos(amountOfX[l], amountOfY[0]);
            }
            if (!flag2) {
                int diff = 0;
                for (int i = 0; i < amountOfY.length - 1; i++) {
                    diff = amountOfY[i + 1] - amountOfY[i];
                    if (diff != 1 && diff != -1) throw new NotValidShipException();
                }
                //we print halos for horizontal ships
                for(int l = 0; l < amountOfY.length; l++) printHalos(amountOfX[0], amountOfY[l]);
            }
        }
        if(deck == 1) printHalos(amountOfX[0], amountOfY[0]);
        //put our ships actually
        for(int i = 0; i < coordinates.length; i++) {
            String[] coordinate = coordinates[i].split(",");
            int x = Integer.parseInt(coordinate[0]);
            int y = Integer.parseInt(coordinate[1]);
            sample[x][y] = Cell.SHIPCELL.getImg();
        }
    }
    public boolean isEmptyBoard() {
        for(int i = 0; i < sample.length; i++) {
            for(int j = 0; j < sample[i].length; j++) {
                if(sample[i][j].equals(Cell.SHIPCELL.getImg())) return true;
            }
        }
        return false;
    }
    public boolean hasPartXUp(int x, int y) {
        while (!sample[x][y].equals(Cell.HALOCELL.getImg()) && x+1 < 9) {
                if (sample[x+1][y].equals(Cell.SHIPCELL.getImg())) return true;
                x += 1;
        }
        return false;
    }
    public boolean hasPartXDown(int x, int y) {
        while (!sample[x][y].equals(Cell.HALOCELL.getImg()) && x-1 > 0) {
            if(sample[x-1][y].equals(Cell.SHIPCELL.getImg())) return true;
            x -= 1;
        }
        return false;
    }
    public boolean hasPartYUp(int x, int y) {
        while (!sample[x][y].equals(Cell.HALOCELL.getImg()) && y+1 < 9) {
            if(sample[x][y+1].equals(Cell.SHIPCELL.getImg())) return true;
            y += 1;
        }
        return false;
    }
    public boolean hasPartYDown(int x, int y) {
        while (!sample[x][y].equals(Cell.HALOCELL.getImg()) && y-1 > 0) {
            if(sample[x][y-1].equals(Cell.SHIPCELL.getImg())) return true;
            y -= 1;
        }
        return false;
    }
}
