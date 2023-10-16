import javax.swing.JButton;

/**
* Button to be used in the Miner game UI
*/
public class MineButton extends JButton {
    private String valueToShow;

    /**
     * method to display the grid.
     */
    public MineButton(String newValue) {
        valueToShow = newValue;
    }

    /**
     * Show hidden values
     */
    public void showValue() {
        setText(valueToShow);
        setEnabled(false);
    }
}