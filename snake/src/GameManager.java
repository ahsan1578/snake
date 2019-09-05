/**
 * @author D M Raisul Ahsan
 * @version 1.0
 * Date: 4/19/2019
 */

package snake.src;

import java.util.LinkedList;

public class GameManager {
    private Dimensions d;
    private Wall w;
    private Snake s;
    private GenRandomFoodPosition gr;
    private int height;
    private int width;
    private char[][] board;
    private Food f;
    private boolean gameOver = false;
    private int score = 0;
    private Life l;
    private boolean isLifeAvailable = false;
    private boolean isLifeTaken = false;
    private int snakeSize;
    private boolean newScore;



    /**
     * Create all the components of the game
     * @param s is the name of the file used to get dimensions of board and wall
     */
    public GameManager(String s){
        this.d = new Dimensions(s);
        this.w = new Wall(d);
        this.s = new Snake(d,w);
        this.gr = new GenRandomFoodPosition(this.d,this.s,this.w);
        this.f = new Food(gr.getFoodPosition());
        this.width = d.getBoardDim().getFirst();
        this.height = d.getBoardDim().getLast();
        this.board = new char[width][height];
        this.snakeSize = this.s.getBody().size();
        this.newScore = false;
    }

    /**
     * Updates snake position to a given direction, check the outcome
     * @param dirction given direction
     */
    public void update(String dirction){
        if(dirction.equals("U")){
            s.moveUp(f,d);
        }else if(dirction.equals("D")){
            s.moveDown(f,d);
        }else if(dirction.equals("R")){
            s.moveRight(f,d);
        }else if(dirction.equals("L")){
            s.moveLeft(f,d);
        }

        if(s.selfCollision() || w.collideSnake(s)){
            gameOver = true;
            this.score = 0;
            System.out.println("Game Over");
        }else {
            gameOver = false;
        }

        if(score!= 0 && score%3 == 0 && !isLifeAvailable && !isLifeTaken){
            this.l = new Life(new GenRandomFoodPosition(this.d,this.s,this.w).getFoodPosition());
            isLifeAvailable = true;
        }

        if(isLifeAvailable && s.gotLife(l)){
            isLifeAvailable = false;
            isLifeTaken = true;
            snakeSize-=2;
        }

        if(score%3 != 0){
            isLifeTaken = false;
            isLifeAvailable = false;
        }

        if(s.isFoodEaten()){
            f = new Food(gr.getFoodPosition());
            this.score++;
            snakeSize++;
            newScore = true;
        }else {
            newScore = false;
        }
    }

    /**
     * Provides char values to the components
     */
    private void makeBoard(){
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                board[i][j] = '.';
            }
        }

        for(int i = 0; i<w.getWall().size(); i++){
            board[w.getWall().get(i)[0]][w.getWall().get(i)[1]] = '*';
        }

        if(isLifeAvailable){
            board[l.getLifeX()][l.getLifeY()] = 'L';
        }

        board[f.getFoodX()][f.getFoodY()] = 'O';

        LinkedList<int[]> body = s.getBody();

        for(int i = 0; i<body.size()-1; i++){
            board[body.get(i)[0]][body.get(i)[1]] = 's';
        }

        board[body.getLast()[0]][body.getLast()[1]] = 'S';
    }

    /**
     * @return string representation of the board
     */
    public String toString(){
        makeBoard();
        String s = "";
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                s = s + board[j][i];
            }
            s = s + "\n";
        }
        return s;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getScore(){
        return score;
    }

    public int getSnakeSize(){
        return snakeSize;
    }

    public boolean isNewScore(){
        return newScore;
    }
}
