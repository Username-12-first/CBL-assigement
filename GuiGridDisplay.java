import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
* Displays grid in the GUI.
*/
public class GuiGridDisplay implements MouseListener {
    private JFrame mainFrame;
    private JLabel textInControlPanel;
    private MinesweeperGrid minesweeperGrid;
    private MineButton[][] buttons;
    private int numberOfFoundMines;
    Font fontForText = new Font("Serif", Font.BOLD, 30);
    
    /**
     * Making the grid.
     */
    public GuiGridDisplay(MinesweeperGrid minesweeperGrid) {
        this.minesweeperGrid = minesweeperGrid;
        Point gridSize = minesweeperGrid.getSize();
        buttons = new MineButton[(int) gridSize.getX()][ (int) gridSize.getY()];
        numberOfFoundMines = 0;
    }

    /**
     * Method to display the grid.
     */
    public void display() {
        numberOfFoundMines = 0;
        mainFrame = new JFrame();
        mainFrame.setLayout(null);
        mainFrame.setTitle("Minsweeper");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 1000);
        mainFrame.setVisible(true);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.white);
        controlPanel.setBounds(375, 100, 250, 50);
        textInControlPanel = new JLabel();
        textInControlPanel.setFont(fontForText);
        updateMineCount();
        controlPanel.add(textInControlPanel);
        mainFrame.add(controlPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(125, 200, 750, 550);
        mainFrame.add(mainPanel);
        Point gridSize = minesweeperGrid.getSize();
        var gridLayout = new GridLayout((int) gridSize.getX(), (int) gridSize.getY());
        gridLayout.setHgap(5);
        gridLayout.setVgap(10);
        mainPanel.setLayout(gridLayout);

        for (int i = 0; i < gridSize.getX(); i++) {
            for (int j = 0; j < gridSize.getY(); j++) {
                int retrieved = minesweeperGrid.getElement(i, j);
                String buttonValueToShow = Integer.toString(retrieved);
                if (retrieved == 100) {
                    buttonValueToShow = "*";
                } 
                
                Boolean buttonIsBlanck = (0 == retrieved);
                Boolean buttonHasMine = (100 == retrieved);
                var buttonMine = 
                    new MineButton(buttonValueToShow, buttonIsBlanck, buttonHasMine, i, j);
                buttonMine.addMouseListener(this);
                buttonMine.setFont(fontForText);
                buttonMine.setBackground(Color.pink);
                mainPanel.add(buttonMine);
                buttons[i][j] = buttonMine;
            }
        }

        mainPanel.updateUI();
   }

    private void FlipNeighboursIfBlanck(MineButton buttonToCheck) {
        int row = buttonToCheck.getRow();
        int column = buttonToCheck.getColumn();
        // Checking left neighbour.
        if (column > 0) {
            var leftNeighbour = buttons[row][column - 1];
            if (leftNeighbour.isBlanckAndActive()) {
                leftNeighbour.showValue();
                FlipNeighboursIfBlanck(leftNeighbour);
            }
        }
        // Checking right neighbour
        if (column < (buttons[0].length - 1)) {
            var rightNeighbour = buttons[row][column + 1];
            if (rightNeighbour.isBlanckAndActive()) {
                rightNeighbour.showValue();
                FlipNeighboursIfBlanck(rightNeighbour);
            }
        }
        // Checking top neighbour
        if (row > 0) {
            var topNeighbour = buttons[row - 1][column];
            if (topNeighbour.isBlanckAndActive()) {
                topNeighbour.showValue();
                FlipNeighboursIfBlanck(topNeighbour);
            }
        }
        // Checking bottom neighbour
        if (row < (buttons.length - 1)) {
            var bottomNeighbour = buttons[row + 1][column];
            if (bottomNeighbour.isBlanckAndActive()) {
                bottomNeighbour.showValue();
                FlipNeighboursIfBlanck(bottomNeighbour);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)  {
        if (e.getSource() instanceof MineButton) {
            var clickedButton = (MineButton) e.getSource();
            if (!clickedButton.isEnabled()) {
                return;
            }
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (clickedButton.isBlanckAndActive()) {
                    clickedButton.showValue();
                    FlipNeighboursIfBlanck(clickedButton);
                } else {
                    if (clickedButton.isMine()) {
                        RestarGame(false, "Here is IS mine, bye, bye");
                        return;
                    }
                    clickedButton.showValue();
                }
            } else if (SwingUtilities.isRightMouseButton(e)) {
                // With the red colour you indicate it is a mine             
                if (clickedButton.isMine()) {
                    clickedButton.setBackground(Color.red);
                    clickedButton.showValue();
                    numberOfFoundMines++;
                    updateMineCount();
                    if (numberOfFoundMines == 10) {
                        RestarGame(true, "YOU WON!!!");
                    }
                } else {
                    RestarGame(false, "Here is NO Mine, bye, bye");
                    return;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Not needeed.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Not needeed.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Not needeed.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Not needeed.
    }

    private void RestarGame(Boolean isWin, String reasonToRestartGame) {
        String prefixMessage = "LOOSER";
        int messageType = JOptionPane.ERROR_MESSAGE;
        if (isWin) {
            messageType = JOptionPane.INFORMATION_MESSAGE;
            prefixMessage = "LUCKY";
        }
        JOptionPane.showMessageDialog(
            null, 
            prefixMessage + "!!!\n" + reasonToRestartGame,
            "GAME OVER",
            messageType
        );
        mainFrame.dispose();
        display();
    }

    private void updateMineCount() {
        textInControlPanel.setText("Mine count = " + Integer.toString(numberOfFoundMines));
    }

}