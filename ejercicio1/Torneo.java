package ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Torneo {
    
    ArrayList<String> participantesinscritos=  new ArrayList<>();

    public abstract BinaryTree generarPartido(String ubicacion, LocalDate fecha, char op);

    public abstract void inscribirequipo(String equipo) throws IllegalArgumentException;

}
