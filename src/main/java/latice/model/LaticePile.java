package latice.model;

import java.util.ArrayList;

public abstract class LaticePile extends ArrayList<Tuile> {
    public Tuile retirerTuile() {
        return remove(0);
    }

    public void ajouterTuile(Tuile tuile) {
        add(tuile);
    }
}
