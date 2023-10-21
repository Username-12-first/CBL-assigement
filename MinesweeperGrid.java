import java.awt.Point;

/**
* The grid for Minesweeper.
* Requrements:
*   - define how many rows and columns the grid has
*   - define how many mines the grid has
*   - define the integer number 100 as a mine
*   - define the intiger number 0 as a empty field
*   - define other intigers for to how many mines a field is conected
*/
public class MinesweeperGrid {

    /**
     * Default grid for Minesweeper.
     */
    private static int[][] defaultGrid = new int[][] {   
        {1, 100, 1, 0, 0, 1, 100, 1, 0},
        {2, 2, 2, 1, 1, 1, 1, 1, 0},
        {100, 1, 1, 100, 1, 0, 0, 0, 0},   
        {1, 1, 1, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 0, 0, 0, 0, 1, 1},   
        {1, 100, 2, 1, 0, 0, 0, 1, 100},   
        {1, 3, 100, 2, 0, 0, 0, 1, 1},   
        {0, 3, 100, 4, 1, 0, 0, 0, 0},   
        {0, 2, 100, 100, 1, 0, 0, 0, 0}, 
    };
    private int[][] grid;

    public MinesweeperGrid() {
        grid = defaultGrid;
    }

    /**
     * Couting the number of mines in the grid.
     */
    public int numberOfMines() {
        int mineCount = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[j][i] == 100) {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }

    private int size = 9;

    public int getElement(int row, int column) {
        if ((row < 0) || (row > grid.length)) {
            return -1;
        }
        if ((column < 0) || (column > grid[0].length)) {
            return -1;
        }
        return grid[row][column];
    }

    public Point getSize() {
        return new Point(grid.length, grid[0].length);
    }

    /**
     * method to print the grid.
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int retrieved = grid[i][j];
                if (retrieved == 100) {
                    System.out.print("*");
                    System.out.print(" ");
                } else {
                    System.out.print(retrieved);
                    System.out.print(" ");
                }
            }
            System.out.println(" ");
        }
    }
}