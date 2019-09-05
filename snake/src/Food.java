/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * Date: 4/19/2019
 */

package snake.src;

public class Food {
    private int foodX;
    private int foodY;

    /**
     * create a food at a given coordinate
     * @param foodPosition the position array with coordinates
     */
    public Food(int[] foodPosition){
        this.foodX = foodPosition[0];
        this.foodY = foodPosition[1];
    }

    /**
     * @return gives the food x-coordinate
     */
    public int getFoodX(){
        return foodX;
    }

    /**
     * @return gives the food y-coordinate
     */
    public int getFoodY(){
        return foodY;
    }
}
