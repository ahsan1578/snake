/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * Date: 5/4/2019
 */
package snake.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CustomButton extends JComponent{
    private BufferedImage imagePausePlay;
    private String imageName;
    private String text;
    private Font customFont;
    private int width;
    private int height;
    private String fontFileName;
    private Color textColor;
    private float fontSize;
    private int textX;
    private int textY;

    public CustomButton(String pathName, String text, int width, int height){
        this.imageName = pathName;
        this.text = text;
        this.width = width;
        this.height = height;
        this.fontFileName = "Resources/Lobster.otf";
        this.textColor = Color.WHITE;
        this.fontSize = 65f;
        this.textX = width/4;
        this.textY = height*7/10;
    }

    public void paintComponent(Graphics g){
        try {
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFileName)).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }catch (Exception e){
            System.out.println("Wrong font file");
        }
        try{
            imagePausePlay = ImageIO.read(new File(imageName));
        }catch (Exception e){
            System.out.println("Wrong Image File");
        }
        g.drawImage(imagePausePlay, 0, 0, width, height, null);
        g.setColor(textColor);
        g.setFont(customFont);
        g.drawString(text, textX,textY);
    }

    public void setImage(String imageName){
        this.imageName = imageName;
    }

    public void setText(String txt){
        this.text = txt;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setCustomFont(String fontFileName){
        this.fontFileName = fontFileName;
    }

    public void setTextColor(Color color){
        this.textColor = color;
    }

    public void setFontSize(float size){
        this.fontSize = size;
    }

    public void setTextX(int x){
        this.textX = x;
    }

    public void setTextY(int y){
        this.textY = y;
    }

    public int getButtonWidth(){
        return width;
    }

    public int getButtonHeight(){
        return height;
    }
}