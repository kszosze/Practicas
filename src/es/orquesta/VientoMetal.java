package es.orquesta;

public class VientoMetal extends Viento {

    public VientoMetal(final String instrumento) {
        super(instrumento);
    }

    @Override
    public String tocar(NotaEnum nota) {
        return soplar() + " ( "+nota+")";
    }

    @Override
    public String soplar() {
        return "Soplando el metal del... " + getNombre();
    }
}
