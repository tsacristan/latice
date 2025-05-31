package latice.application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import latice.controller.ControllerJouerGraphique;
import latice.controller.LaticeController;
import latice.view.gui.InterfaceJeu;
import latice.view.gui.LaticeVueGraphique;

public class LaticeGraphique extends Application { 
    
    @Override
    public void start(Stage primaryStage) {
    	LaticeVueGraphique laticeVue = new LaticeVueGraphique();
        InterfaceJeu root = laticeVue.interfaceJeu();
        
        primaryStage.setFullScreen(true);
        Scene scene = new Scene(root,1920,1005);
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