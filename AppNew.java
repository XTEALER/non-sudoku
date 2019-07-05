
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Random;

class AppNew {
    static int x;
    static createWindow cW;
    static int numbersStored = 0;
    static Messages msgs = new Messages();
    static String[] tableNumbers = new String[9];
    static JLabel[][] scoreslist = new JLabel[10][2];
    static JLabel[] titles = new JLabel[2];
    static JLabel[] tablePositions = new JLabel[9];
    static String[] winners = new String[10];
    static int[] scores = new int[10];
    static JPanel scorePanel;
    static JLabel errorWarning;

    // initializes array
    private static void initArray() {
        Random rand = new Random();
        int centerValue = rand.nextInt(9) + 1;
        int x;
        // restarts numbers stored
        numbersStored = 0;
        for (x = 0; x < 9; x++) {
            // sets jlabel text
            if (x != 4) {
                tableNumbers[x] = "X";
                tablePositions[x].setText(tableNumbers[x]);
                tablePositions[x].setForeground(Color.decode("#5d13e7"));
            } else {
                tableNumbers[x] = String.valueOf(centerValue);
                tablePositions[x].setText(tableNumbers[x]);
            }
        }
        return;
    }

    // checks if input for position is a valid integer between 1 to 9
    private static boolean validateInput(String strNumber) {
        boolean isValid = false;
        int temp;
        try {
            temp = Integer.parseInt(strNumber);
            if (temp > 0 && temp < 10) {
                isValid = true;
            }
        } catch (Exception e) {
            cW.msgScreen(msgs.errorStates(msgs.errInt));
        }
        // returns valid input
        return isValid;
    }

    // player has won or lost /* add code here */
    private static boolean playerWon() {
        int[] values = new int[9];
        boolean hasWon = false;
        int x;
        // converts string numbers to integer and stores them on local integer array
        for (x = 0; x < 9; x++) {
            values[x] = Integer.parseInt(tableNumbers[x]);
        }
        int horizontal1 = values[0] + values[1] + values[2];
        int horizontal2 = values[3] + values[4] + values[5];
        int horizontal3 = values[6] + values[7] + values[8];
        int vertical1 = values[0] + values[3] + values[6];
        int vertical2 = values[1] + values[4] + values[7];
        int vertical3 = values[2] + values[5] + values[8];
        int oblicua1 = values[0] + values[4] + values[8];
        int oblicua2 = values[2] + values[4] + values[6];
        // if every possible combination results on 15 then player wins
        if (horizontal1 == 15 && horizontal2 == 15 && horizontal3 == 15 && vertical1 == 15 && vertical2 == 15
                && vertical3 == 15 && oblicua1 == 15 && oblicua2 == 15) {
            hasWon = true;
        }
        // value that returns
        return hasWon;
    }

    private static void storeWinnerPlayer(String playerName) {
        int j;
        boolean playerExists = false;
        for (j = 0; j < 10; j++) {
            if (playerName.equals(winners[j])) {
                playerExists = true;
                scores[j]++;
                break;
            }
        }
        // saves new players on scoreboard
        if (!playerExists) {
            for (j = 0; j < 10; j++) {
                if (winners[j].equals("empty")) {
                    winners[j] = playerName;
                    scores[j] = 1;
                    break;
                } else if (j == 8) {
                    winners[j] = playerName;
                    scores[j] = 1;
                }
            }
        }
        return;
    }

    // checks if won or lost
    private static void checkGame() {
        boolean userHasWon = playerWon();
        cW.msgScreen(msgs.gameState(userHasWon));
        return;
    }

    // shows scoreboard screen and keeps updating it
    private static void showScores() {
        int j;
        for (j = 0; j < 10; j++) {
            scoreslist[j][0].setText(winners[j]);
            scoreslist[j][1].setText(String.valueOf(scores[j]));
        }
        return;
    }

