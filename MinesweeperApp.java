public class MinesweeperApp {

    private MinesweeperGrid gridToPrint;

    public MinesweeperApp(MinesweeperGrid grid) {
        // Initialize the Minesweepergrid with the provided grid.
        gridToPrint = grid;
    }

    public static void main(String[] args) {
        MinesweeperGrid grid = new MinesweeperGrid();
        grid.print();
    }
}