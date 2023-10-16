import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
* Displays grid in the GUI.
*/
public class GuiGridDisplay implements ActionListener {
    private MinesweeperGrid minesweeperGrid;
    
    public GuiGridDisplay(MinesweeperGrid minesweeperGrid) {
        this.minesweeperGrid = minesweeperGrid;
    }

    /**
     * method to print the grid.
     */
    public void display() {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("Minsweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);

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


        for (int i = 0; i < gridSize.getX(); i++) {
            for (int j = 0; j < gridSize.getY(); j++) {
                int retrieved = minesweeperGrid.getElement(i, j);
                String buttonValueToShow = Integer.toString(retrieved);
                if (retrieved == 100) {
                    buttonValueToShow = "*";
                } 
                
                JButton buttonMine = new MineButton(buttonValueToShow);
                buttonMine.addActionListener(this);
                buttonMine.setBackground(Color.pink);
                mainPanel.add(buttonMine);
            }
        }
        mainPanel.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof MineButton) {
            var clickedButton = (MineButton) e.getSource();
            clickedButton.showValue();
        }
   }
}