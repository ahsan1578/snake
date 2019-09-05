sSnake
Author: D M Raisul Ahsan
Date: 5/5/2019

Game play:

The snake starts with an initial length. Move the snake using keyboard button UP(go up), DOWN(go down), RIGHT(go right), LEFT(go left).
The target is to move the snake and eat food. Eating food everytime increases the score by 1.
The snake dies if it hits itself or hit the walls. The snake can move through from one end and come back from another end if there is no
wall. 
There are four levels of the game. After every 20 score you reach the next level. 

Classes used in the game are:

1.Snake
2.Wall
3.Dimensions
4.GenRandomFoodPosition
5.GenRandomSnakePosition
6.Food
7.GameManager
8.Life
9.SideBar
10.CustomButton
11.PaintBoard
12.SnakeGui

Most of the classes are explained by the comments.
In short, Snake is a linkedlist of coordinates. Moving snake add one coordinate at the head and removes one from the end.
Wall and Dimensions gives the linkedlists of board size and wall coordinates.
All the processes are gathered in GameManager. After every snake move the evaluation is done if the head of the snake has same coordinate
as the any of the wall or snake body coordinates which is found yes means death and game over.
Same process goes on with the food coordinate to check if food is eaten and score is increased.
Life is a class that makes something like food which, if eaten, doesn't increase the score, but decrease snake size.

Extras:

1. Speacial food to decrease snake size.
2. Custom background
3. A lot of graphic elements to make the game as realistic as possible.
4. 3 different pages on mainframe, one before game starts, another during the game, other after the snake dies
5. Ability to restart the game or exit after game over clicking a game button.
6. DIfferent sounds for different actions and backgorund music.
7. Custom made buttons from CustomButton class which can be used for other purposes than this game.
8. Custom font
9. Fancy outlook
10. Many more which can be understood playing the game.

Things that I couldn't do, but I had in mind:
1. jar doesn't work if take outside the folder with classes. I wanted to fix this.
2. More realistic snake
3. Moving wall
4. Adding high score
5. Make the outlook more 3d
6. Adding tunnels which would both increase score and decrease snake length.
