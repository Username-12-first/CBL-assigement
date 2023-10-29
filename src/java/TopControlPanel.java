import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * The control panel that sits at the top row of the mine field frame. It
 * displays the number of mines left in the game, the time in seconds that
 * has passed since the (res)start, a reset button, shown as the smiley emoji
 * and difficulty level control buttons. Pressing the smiley button or the 
 * level buttons restarts the game at any given time. 
 */
public class TopControlPanel extends JPanel {
    // The main control object that manages the this top panel
    MinesweeperMain mainControl;

    JLabel lblMineCounter;
    JLabel lblTimer;
    JButton btnRestart;

    JButton btnBeginner;
    JButton btnIntermediate;
    JButton btnExpert;
    
    private ImageIcon smileyIcon;
    private ImageIcon coolSmileyIcon;
    private ImageIcon sadSmileyIcon;

    private ImageIcon oneNumberIcon;
    private ImageIcon twoNumberIcon;
    private ImageIcon threeNumberIcon;

    /**
     * Constructor that takes in the main control object.
     */
    public TopControlPanel(MinesweeperMain mainControl) {
        this.mainControl = mainControl;
        initIcons();
        initialize();
    }

    public void setTime(int seconds) {
        lblTimer.setText("Time: " + seconds);
    }

    public void setMinesLeft(int minesLeft) {
        lblMineCounter.setText("Mines: " + minesLeft);        
    }   
    
    // The icons are seen on the buttons of the panel to control the game
    private void initIcons() {
        String iconPath = mainControl.iconPath();
        String suffix = mainControl.iconFileExtension();
        smileyIcon = new ImageIcon(iconPath + "smiley" + suffix, "Restart");
        coolSmileyIcon = new ImageIcon(iconPath + "smiley-cool" + suffix, "You won!");
        sadSmileyIcon = new ImageIcon(iconPath + "smiley-sad" + suffix, "Bad Luck!");

        oneNumberIcon = new ImageIcon(iconPath + "one" + suffix);
        twoNumberIcon = new ImageIcon(iconPath + "two" + suffix);
        threeNumberIcon = new ImageIcon(iconPath + "three" + suffix);
    }

    /**
     * Initializes the GUI elements, sets their layouts and adds listeners.
     */
    public void initialize() {
        lblMineCounter = new JLabel();
        lblMineCounter.setText("Mines: " + mainControl.getMinesLeft());
        lblTimer = new JLabel("Time: ");
        lblTimer.setPreferredSize(new Dimension(50, 32));
        btnRestart = new JButton(smileyIcon);
        btnRestart.setToolTipText("Restart Game");        
        //btnRestart.setSize(40,40);
        btnRestart.setPreferredSize(new Dimension(36, 36));
        //btnRestart.setMaximumSize(new Dimension(38,38));
        btnRestart.setOpaque(false);
        btnRestart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainControl.restartGame();
            }
        });
    
        JRadioButton rbtnBeginner = new JRadioButton("", oneNumberIcon, true);
        rbtnBeginner.setToolTipText("Beginner");
        rbtnBeginner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainControl.restartGame(MinesweeperMain.Difficulty.BEGINNER);
            }
        });
        JRadioButton rbtnIntermediate = new JRadioButton("", twoNumberIcon, true);
        rbtnIntermediate.setToolTipText("Intermediate");
        rbtnIntermediate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainControl.restartGame(MinesweeperMain.Difficulty.INTERMEDIATE);
            }
        });
        JRadioButton rbtnExpert = new JRadioButton("", threeNumberIcon, true);
        rbtnExpert.setToolTipText("Expert");
        rbtnExpert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainControl.restartGame(MinesweeperMain.Difficulty.EXPERT);
            }
        });
        
        ButtonGroup btgDifficulty = new ButtonGroup();
        btgDifficulty.add(rbtnBeginner);
        btgDifficulty.add(rbtnIntermediate);
        btgDifficulty.add(rbtnExpert);
        JPanel pnlDifficulty = new JPanel();
        pnlDifficulty.setLayout(new BoxLayout(pnlDifficulty, BoxLayout.X_AXIS));
        pnlDifficulty.add(rbtnBeginner);
        pnlDifficulty.add(rbtnIntermediate);
        pnlDifficulty.add(rbtnExpert);

        setLayout(new GridBagLayout());        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(lblMineCounter, gbc);

        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnRestart, gbc);

        gbc.weightx = 1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlDifficulty, gbc);

        gbc.weightx = 1;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;       
        add(lblTimer, gbc);
    }

    // When the game is won, the controller calls this method to set the
    // smiley face on the reset button
    public void gameWon() {
        btnRestart.setIcon(coolSmileyIcon);
    }

    // When the game is lost, the controller calls this method to set the
    // sad smiley face on the reset button
    public void gameLost() {
        btnRestart.setIcon(sadSmileyIcon);
    }

    /**
     * Called by main control to setup the panel when the game is (re)started.
     */
    public void prepareStart() {
        btnRestart.setIcon(smileyIcon);
        setMinesLeft(mainControl.getMinesLeft());
        setTime(0);         
    }
}
