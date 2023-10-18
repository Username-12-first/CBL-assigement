import javax.swing.JButton;

/**
* Button to be used in the Miner game UI.
*/
public class MineButton extends JButton {
    private String valueToShow;

    private int row;
    private int column;
    private Boolean blanck;
    
    /**
     * method to display the grid.
     */
    public MineButton(String newValue, Boolean blanck, int row, int column) {
        valueToShow = newValue;
        this.blanck = blanck;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Boolean isBlanckAndActive() {
        return blanck && isEnabled();
    }

    /**
     * Show hidden values.
     */
    public void showValue() {
        setText(valueToShow);
        setEnabled(false);
    }
}