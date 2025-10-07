package org.example;

import Controller.GameController;
import Controller.InputState;
import Model.GameModel;
import View.GameView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    private static final int WIN_WIDTH = 800;
    private static final int WIN_HEIGHT = 600;

    public static void main(String[] args) {
        System.out.print("Launching Application...\n");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //MVC
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        GameController controller = new GameController(model);

        //UI
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIN_WIDTH, WIN_HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root);

        //Event Handling
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> controller.handleKey(event.getCode()));
        canvas.setOnKeyReleased(event -> controller.handleRelease(event.getCode()));

        //Game Loop
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                InputState currentState = controller.getCurrentInputState();
                model.update(currentState);
                view.render(gc);
                //System.out.println(currentState);
            }
        };
        loop.start();
        stage.setTitle("Input Test");
        stage.setScene(scene);
        stage.show();
        canvas.requestFocus();
    }
}