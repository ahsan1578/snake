/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * 5/4/2019
 */

package snake.src;

import java.util.LinkedList;
import java.util.Random;

public class GenRandomSnakePos {
    private Dimensions d;
    private Wall w;
    private LinkedList<int[]> snakeBody;
    private Random r1;
    private Random r2;

    /**
     * This class works same as the GenRandomFoodPos
     * Only it check multiple points from snake body with wall rather than 1 point
     */

    public GenRandomSnakePos(Dimensions d, Wall w){
        this.d = d;
        this.w = w;
        this.snakeBody = new LinkedList<>();
        this.r1 = new Random();
        this.r2 = new Random();
    }


    public LinkedList<int[]> getSnakeBody(){
        makeSnakeBody();

        while (!checkValidPosition(w.getWall(),this.snakeBody)){
            makeSnakeBody();
        }

        return snakeBody;
    }


    private boolean checkValidPosition(LinkedList<int[]> list, LinkedList<int[]> snake){
        boolean validPos = true;
        for(int i = 0; i<list.size(); i++){
            for(int j = 0; j<snake.size(); j++){
                if(snake.get(j)[0] == list.get(i)[0] && snake.get(j)[1] == list.get(i)[1]){
                    validPos = false;
                    break;
                }
            }
            if(!validPos){
                break;
            }
        }
        return  validPos;
    }


    public void makeSnakeBody(){
        snakeBody.clear();
        int x = r1.nextInt(d.getBoardDim().get(0)-15);
        int y = r2.nextInt(d.getBoardDim().get(1)-2);

        for(int i = 1; i<8; i++){
            int [] arr = {x,y};
            snakeBody.add(arr);
            x = x + 1;
        }
    }

    public LinkedList<int[]> getBody(){
        return snakeBody;
    }

}
