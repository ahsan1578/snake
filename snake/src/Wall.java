/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * Date: 4/19/2019
 */

package snake.src;

import java.util.LinkedList;

public class Wall {

    private LinkedList<int[]> wall = new LinkedList<>();

    /**
     * Creates a Linkedlist with all the coordinates of wall
     *
     * @param d provides dimensions for wall
     */
    public Wall(Dimensions d) {
        LinkedList<LinkedList<Integer>> list = d.getWallDim();
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.get(i).get(0); j <= list.get(i).get(2); j++) {
                for (int k = list.get(i).get(1); k <= list.get(i).get(3); k++) {
                    int[] coOrdinates = {j, k};
                    wall.add(coOrdinates);
                }
            }
        }
    }

    /**
     * @return the linkedlist of wall coordinates
     */
    public LinkedList<int[]> getWall() {
        return wall;
    }

    /**
     * @param s snake
     * @return if the snake collided with wall
     */
    public boolean collideSnake(Snake s) {
        for (int i = 0; i < wall.size(); i++) {
            if (s.getBody().getLast()[0] == wall.get(i)[0] && s.getBody().getLast()[1] == wall.get(i)[1]) {
                return true;
            }
        }
        return false;
    }
}
