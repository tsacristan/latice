package latice.application;

import latice.controller.LaticeController;
import latice.controller.ControllerJouerConsole;
import latice.view.console.LaticeVueConsole;

public class LaticeApplication {
    public static void main(String[] args) {
    	LaticeVueConsole vue = new LaticeVueConsole();
    	LaticeController controller = new LaticeController(vue, new ControllerJouerConsole(vue));
    	
    	controller.demarrerJeu();
    }
}