package es.orquesta;

public abstract class Viento extends Instrumento {

    public Viento(final String instrumento) {
        super(instrumento);
    }

    public abstract String soplar();

}
