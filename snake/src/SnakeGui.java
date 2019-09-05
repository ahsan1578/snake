/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * 5/4/2019
 */

package snake.src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;



public class SnakeGui implements ActionListener {
    private GameManager game;
    private JFrame mainFrame;
    private PaintBoard p;
    private Timer t = new Timer(250,this);
    private boolean isPaused = false;
    private int totalScore;
    private boolean secondBoardOn = false;
    private boolean thirdBoardOn = false;
    private boolean fourthBoardOn = false;
    private CustomButton pauseButton;
    private CustomButton scoreButton;
    private CustomButton snakeSizeButton;
    private CustomButton gameOver;
    private CustomButton newGame;
    private CustomButton exitButton;
    private boolean gameStarted = false;
    private Clip click;
    private Clip eat;
    private Clip die;
    private Clip backGround;

    public SnakeGui(){
        System.out.println(new File(System.getProperty("user.dir")).getPath());
        game = new GameManager("Resources/maze1.txt");
        mainFrame = new JFrame("Snake v1.0");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBackground(Color.BLACK);

        totalScore = 0;

        AudioInputStream buttonSound;
        AudioInputStream deathSound;
        AudioInputStream eatSound;
        AudioInputStream backGroundSound;


        try {
            buttonSound = AudioSystem.getAudioInputStream(new File("Resources/click.wav"));
            click = AudioSystem.getClip();
            click.open(buttonSound);

            eatSound = AudioSystem.getAudioInputStream(new File("Resources/eat2.wav"));
            eat = AudioSystem.getClip();
            eat.open(eatSound);

            deathSound = AudioSystem.getAudioInputStream(new File("Resources/die.wav"));
            die = AudioSystem.getClip();
            die.open(deathSound);

            backGroundSound = AudioSystem.getAudioInputStream(new File("Resources/back.wav"));
            backGround = AudioSystem.getClip();
            backGround.open(backGroundSound);

        }catch (Exception e){
            System.out.println("Wrong audio file");
        }

        backGround.loop(Clip.LOOP_CONTINUOUSLY);

        this.p = new PaintBoard(game, gameStarted);
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(p.getWidthOfGame(),p.getHeightOfGame()));
        mainFrame.add(p);



        pauseButton = new CustomButton("Resources/playPause.png", "Start", 300,100);
        pauseButton.setPreferredSize(new Dimension(300,100));

        pauseButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                click.start();
                click.setFramePosition(0);
                if(!gameStarted){
                    backGround.stop();
                    gameStarted = true;
                    t.start();
                    p.isGameStarted(gameStarted);
                    pauseButton.setText("Pause");
                    pauseButton.setTextX(pauseButton.getButtonWidth()/4);
                    p.requestFocusInWindow();
                }else if(!game.isGameOver() && !isPaused){
                    t.stop();
                    backGround.loop(Clip.LOOP_CONTINUOUSLY);
                    pauseButton.setTextX(pauseButton.getButtonWidth()/3-5);
                    pauseButton.setText("Play");
                    pauseButton.repaint();
                    isPaused = true;
                    pauseButton.requestFocusInWindow();
                }else if(!game.isGameOver() && isPaused){
                    t.start();
                    backGround.stop();
                    pauseButton.setText("Pause");
                    pauseButton.setTextX(pauseButton.getButtonWidth()/4);
                    isPaused = false;
                    pauseButton.repaint();
                    p.requestFocusInWindow();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.scoreButton = new CustomButton("Resources/scoreBtn.png", "Score "+
                game.getScore(),300,100);
        scoreButton.setPreferredSize(new Dimension(300,100));
        scoreButton.setFontSize(50f);
        scoreButton.setTextX(scoreButton.getButtonWidth()/6);

        this.snakeSizeButton = new CustomButton("Resources/scoreBtn.png","Size of Snake "
                +game.getSnakeSize(),300,100);
        snakeSizeButton.setPreferredSize(new Dimension(300,100));
        snakeSizeButton.setFontSize(33f);
        snakeSizeButton.setTextX(snakeSizeButton.getButtonWidth()/7);
        snakeSizeButton.setTextY(snakeSizeButton.getButtonHeight()*3/5+4);

        SideBar sideBar = new SideBar();
        sideBar.setPreferredSize(new Dimension(350,p.getHeightOfGame()));

        sideBar.add(pauseButton);
        sideBar.add(scoreButton);
        sideBar.add(snakeSizeButton);

        mainFrame.add(sideBar,BorderLayout.EAST);

        gameOver = new CustomButton("Resources/gameOver.png","Game Over! "+"Score "+totalScore,
                700,400);
        gameOver.setPreferredSize(new Dimension(700,400));
        gameOver.setTextX(gameOver.getButtonWidth()/8-10);
        gameOver.setTextY(gameOver.getButtonHeight()/2+10);
        gameOver.setVisible(false);
        p.add(gameOver, BorderLayout.CENTER);

        newGame = new CustomButton("Resources/gameOver.png","New Game",
                300,100);
        newGame.setPreferredSize(new Dimension(300,100));
        newGame.setFontSize(50f);
        newGame.setTextX(newGame.getButtonWidth()/6-10);
        newGame.setTextY(newGame.getButtonHeight()*3/5+3);
        newGame.setVisible(false);
        p.add(newGame, BorderLayout.CENTER);

        exitButton = new CustomButton("Resources/gameOver.png","Exit",
                300,100);
        exitButton.setFontSize(50f);
        exitButton.setTextX(exitButton.getButtonWidth()/3+6);
        exitButton.setTextY(exitButton.getButtonHeight()*3/5+3);
        exitButton.setPreferredSize(new Dimension(300,100));
        exitButton.setVisible(false);
        p.add(exitButton, BorderLayout.SOUTH);


        mainFrame.pack();
        mainFrame.setVisible(true);
        p.requestFocusInWindow();
    }

    public void actionPerformed(ActionEvent e){
        if(game.isGameOver()){
            backGround.loop(Clip.LOOP_CONTINUOUSLY);
            die.start();
            die.setFramePosition(0);
            t.stop();
            gameOver.setVisible(true);
            newGame.setVisible(true);
            exitButton.setVisible(true);
            newGame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    click.start();
                    click.setFramePosition(0);
                    backGround.stop();
                    totalScore = 0;
                    game = new GameManager("Resources/maze1.txt");
                    p.setGame(game);
                    p.setDir("R");
                    secondBoardOn = false;
                    thirdBoardOn = false;
                    fourthBoardOn = false;
                    gameOver.setVisible(false);
                    newGame.setVisible(false);
                    exitButton.setVisible(false);
                    t.setDelay(250);

                    t.start();
                    newGame.removeMouseListener(this);

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            exitButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    click.start();
                    click.setFramePosition(0);
                    System.exit(0);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        if(game.isNewScore()){
            eat.start();
            eat.setFramePosition(0);
            totalScore++;
        }

        gameOver.setText("Game Over! "+"Score "+totalScore);
        game.update(p.getDir());
        scoreButton.setText("Score "+totalScore);
        snakeSizeButton.setText("Size of Snake "+game.getSnakeSize());
        if(game.getScore()>=15){
            t.setDelay(100);
        }else if(game.getScore()>=10){
            t.setDelay(150);
        }else if(game.getScore()>=5){
            t.setDelay(200);
        }
        if(totalScore>=20 && !secondBoardOn){
            this.game = new GameManager("Resources/maze2.txt");
            p.setDir("R");
            p.setGame(game);
            t.setDelay(250);
            secondBoardOn =true;
        }else if(totalScore>=40 && !thirdBoardOn){
            this.game = new GameManager("Resources/maze3.txt");
            p.setDir("R");
            p.setGame(game);
            t.setDelay(250);
            thirdBoardOn =true;
        }else if(totalScore>=60 && !fourthBoardOn){
            this.game = new GameManager("Resources/maze4.txt");
            p.setDir("R");
            p.setGame(game);
            t.setDelay(250);
            fourthBoardOn =true;
        }
        mainFrame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new SnakeGui();
            }
        });
    }
}

