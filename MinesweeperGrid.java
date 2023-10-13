/**
 * The grid for Minesweeper.
 */
public class MinesweeperGrid{

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

    private int size = 9;

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