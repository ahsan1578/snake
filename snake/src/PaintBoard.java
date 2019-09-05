/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * 5/4/2019
 */

package snake.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class PaintBoard extends JPanel implements KeyListener {
    private GameManager game;
    private int widthOfGame;
    private int heightOfGame;
    private String dir;
    private BufferedImage imageApple;
    private BufferedImage imageDot;
    private BufferedImage imageSnakeMouth;
    private BufferedImage imageGround;
    private BufferedImage imageBrick;
    private BufferedImage imageLife;
    private BufferedImage imageFrontPage;
    private boolean gameStarted;


    PaintBoard(GameManager game, boolean gameStarted){
        this.gameStarted = gameStarted;
        this.game = game;
        this.widthOfGame = game.getWidth();
        this.dir = "R";
        this.heightOfGame = game.getHeight();
        addKeyListener(this);
        try{
            imageApple = ImageIO.read(new File("Resources/apple.png"));
            imageDot = ImageIO.read(new File("Resources/OrbCropped.png"));
            imageGround= ImageIO.read(new File("Resources/gr6.jpg"));
            imageBrick = ImageIO.read(new File("Resources/rock4.png"));
            imageSnakeMouth = ImageIO.read(new File("Resources/dot6.png"));
            imageLife = ImageIO.read(new File("Resources/life6.png"));
            imageFrontPage = ImageIO.read(new File("Resources/front2.png"));


        }catch (Exception e){
            System.out.println("wrong image file");
        }
    }

    public int getWidthOfGame(){
        return (widthOfGame)*20;
    }

    public int getHeightOfGame(){
        return (heightOfGame)*20;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(imageGround,0,0,widthOfGame*20,heightOfGame*20,null);

        if(gameStarted){
            drawGame((Graphics2D)g);
        }else {
            g.setColor(new Color(58,150,22));
            g.fillRect(0,0,widthOfGame*20,heightOfGame*20);
            g.drawImage(imageFrontPage,0,0,widthOfGame*20,heightOfGame*20,null);

        }
        g.setColor(Color.red);
    }


    private void drawGame(Graphics2D g){
        String board = game.toString();
        String [] boardArray = board.split("\\n");
        int numStrings = boardArray.length;
        int stringLength = boardArray[0].length();
        for(int i = 0; i<numStrings; i++){
            for(int j = 0; j<stringLength; j++){
                if(boardArray[i].charAt(j)== 'S'){
                    g.drawImage(imageSnakeMouth,j*20,i*20,30,30,null);
                }else if(boardArray[i].charAt(j)=='s'){
                    g.setColor(Color.green);
                    g.drawImage(imageDot,(j)*20,(i)*20,30,30,null);
                }else if(boardArray[i].charAt(j)=='O'){
                    g.setColor(Color.blue);
                    g.drawImage(imageApple,(j)*20,(i)*20,30,30,null);
                }else if(boardArray[i].charAt(j)=='*'){
                    g.drawImage(imageBrick,(j)*20,(i)*20,30,30,null);
                }else if(boardArray[i].charAt(j)=='L'){
                    g.setColor(Color.MAGENTA);
                    g.drawImage(imageLife,j*20,i*20,40,40,null);
                }
            }
        }
    }

    public void keyPressed(KeyEvent ev){
        if(ev.getKeyCode() == KeyEvent.VK_DOWN && !dir.equals("U")){
            dir = "D";
        }else if(ev.getKeyCode() == KeyEvent.VK_UP && !dir.equals("D")){
            dir = "U";
        }else if(ev.getKeyCode() == KeyEvent.VK_LEFT && !dir.equals("R")){
            dir = "L";
        }if(ev.getKeyCode() == KeyEvent.VK_RIGHT && !dir.equals("L")){
            dir = "R";
        }
    }

    public void keyTyped(KeyEvent event){

    }

    public void keyReleased(KeyEvent event){

    }

    public void setGame(GameManager game){
        this.game = game;
    }

    public String getDir(){
        return this.dir;
    }

    public void setDir(String dir){
        this.dir = dir;
    }

    public void isGameStarted(boolean gameStarted){
        this.gameStarted = gameStarted;
    }
}