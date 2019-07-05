
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

class AppNew {
    static ArrayList<String> winners = new ArrayList<String>();
    static ArrayList<Integer> scores = new ArrayList<Integer>();
    static JLabel[] tablePositions = new JLabel[9];
    static String[] tableNumbers = new String[9];
    static int numbersStored = 0;
    static createWindow cW;
    static int x;

    // help window
    private static void Help() {
        String text = "La suma de 3 numeros en\norden diagonal, horizontal o vertical no puede ser\nmayor a 15.\n\nEscoge tus valores estrategicamente!";
        cW.msgScreen("Instrucciones", text);
        return;
    }

    // initializes array
    private static void initArray() {
        int x;
        // restarts numbers stored
        numbersStored = 0;
        for (x = 0; x < 9; x++) {
            //sets jlabel text
            if(x != 4) {
                tableNumbers[x] = "X";
                tablePositions[x].setText(tableNumbers[x]);
                tablePositions[x].setForeground(Color.decode("#5d13e7"));
            } else {
                tablePositions[x].setText("?");
                tableNumbers[x] = "5";
            }
        }
        return;
    }

    // checks if input for position is a valid integer between 1 to 9
    private static boolean validateInput(String data) {
        boolean isValid = false;
        int temp;
        try {
            temp = Integer.parseInt(data);
            if(temp > 0 && temp < 10) {
                isValid = true;
            }
        } catch(Exception e) {
            cW.msgScreen("ERROR", "DIGITE NUMEROS VALIDOS");
        }
        // returns valid input
        return isValid;
    }

    // player has won or lost            /* add code here */
    private static boolean playerWon() {
        boolean hasWon = false;

        // value that returns
        return hasWon;
    }

    // shows scoreboard screen and keeps updating it
    private static void scoreBoard() {
        createWindow temp = new createWindow("PUNTAJES");
        GridBagConstraints gb = new GridBagConstraints();
        JLabel scoreslist = new JLabel("", SwingConstants.LEFT);
        JPanel scopanel = new JPanel();
        JLabel[] titles = new JLabel[2];
        int j;
        temp.Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        temp.Frame.setLayout(new GridBagLayout());
        gb.gridx = 0;
        gb.gridy = 0;
        gb.insets = new Insets(10, 10, 10, 10);
        // new border
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        // creates new empty border
        Border margin = new EmptyBorder(2,5,2,5);
        titles[0] = new JLabel("NOMBRE", SwingConstants.CENTER);
        titles[0].setBorder(new CompoundBorder(border, margin));
        titles[0].setForeground(Color.WHITE);
        scopanel.add(titles[0], gb);
        gb.gridx++;
        titles[1] = new JLabel("PUNTAJE", SwingConstants.CENTER);
        titles[1].setBorder(new CompoundBorder(border, margin));
        titles[1].setForeground(Color.WHITE);
        scopanel.add(titles[1], gb);
        
        
        // for(j = 0; j < winners.size(); j++) {
        //     gb.gridx = 0;
        //     gb.gridy++;
        //     scoreslist.setText(winners.get(j));
        //     scopanel.add(scoreslist, gb);
        //     gb.gridx++;
        //     scoreslist.setText(scores.get(j).toString());
        //     scopanel.add(scoreslist, gb);
        // }
        gb.gridx = 0;
        gb.gridy = 0;
        temp.Frame.setMinimumSize(new Dimension(400, 250));
        temp.Frame.setResizable(false);
        scopanel.setBackground(Color.BLACK);
        gb.fill = GridBagConstraints.HORIZONTAL;
        temp.Frame.add(scopanel, gb);
        temp.Frame.setVisible(true);
        return;
    }

    // add element to numbers table
    private static void addElement(int index) {
        String temp;
        while(true) 
        {
            temp = JOptionPane.showInputDialog(cW.Frame, "NUMERO ENTRE 1 Y 9");
            if(temp != null && !temp.equals("")) {
                if(validateInput(temp)) {
                    tableNumbers[index] = temp;
                    tablePositions[index].setText(temp);
                    // increments values stored on array before returning
                    numbersStored++;
                    if(numbersStored == 8) {
                        if(playerWon()) {
                            // show victory
                        } else {
                            // show lost
                        }
                        scoreBoard();
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

    // checks if won or lost
    private static void checkGame() {
        // funcion angel
        return;
    }


    // main method
    public static void main(String[] args) {
        // set system scores
        winners.add("XTEALER");
        winners.add("ANGELOTO");
        winners.add("ANGELOTO");
        winners.add("XTEALER");
        scores.add(120);
        scores.add(119);
        scores.add(107);
        scores.add(105);

        // mainframe
        cW = new createWindow("3x3 Table Game");
        cW.Frame.setMinimumSize(new Dimension(600, 600));
        cW.Frame.setBackground(Color.decode("#2FCD55"));
        cW.Frame.setResizable(false);

        // grid bag constrains object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // base panel
        JPanel basePanel = new JPanel(new GridBagLayout());
        basePanel.setBackground(Color.decode("#0f7494"));
        gbc.fill = GridBagConstraints.BOTH;
        // add base panel to Frame
        cW.Frame.add(basePanel);
        // menu panel
        JPanel menuPanel = new JPanel(new GridBagLayout());
        // game window
        JPanel gameWindow = new JPanel(new GridBagLayout());
        gameWindow.setVisible(false);
        // in-game buttons
        JPanel gameButtons = new JPanel(new GridBagLayout());
        gameButtons.setVisible(false);

        /* -----------------------create buttons for UI----------------------- */
        
        // help button
        JButton helpBtn = cW.addBtn("AYUDA");
        helpBtn.setFont(new Font("Arial", Font.BOLD, 15));
        helpBtn.setForeground(Color.decode("#614ad3"));
        // hover listener
        helpBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                helpBtn.setForeground(Color.decode("#216583"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                helpBtn.setForeground(Color.decode("#614ad3"));
            }
        });
        // on-press action
        helpBtn.addActionListener(e -> Help());

        // restart game button
        JButton restartGame = new JButton("REINICIAR PARTIDA");
        restartGame.setFont(new Font("Arial", Font.BOLD, 15));
        restartGame.setForeground(Color.decode("#27aa80"));
        // hover listener
        restartGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                restartGame.setForeground(Color.decode("#216583"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                restartGame.setForeground(Color.decode("#27aa80"));
            }
        });
        // on-press listener
        restartGame.addActionListener(e -> initArray());

        // go back to menu button
        JButton backToMenu = new JButton("REGRESAR");
        backToMenu.setFont(new Font("Arial", Font.BOLD, 15));
        backToMenu.setForeground(Color.decode("#f76262"));
        // hover listener
        backToMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backToMenu.setForeground(Color.decode("#216583"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backToMenu.setForeground(Color.decode("#f76262"));
            }
        });
        // on-press listener
        backToMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // hides game panel and its buttons
                gameWindow.setVisible(false);
                gameButtons.setVisible(false);
                // show menu panel
                menuPanel.setVisible(true);
                return;
            }
        });

