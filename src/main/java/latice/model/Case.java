package latice.model;

public class Case {
    private Tuile tuile;
    private final TypeCase typeCase;

    public Case(Tuile tuile, TypeCase typeCase) {
        this.tuile = tuile;
        this.typeCase = typeCase;
    }

    public Case(Tuile tuile) {
        this(tuile, null);
    }

    public Case(TypeCase typeCase) {
        this(null, typeCase);
    }

    public Case() {
        this(null, null);
    }

    public Tuile tuile() {
        return tuile;
    }

    public TypeCase typeCase() {
        return typeCase;
    }

    @Override
    public String toString() {
        return "Case{" +
                "tuile=" + tuile +
                ", typeCase=" + typeCase +
                '}';
    }
}
