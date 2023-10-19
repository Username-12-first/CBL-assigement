import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
* Displays grid in the GUI.
*/
public class GuiGridDisplay implements ActionListener {
    private MinesweeperGrid minesweeperGrid;
    private MineButton[][] buttons;
    
    /**
     * Filling the buttons with the value of the grid.
     */
    public GuiGridDisplay(MinesweeperGrid minesweeperGrid) {
        this.minesweeperGrid = minesweeperGrid;
        Point gridSize = minesweeperGrid.getSize();
        buttons = new MineButton[(int) gridSize.getX()][ (int) gridSize.getY()];
    }

    /**
     * method to display the grid.
     */
    public void display() {
        //Setting the frame.
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("Minsweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);

        //Setting the controlPanel.
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.white);
        controlPanel.setBounds(375, 100, 250, 50);
        controlPanel.add(new JLabel("Control panel"));
        frame.add(controlPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(125, 200, 750, 550);
        frame.add(mainPanel);
        Point gridSize = minesweeperGrid.getSize();
        var gridLayout = new GridLayout((int) gridSize.getX(), (int) gridSize.getY());
        gridLayout.setHgap(5);
        gridLayout.setVgap(10);
        mainPanel.setLayout(gridLayout);

        //Displaying the grid.
        for (int i = 0; i < gridSize.getX(); i++) {
            for (int j = 0; j < gridSize.getY(); j++) {
                int retrieved = minesweeperGrid.getElement(i, j);
                String buttonValueToShow = Integer.toString(retrieved);
                if (retrieved == 100) {
                    buttonValueToShow = "*";
                } 
                
                Boolean buttonIsBlanck = (0 == retrieved);
                var buttonMine = new MineButton(buttonValueToShow, buttonIsBlanck, i, j);
                buttonMine.addActionListener(this);
                buttonMine.setBackground(Color.pink);
                mainPanel.add(buttonMine);
                buttons[i][j] = buttonMine;
            }
        }
        
        //To refresh imediatly the display.
        mainPanel.updateUI();
    }

    // Action of clicking on the buttons.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof MineButton) {
            var clickedButton = (MineButton) e.getSource();
            if (clickedButton.isBlanckAndActive()) {
                clickedButton.showValue();
                FlipNeighboursIfBlanck(clickedButton.getRow(), clickedButton.getColumn());
            } else {
                clickedButton.showValue();
            }
        }
    }

    /**
     * Recursevly checking if the neighbouting buttons are empty.
     */
    private void FlipNeighboursIfBlanck(int row, int column) {
        // left
        if (column > 0) {
            var leftNeighbour = buttons[row][column - 1];
            if (leftNeighbour.isBlanckAndActive()) {
                leftNeighbour.showValue();
                FlipNeighboursIfBlanck(leftNeighbour.getRow(), leftNeighbour.getColumn());
            }
        }
        // right
        if (column < (buttons[0].length - 1)) {
            var rightNeighbour = buttons[row][column + 1];
            if (rightNeighbour.isBlanckAndActive()) {
                rightNeighbour.showValue();
                FlipNeighboursIfBlanck(rightNeighbour.getRow(), rightNeighbour.getColumn());
            }
        }
        // top
        if (row > 0) {
            var topNeighbour = buttons[row - 1][column];
            if (topNeighbour.isBlanckAndActive()) {
                topNeighbour.showValue();
                FlipNeighboursIfBlanck(topNeighbour.getRow(), topNeighbour.getColumn());
            }
        }
        // bottom
        if (row < (buttons.length - 1)) {
            var bottomNeighbour = buttons[row + 1][column];
            if (bottomNeighbour.isBlanckAndActive()) {
                bottomNeighbour.showValue();
                FlipNeighboursIfBlanck(bottomNeighbour.getRow(), bottomNeighbour.getColumn());
            }
        }
    }
    jjjj
}