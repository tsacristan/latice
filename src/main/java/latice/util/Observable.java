package latice.util;

import java.util.ArrayList;
import java.util.List;


public abstract class Observable<T> {
    protected List<T> listeners = new ArrayList<>();

    public void ajouterListener(T listener) {
        listeners.add(listener);
    }

    public List<T> listeners() {
        return listeners;
    }
    
    protected abstract void declencherListeners();
}
