package es.orquesta;

public class Percusion extends Instrumento {

    public Percusion(String instrumento) {
        super(instrumento);
    }

    @Override
    public String tocar(final NotaEnum nota) {
        return golpear() + "( " + nota + ")";
    }

    public String golpear() {
        return "golpeando el... "+getNombre();
    }

}
