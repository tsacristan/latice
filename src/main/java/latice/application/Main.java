package latice.application;

import java.awt.Button;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		StackPane root = new StackPane();

        Button btn = new Button();


        Scene scene = new Scene(root, 600,250);

        primaryStage.setScene(scene);
        primaryStage.setTitle("");
        primaryStage.show();
	    
	}

	public static void main(String[] args) {
		launch(args);
	}
}
