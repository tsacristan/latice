package latice.view.gui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import latice.model.player.Joueur;
import latice.view.Textes;
import latice.view.TextesErreurs;

public class Boutique extends Alert {

    public Boutique() {
        super(AlertType.CONFIRMATION);
        setTitle("Boutique");
    }

    public void ouvrir(Joueur joueur) {

        setAlertType(AlertType.CONFIRMATION);
        setHeaderText(Textes.ACHETER_COUP_SUPPLEMENTAIRE.toString());
        String contenuTexte = String.format(Textes.POINTS_JOUEURS.texte(), joueur.points());
        setContentText(contenuTexte);

        Optional<ButtonType> response = showAndWait();
        if (response.isPresent() && response.get() == ButtonType.OK) {
            if (joueur.points() >= 2) {
                joueur.ajouterPoints(-2);
                joueur.changerNombreCoups(joueur.nombreCoups() + 1);

                setAlertType(AlertType.INFORMATION);
                setTitle("Succès");
                setHeaderText(null);
                setContentText(Textes.ACHAT_ACTION_SUPPLEMENTAIRE_SUCCES.toString());
                showAndWait();
            } else {
                setAlertType(AlertType.ERROR);
                setTitle("Erreur");
                setHeaderText(null);
                setContentText(TextesErreurs.ACHAT_ACTION_SUPPLEMENTAIRE_ECHEC.toString());
                showAndWait();
            }
        }
    }
}