        // play button
        JButton playBtn = cW.addBtn("JUGAR");
        playBtn.setForeground(Color.decode("#fe5f55"));
        playBtn.setFont(new Font("Arial", Font.BOLD, 25));
        // hover listener
        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playBtn.setForeground(Color.decode("#6b778d"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                playBtn.setForeground(Color.decode("#fe5f55"));
            }
        });
        // set button listener
        playBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ressets table array
                initArray();
                // hides menu panel
                menuPanel.setVisible(false);
                // shows game panel and its buttons
                gameWindow.setVisible(true);
                gameButtons.setVisible(true);
                return;
            }
        });

        // exit button
        JButton exitBtn = cW.addBtn("SALIR");
        exitBtn.setForeground(Color.decode("#e42c64"));
        exitBtn.setFont(new Font("Arial", Font.BOLD, 25));
        // hover listener
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn.setForeground(Color.decode("#6b778d"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setForeground(Color.decode("#e42c64"));
            }
        });
        // on press listener
        exitBtn.addActionListener(e -> System.exit(0));

        // title
        JLabel mainTitle = new JLabel("BIENVENIDO AL JUEGO 3x3!");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 20));

        // add components to menuPanel
        gbc.insets = new Insets(40, 20, 50, 20);
        menuPanel.add(mainTitle, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 20, 25, 20);
        gbc.ipady = 30;
        gbc.ipadx = 50;
        menuPanel.add(playBtn, gbc);
        gbc.gridy++;
        menuPanel.add(exitBtn, gbc);
        gbc.gridx++;
        
        // add to game window its components
        //reset grid positions
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
        for(x = 0; x < 9; x++) {
            if(x % 3 == 0) {
                gbc.gridy++;
                gbc.gridx = 0;
            }
            if((x + 1) % 3 == 0 ) {
                gbc.insets = new Insets(5, 5, 0, 5);
            } else {
                gbc.insets = new Insets(5, 5, 0, 0);
            }
            // creates new JLabel at position
            tablePositions[x] = new JLabel("", SwingConstants.CENTER);
            // sets font for jlabel
            tablePositions[x].setFont(new Font("Arial", Font.BOLD, 30));
            // sets Jlabel initial color
            if(x != 4) {
                tablePositions[x].setForeground(Color.decode("#5d13e7"));
            } else {
                tablePositions[x].setForeground(Color.decode("#f6c523"));
            }
            // adds border to jlabel
            tablePositions[x].setBorder(BorderFactory.createLineBorder(Color.decode("#304E78"), 5));
            // gets border object from label
            Border border = tablePositions[x].getBorder();
            // creates new empty border
            Border margin = new EmptyBorder(30,30,30,30);
            // adds internal border and external border to jlabel
            tablePositions[x].setBorder(new CompoundBorder(border, margin));
            tablePositions[x].setCursor(tablePositions[x].getCursor());
            tablePositions[x].addMouseListener(new MouseAdapter() {
                int nx = x;
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(nx != 4) {
                        if(tableNumbers[nx] == "X") {
                            tablePositions[nx].setForeground(Color.decode("#ff8f56"));
                        } else {
                            tablePositions[nx].setForeground(Color.decode("#aaaaaa"));
                        }
                    }
                    return;
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if(nx != 4) {
                        if(tableNumbers[nx] == "X") {
                            tablePositions[nx].setForeground(Color.decode("#5d13e7"));
                        } else {
                            tablePositions[nx].setForeground(Color.decode("#aaaaaa"));
                        }
                    }
                    return;
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(tableNumbers[nx] == "X") {
                        addElement(nx);
                    }
                    return;
                }
            });
            //adds jlabel to game jpanel
            gameWindow.add(tablePositions[x], gbc);
            // handles gridbaglayout constraints
            if(x < 8) {
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
        gbc.gridy++;
        gbc.ipady = 0;
        basePanel.add(gameButtons, gbc);

        // makes window visible
        cW.Frame.setVisible(true);
    }
}