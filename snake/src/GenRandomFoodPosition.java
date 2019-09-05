/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * Date: 4/19/2019
 */

package snake.src;

import java.util.LinkedList;
import java.util.Random;
public class GenRandomFoodPosition{
    private Dimensions d;
    private Snake s;
    private Wall w;
    private int[] foodposition = new int[2];
    private Random r1 = new Random();
    private Random r2 = new Random();

    /**
     * Create a random numbers generator cosidering that they don't collide with snake or wall
     * @param d dimensions
     * @param s snake
     * @param w wall
     */
    public GenRandomFoodPosition(Dimensions d, Snake s, Wall w){
        this.s = s;
        this.d = d;
        this.w = w;
    }

    /**
     * @return food coordinates using the 2 different seeds for x and y
     */
    public int[] getFoodPosition(){

        int x = 0;
        int y = 0;

        LinkedList<int[]> snakeBody = s.getBody();
        LinkedList<int[]> wall = w.getWall();

        boolean validPosition = false;

        while (!validPosition){
            validPosition = true;
            x = r1.nextInt(d.getBoardDim().get(0)-5);
            y = r2.nextInt(d.getBoardDim().get(1)-5);

            if(!checkValidPosition(snakeBody,x,y)||!checkValidPosition(wall,x,y)){
                validPosition = false;
            }
        }

        foodposition[0] = x;
        foodposition[1] = y;

        return foodposition;
    }

    /**
     * Check is the x and y generated is valid(doesn't collide with snake or wall)
     * This method is used in the getFoodPosition for check the validity
     * @param list list of wall or snake whichever passed
     * @param x x coordinate
     * @param y y coordinate
     * @return is the x, y are valid
     */
    private boolean checkValidPosition(LinkedList<int[]> list, int x, int y){
        boolean validPos = true;
        for(int i = 0; i<list.size(); i++){
            if(x == list.get(i)[0] && y == list.get(i)[1]){
                validPos = false;
            }
        }
        return  validPos;
    }
}