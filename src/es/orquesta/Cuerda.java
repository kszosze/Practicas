package es.orquesta;

public class Cuerda extends Instrumento {

    public Cuerda(String instrumento) {
        super(instrumento);
    }

    @Override
    public String tocar(NotaEnum nota) {
        return vibrar() + " ( "+ nota +")";
    }

    public String vibrar() {
        return "vibrando la cuerda del... "+ getNombre();
    }

}
