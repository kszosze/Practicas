package es.orquesta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orquesta {

    enum TipoInstrumento { VIENTO_METAL, VIENTO_MADERA, PERCUSION, CUERDA; };

    private final Set<Instrumento> conjuntoInstrumentos= new HashSet();
    
   
    public void addInstrumentoViento(final String nombre, final NotaEnum nota, final TipoInstrumento tipo) {
        switch (tipo) {
            case VIENTO_METAL : conjuntoInstrumentos.add( new VientoMetal(nombre)); break;
            case VIENTO_MADERA : conjuntoInstrumentos.add( new VientoMadera(nombre)); break;
            case PERCUSION : conjuntoInstrumentos.add( new Percusion(nombre)); break;
            case CUERDA : conjuntoInstrumentos.add( new Cuerda(nombre)); break;
        }
    }
    
    public String conjuntoinstrumentos(final List<? extends Instrumento> l) {
        final StringBuilder sb = new StringBuilder();
        for (Instrumento I : l) {
            sb.append(I.tocar(NotaEnum.DO));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
