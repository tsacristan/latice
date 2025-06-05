package latice.application;


import java.io.File;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import latice.controller.ControllerJouerGraphique;
import latice.controller.LaticeController;
import latice.view.gui.InterfaceJeu;
import latice.view.gui.LaticeVueGraphique;

public class LaticeGraphique extends Application { 
    
    
    @Override
    public void start(Stage primaryStage) {
        LaticeVueGraphique laticeVue = new LaticeVueGraphique();
        InterfaceJeu racine = laticeVue.interfaceJeu();

        Image image = new Image(getClass().getResource("/image2.png").toExternalForm());
        ImageView backgroundView = new ImageView(image);
        backgroundView.setPreserveRatio(false);
        backgroundView.setFitWidth(1920);
        backgroundView.setFitHeight(1080);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, racine);

        Scene scene = new Scene(root, 1920, 1080); // ✅ Utilise root ici

        scene.getStylesheets().add(getClass().getResource("/CSSTHEME.css").toExternalForm());

        primaryStage.setMaximized(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Latice");
        primaryStage.show();

        LaticeController controller = new LaticeController(laticeVue, new ControllerJouerGraphique(laticeVue));
        controller.demarrerJeu();
    }


    public static void main(String[] args) {
        launch(args);
    }
}