    // add element to numbers table
    private static void addElement(int index) {
        String temp;
        while (true) {
            temp = JOptionPane.showInputDialog(cW.Frame, "NUMERO ENTRE 1 Y 9");
            if (temp != null && !temp.equals("")) {
                if (validateInput(temp)) {
                    tableNumbers[index] = temp;
                    tablePositions[index].setText(temp);
                    // increments values stored on array before returning
                    numbersStored++;
                    if (numbersStored == 8) {
                        checkGame();
                    }
                    break;
                }
            } else {
                System.out.println("User cancelled");
                break;
            }
        }
        return;
    }

    // main method
    public static void main(String[] args) {
        // grid bag constrains object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 0, 3, 3);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // mainframe
        cW = new createWindow("15 MISTERIOSO", "#2FCD55", false, 600, 600);
        // score panel
        scorePanel = cW.createPanel("#000000", false);

        // new border
        Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
        // creates new empty border
        Border margin = new EmptyBorder(2, 20, 2, 20);
        titles[0] = new JLabel("NOMBRE", SwingConstants.CENTER);
        titles[0].setFont(new Font("Arial", Font.BOLD, 20));
        titles[0].setBorder(new CompoundBorder(border, margin));
        titles[0].setForeground(Color.WHITE);
        scorePanel.add(titles[0], gbc);

        gbc.gridx++;
        titles[1] = new JLabel("PUNTAJE", SwingConstants.CENTER);
        titles[1].setFont(new Font("Arial", Font.BOLD, 20));
        titles[1].setBorder(new CompoundBorder(border, margin));
        titles[1].setForeground(Color.WHITE);
        scorePanel.add(titles[1], gbc);

        // set system scores
        winners[0] = "ANGELOTO";
        winners[1] = "XTEALER";
        scores[0] = 4;
        scores[1] = 4;

        gbc.insets = new Insets(0, 0, 3, 3);
        gbc.fill = GridBagConstraints.BOTH;
        for (x = 0; x < 10; x++) {
            if (winners[x] == null) {
                winners[x] = "empty";
            }
            scoreslist[x][0] = new JLabel(winners[x], SwingConstants.LEFT);
            scoreslist[x][1] = new JLabel("" + scores[x], SwingConstants.CENTER);
            scoreslist[x][0].setBorder(new CompoundBorder(border, margin));
            scoreslist[x][1].setBorder(new CompoundBorder(border, margin));
            scoreslist[x][0].setFont(new Font("Arial", Font.BOLD, 20));
            scoreslist[x][1].setFont(new Font("Arial", Font.BOLD, 20));
            scoreslist[x][0].setForeground(Color.WHITE);
            scoreslist[x][1].setForeground(Color.WHITE);
            gbc.gridx = 0;
            gbc.gridy++;
            scorePanel.add(scoreslist[x][0], gbc);
            gbc.gridx++;
            scorePanel.add(scoreslist[x][1], gbc);
        }

        // button builder
        Buttons btn = new Buttons();

        // grid constrains reset
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // base panel
        JPanel basePanel = cW.createPanel("#0F7494", true);
        // add base panel to Frame
        cW.Frame.add(basePanel);

        // menu panel
        JPanel menuPanel = cW.createPanel("#FFFFFF", true);
        // game window
        JPanel gameWindow = cW.createPanel("#FFFFFF", false);
        // in-game buttons
        JPanel gameButtons = cW.createPanel("#FFFFFF", false);
        // name input panel
        JPanel inputPanel = cW.createPanel("#000000", false);
        // error panel
        JPanel errorPanel = cW.createPanel("#000000", true);

        /* -----------------------create buttons for UI----------------------- */

        // help button
        JButton helpBtn = btn.createButton("AYUDA", "#c78928", "Arial", 15);
        // hover listener
        helpBtn.addMouseListener(btn.onHover(helpBtn, "#216583", "#c78928"));
        // on-press action
        helpBtn.addActionListener(e -> cW.msgScreen(msgs.Help()));

        // restart game button
        JButton restartGame = btn.createButton("REINICIAR PARTIDA", "#27AA80", "Arial", 15);
        // hover listener
        restartGame.addMouseListener(btn.onHover(restartGame, "#216583", "#27AA80"));
        // on-press listener
        restartGame.addActionListener(e -> initArray());

        // go back to menu button
        JButton backToMenu = btn.createButton("REGRESAR", "#F76262", "Arial", 15);
        // hover listener
        backToMenu.addMouseListener(btn.onHover(backToMenu, "#216583", "#F76262"));
        // on-press listener
        backToMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameWindow.isVisible()) {
                    // hides game panel and its buttons
                    gameWindow.setVisible(false);
                } else {
                    scorePanel.setVisible(false);
                }
                gameButtons.setVisible(false);
                // show menu panel
                menuPanel.setVisible(true);
                return;
            }
        });

        // input field
        JTextField playerName = new JTextField("10 caracteres max.", SwingConstants.CENTER);
        // Listen for changes in the text
        playerName.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                errorWarning.setForeground(Color.BLACK);
            }
        });

        JButton saveInput = btn.createButton("ACEPTAR", "#D57F1D", "Arial", 25);
        saveInput.addMouseListener(btn.onHover(saveInput, "#216583", "#D57F1D"));
        saveInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pName = "";
                pName = playerName.getText().toUpperCase();
                if (pName.length() > 1 && pName.length() < 11) {
                    inputPanel.setVisible(false);
                    storeWinnerPlayer(pName);
                    playerName.setText(null);
                    // shows scoreboard
                    showScores();
                    scorePanel.setVisible(true);
                    helpBtn.setVisible(false);
                    restartGame.setVisible(false);
                    gameButtons.setVisible(true);
                } else {
                    errorWarning.setForeground(Color.RED);
                }
            }
        });

        JButton showScoreBoard = btn.createButton("PUNTUACIONES", "#89af43", "Arial", 25);
        showScoreBoard.addMouseListener(btn.onHover(showScoreBoard, "#69a9d3", "#89af43"));
        // set button listener
        showScoreBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showScores();
                // hides menu panel
                menuPanel.setVisible(false);
                // shows scoreboard
                scorePanel.setVisible(true);
                helpBtn.setVisible(false);
                restartGame.setVisible(false);
                gameButtons.setVisible(true);
                return;
            }
        });

        // play button
        JButton playBtn = btn.createButton("JUGAR", "#D57F1D", "Arial", 25);
        // hover listener
        playBtn.addMouseListener(btn.onHover(playBtn, "#69a9d3", "#D57F1D"));
        // set button listener
        playBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ressets table array
                initArray();
                // hides menu panel
                menuPanel.setVisible(false);
                // shows game panel and its buttons
                gameWindow.setVisible(true);
                helpBtn.setVisible(true);
                restartGame.setVisible(true);
                gameButtons.setVisible(true);
                return;
            }
        });

        // exit button
        JButton exitBtn = btn.createButton("SALIR", "#d54638", "Arial", 25);
        // hover listener
        exitBtn.addMouseListener(btn.onHover(exitBtn, "#ff1700", "#d54638"));
        // on press listener
        exitBtn.addActionListener(e -> System.exit(0));

        // title and properties of it
        JLabel mainTitle = new JLabel("¡BIENVENIDO A 15 MISTERIOSO!");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 20));

        // input label
        JLabel inputText = new JLabel("DIGITE SU NOMBRE", SwingConstants.CENTER);
        inputText.setFont(new Font("Arial", Font.BOLD, 30));
        inputText.setForeground(Color.WHITE);

        // error label
        errorWarning = new JLabel("ERROR", SwingConstants.CENTER);
        errorWarning.setFont(new Font("Arial", Font.BOLD, 30));
        errorWarning.setForeground(Color.BLACK);
        errorWarning.setVisible(true);

        // add components to input panel
        gbc.insets = new Insets(50, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        inputPanel.add(inputText, gbc);
        gbc.gridy++;
        gbc.weighty = 10;
        inputPanel.add(playerName, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        errorPanel.add(errorWarning, gbc);
        gbc.insets = new Insets(20, 0, 0, 0);
        inputPanel.add(errorPanel, gbc);
        gbc.insets = new Insets(40, 20, 20, 20);
        gbc.gridy++;
        inputPanel.add(saveInput, gbc);
        gbc.gridy = 0;

        // add components to menuPanel
        gbc.insets = new Insets(40, 20, 50, 20);
        menuPanel.add(mainTitle, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 20, 25, 20);
        gbc.weighty = 0;
        gbc.ipady = 30;
        gbc.ipadx = 50;
        menuPanel.add(playBtn, gbc);
        gbc.gridy++;
        menuPanel.add(showScoreBoard, gbc);
        gbc.gridy++;
        menuPanel.add(exitBtn, gbc);

        // add to game window its components
        // reset grid positions
        gbc.gridx = 0;
        gbc.gridy = 0;
        // internal space for buttons
        gbc.ipadx = 5;
        gbc.ipady = 15;
        // set external padding for cells
        gbc.insets = new Insets(10, 5, 10, 5);
        // add buttons to button panel
        gameButtons.add(helpBtn, gbc);
        gbc.gridx++;
        gameButtons.add(restartGame, gbc);
        gbc.gridx++;
        gameButtons.add(backToMenu, gbc);
        // reset x position
        gbc.gridx = 0;
        gbc.ipadx = 15;
        gbc.ipady = 15;
        for (x = 0; x < 9; x++) {
            if (x % 3 == 0) {
                gbc.gridy++;
                gbc.gridx = 0;
            }
            if ((x + 1) % 3 == 0) {
                gbc.insets = new Insets(5, 5, 0, 5);
            } else {
                gbc.insets = new Insets(5, 5, 0, 0);
            }
            // creates new JLabel at position
            tablePositions[x] = new JLabel("", SwingConstants.CENTER);
            // sets font for jlabel
            tablePositions[x].setFont(new Font("Arial", Font.BOLD, 30));
            // sets Jlabel initial color
            if (x != 4) {
                tablePositions[x].setForeground(Color.decode("#5d13e7"));
            } else {
                tablePositions[x].setForeground(Color.decode("#f6c523"));
            }
            // adds border to jlabel
            tablePositions[x].setBorder(BorderFactory.createLineBorder(Color.decode("#304E78"), 5));
            // gets border object from label
            border = tablePositions[x].getBorder();
            // creates new empty border
            margin = new EmptyBorder(30, 30, 30, 30);
            // adds internal border and external border to jlabel
            tablePositions[x].setBorder(new CompoundBorder(border, margin));
            tablePositions[x].setCursor(tablePositions[x].getCursor());
            tablePositions[x].addMouseListener(new MouseAdapter() {
                int nx = x;

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (nx != 4) {
                        if (tableNumbers[nx] == "X") {
                            tablePositions[nx].setForeground(Color.decode("#ff8f56"));
                        } else {
                            tablePositions[nx].setForeground(Color.decode("#aaaaaa"));
                        }
                    }
                    return;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (nx != 4) {
                        if (tableNumbers[nx] == "X") {
                            tablePositions[nx].setForeground(Color.decode("#5d13e7"));
                        } else {
                            tablePositions[nx].setForeground(Color.decode("#aaaaaa"));
                        }
                    }
                    return;
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (tableNumbers[nx] == "X") {
                        addElement(nx);
                    }
                    // if player won, store its name on the score board
                    if (numbersStored == 8 && playerWon()) {
                        gameWindow.setVisible(false);
                        gameButtons.setVisible(false);
                        inputPanel.setVisible(true);
                        initArray();
                    }
                    return;
                }
            });
            // adds jlabel to game jpanel
            gameWindow.add(tablePositions[x], gbc);
            // handles gridbaglayout constraints
            if (x < 8) {
                gbc.gridx++;
            } else {
                gbc.gridx = 0;
                gbc.gridy = 0;
            }
        }

        // add base panel components
        gbc.ipadx = 10;
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        basePanel.add(menuPanel, gbc);
        gbc.ipady = 25;
        basePanel.add(gameWindow, gbc);
        gbc.weightx = 50;
        basePanel.add(scorePanel, gbc);
        basePanel.add(inputPanel, gbc);
        gbc.gridy++;
        gbc.ipady = 0;
        basePanel.add(gameButtons, gbc);

        // makes window visible
        cW.Frame.setVisible(true);
    }
}