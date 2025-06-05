package latice.application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
        InterfaceJeu interfaceJeu = laticeVue.interfaceJeu();

        Image image = new Image(getClass().getResource("/image2.png").toExternalForm());
        ImageView backgroundView = new ImageView(image);
        backgroundView.setPreserveRatio(false);
        backgroundView.setFitWidth(1920);
        backgroundView.setFitHeight(1080);

        StackPane racine = new StackPane();
        racine.getChildren().addAll(backgroundView, interfaceJeu);

        Scene scene = new Scene(racine, 1920, 1080);

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
