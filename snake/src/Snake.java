/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * Date: 4/19/2019
 */

package snake.src;

import java.util.*;

public class Snake {
    private LinkedList<int[]> body = new LinkedList<>();
    private boolean foodEaten;
    private String currDir = "";

    /**
     * Create a snake somewhere where there is no wall
     *
     * @param d Gives the board dimension
     * @param w Gives the wall coordinates
     */

    public Snake(Dimensions d, Wall w) {
        GenRandomSnakePos g = new GenRandomSnakePos(d, w);
        g.getSnakeBody();
        this.body = g.getBody();

    }

    /**
     * @return a linkedlist with the coordinates of snake body. The first element in the list
     * is the tail coordinate and the lass element is the head coordinate
     */
    public LinkedList<int[]> getBody() {
        return body;
    }

    /**
     * Use the generic move() method to move up
     *
     * @param f food coordinate
     * @param d dimension of the board
     */
    public void moveUp(Food f, Dimensions d) {
        if (!currDir.equals("DOWN")) {
            move(f, 0, -1, d);
            currDir = "UP";
        }
    }

    /**
     * Use the generic move() method to move down
     *
     * @param f food coordinate
     * @param d dimension of the board
     */
    public void moveDown(Food f, Dimensions d) {
        if (!currDir.equals("UP")) {
            move(f, 0, 1, d);
            currDir = "DOWN";
        }
    }

    /**
     * Use the generic move() method to move left
     *
     * @param f food coordinate
     * @param d dimension of the board
     */
    public void moveLeft(Food f, Dimensions d) {
        if (!currDir.equals("RIGHT")) {
            move(f, -1, 0, d);
            currDir = "LEFT";
        }
    }

    /**
     * Use the generic move() method to move right
     *
     * @param f food coordinate
     * @param d dimension of the board
     */
    public void moveRight(Food f, Dimensions d) {
        if (!currDir.equals("LEFT")) {
            move(f, 1, 0, d);
            currDir = "RIGHT";
        }
    }

    /**
     * Generic move method, move the snake and also if food was eaten and assigns a value
     * to boolean foodeaten instance variable
     *
     * @param f          food coordinate
     * @param incrementX increase or decrease in x coordinate of snake
     * @param incrementY increase or decrease in y coordinate of snake
     * @param d          dimension of board
     */
    private void move(Food f, int incrementX, int incrementY, Dimensions d) {
        int x = getBody().getLast()[0];
        int y = getBody().getLast()[1];
        int[] newCoOrdinate = {x + incrementX, y + incrementY};
        if (newCoOrdinate[0] < 0) {
            newCoOrdinate[0] = d.getBoardDim().getFirst() - 1;
        } else if (newCoOrdinate[0] >= d.getBoardDim().getFirst()) {
            newCoOrdinate[0] = 0;
        } else if (newCoOrdinate[1] < 0) {
            newCoOrdinate[1] = d.getBoardDim().getLast() - 1;
        } else if (newCoOrdinate[1] >= d.getBoardDim().getLast()) {
            newCoOrdinate[1] = 0;
        }
        if (newCoOrdinate[0] != f.getFoodX() || newCoOrdinate[1] != f.getFoodY()) {
            body.removeFirst();
            foodEaten = false;
        } else {
            foodEaten = true;
        }
        body.add(newCoOrdinate);
    }

    /**
     * @return if the snake collided with itself
     */
    public boolean selfCollision() {
        for (int i = 0; i < getBody().size() - 1; i++) {
            if (getBody().get(i)[0] == getBody().getLast()[0] && getBody().get(i)[1] == getBody().getLast()[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean gotLife(Life l) {
        if (body.getLast()[0] == l.getLifeX() && body.getLast()[1] == l.getLifeY()) {
            body.removeLast();
            body.removeLast();
            return true;
        }
        return false;
    }

    /**
     * @return is the food eaten
     */
    public boolean isFoodEaten() {
        return foodEaten;
    }
}
