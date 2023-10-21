import java.awt.*;
import javax.swing.*;

/**
* Button to be used in the Miner game UI.
*/
public class MineButton extends JButton {
    private String valueToShow;

    private int row;
    private int column;
    private Boolean blanck;
    private Boolean mine;
    
    public MineButton(String newValue) {
        this(newValue, false, false, -1, -1);
    }
    
    /**
     * Create new button.
     */
    public MineButton(String newValue, Boolean blanck, Boolean mine, int row, int column) {
        valueToShow = newValue;
        this.blanck = blanck;
        this.mine = mine;
        this.row = row;
        this.column = column;

        var fontForText = new Font("Serif", Font.BOLD, 40);
        setFont(fontForText);
        setBackground(Color.pink);
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

    public Boolean isMine() {
        return mine;
    }

    /**
     * Show hidden values.
     */
    public void showValue() {
        if (blanck) {
            setBackground(Color.white);
        } else if (mine) {
            setBackground(Color.red);
        } else {
            setBackground(Color.yellow);
        }

        setText(valueToShow);
        setEnabled(false);
    }
}