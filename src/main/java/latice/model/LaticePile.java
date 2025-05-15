package latice.model;

import java.util.ArrayList;
import java.util.Collections;

import latice.model.material.Tuile;

public abstract class LaticePile extends ArrayList<Tuile> {
    public Tuile retirerTuile() {
        return remove(0);
    }

    public void ajouterTuile(Tuile tuile) {
        add(tuile);
    }
    
    public void melanger() {
        Collections.shuffle(this);
    }

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
