package latice.application;

import latice.controller.LaticeController;
import latice.view.LaticeVue;
import latice.view.console.LaticeVueConsole;

public class LaticeApplication {
    public static void main(String[] args) {
    	LaticeVue vue = new LaticeVueConsole();
    	LaticeController controller = new LaticeController(vue, false);
    	
    	controller.demarrerJeu();
    }
}