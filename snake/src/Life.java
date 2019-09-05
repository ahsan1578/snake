/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * 5/4/2019
 */

package  snake.src;

public class Life {
    private int LifeX;
    private int LifeY;

    public Life(int [] LifePosition){
        this.LifeX = LifePosition[0];
        this.LifeY = LifePosition[1];
    }

    public int getLifeX(){
        return LifeX;
    }

    public int getLifeY(){
        return LifeY;
    }

}
