package Model;

import Controller.InputState;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static final double GRAVITY = 0.4,
                                MOVE_SPEED = 4.0,
                                JUMP_STRENGTH = -10.0;
    private double playerX, playerY;
    private double playerVX, playerVY;
    private final double width = 30;
    private final double height = 50;
    private boolean grounded = false;

    private final List<Rectangle2D> platforms = new ArrayList<>();
    public GameModel(){
        playerX = 100;
        playerY = 300;
        playerVX = 0;
        playerVY = 0;
        initializePlatforms();
    }

    private void initializePlatforms(){
        platforms.add(new Rectangle2D(0,500,800,50));
        platforms.add(new Rectangle2D(50,350,150,20));
        platforms.add(new Rectangle2D(400,300,150,20));
    }

    public void update(InputState input){
        handleInput(input);
        applyPhysics();
        handleCollision();
    }

    private void handleInput(InputState input){
        if(input.isLeft()){
            playerVX = -MOVE_SPEED;
        }
        else if (input.isRight()) playerVX = MOVE_SPEED;
        else  playerVX = 0;
        if (input.isJump() && grounded){
            playerVY = JUMP_STRENGTH;
            grounded = false;
        }
    }

    private void applyPhysics(){
        playerVY += GRAVITY;
        playerX += playerVX;
        playerY += playerVY;
    }

    private void handleCollision(){
        grounded = false;
        Rectangle2D playerBounds = new Rectangle2D(playerX,playerY,width,height);
        for (Rectangle2D platform : platforms){
            if (playerBounds.intersects(platform)){
                double footLevel = (playerY - playerVY) + height;
                if (footLevel <= platform.getMinY()){
                    playerVY = 0;
                    playerY = platform.getMinY() - height;
                    grounded = true;
                }
            }
        }
    }
    public double getPlayerX(){
        return playerX;
    }

    public double getPlayerVX(){
        return playerVX;
    }

    public double getPlayerY(){
        return playerY;
    }

    public double getPlayerWidth(){
        return width;
    }

    public double getPlayerHeight(){
        return height;
    }

    public List<Rectangle2D> getPlatforms(){
        return platforms;
    }
}
