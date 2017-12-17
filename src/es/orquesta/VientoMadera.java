package es.orquesta;

public class VientoMadera extends Viento {

    public VientoMadera(final String instrumento) {
        super(instrumento);
    }

    @Override
    public String tocar(NotaEnum nota) {
        return soplar() + "( " + nota + ")";
    }


    @Override
    public String soplar() {
        return "Soplando la madera del....  " + getNombre();
    }
}
