/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * 5/4/2019
 */

package snake.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SideBar extends JPanel {
    private BufferedImage grass;
    private BufferedImage snake;


    public SideBar(){
        try{
            this.grass = ImageIO.read(new File("Resources/grass.png"));
            this.snake = ImageIO.read(new File("Resources//snake2.png"));
        }catch (Exception e){
            System.out.println("Wrong image file");
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(new Color(240,240,255));
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(snake,0,getHeight()/2-50,getWidth()-10,getHeight()/2-5,null);
        g.drawImage(grass,0,getHeight()/2+10,getWidth(),getHeight()/2,null);
    }
}
