package View;

import Model.GameModel;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameView {
    private final GameModel model;
    public GameView(GameModel model){
        this.model = model;
    }

    public void render(GraphicsContext gc){
        //Background
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        //Platforms
        gc.setFill(Color.GREEN);
        for(Rectangle2D platform : model.getPlatforms()){
            System.out.println(platform.getMinX());
            gc.fillRect(
                    platform.getMinX(),
                    platform.getMinY(),
                    platform.getWidth(),
                    platform.getHeight()
                    );
        }
        //Player
        gc.setFill(Color.RED);
        gc.fillRect(
                model.getPlayerX(),
                model.getPlayerY(),
                model.getPlayerWidth(),
                model.getPlayerHeight()
        );
    }
}
