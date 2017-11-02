package es.orquesta;


import java.util.Objects;

public abstract class Instrumento {
        
    private final String nombre;

    public Instrumento(final String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String tocar(final NotaEnum nota);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrumento)) return false;
        Instrumento that = (Instrumento) o;
        return Objects.equals(getNombre(), that.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre());
    }
}
