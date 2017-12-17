package ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TorneoPorGruposTest {

    @Test
    public void testInscribirequipo() {
        Torneo grupos = new TorneoPorGrupos();
        String[] equiposainsertar = {"A", "B", "C", "D", "E", "F"};
        ArrayList<String> result = new ArrayList<>();
        String[] expResult = new String[6];
        for (int i = 0; i < equiposainsertar.length; i++) {
            expResult[i] = equiposainsertar[i];
        }
        for (int i = 0; i < equiposainsertar.length; i++) {
            grupos.inscribirequipo(equiposainsertar[i]);
        }
        result = grupos.participantesinscritos;
        assertArrayEquals(expResult, result.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInscribirequipoException() {
        Torneo grupos = new TorneoPorGrupos();
        String[] equiposainsertar = {"A", "B", "A", "D", "E", "F"};
        ArrayList<String> result = new ArrayList<>();
        String[] expResult = new String[6];

        for (int i = 0; i < equiposainsertar.length; i++) {
            expResult[i] = equiposainsertar[i];
        }
        for (int i = 0; i < equiposainsertar.length; i++) {
            grupos.inscribirequipo(equiposainsertar[i]);
        }

        result = grupos.participantesinscritos;
        assertArrayEquals(expResult, result.toArray());
    }

    @Test
    public void testgenerarPartidoAleatorio() {
        List<String> partidos = Arrays.asList("AB", "AC", "AD", "AE", "AF", "AG", "AH",
                "BA", "BC", "BD", "BE", "BF", "BG", "BH",
                "CA", "CB", "CD", "CE", "CF", "CG", "CH",
                "DA", "DB", "DC", "DE", "DF", "DG", "DH",
                "EA", "EB", "EC", "ED", "EF", "EG", "EH",
                "FA", "FB", "FC", "FD", "FE", "FG", "FH",
                "GA", "GB", "GC", "GD", "GE", "GF", "GH",
                "HA", "HB", "HC", "HD", "HE", "HF", "HG");
        String[] copyresult = new String[20];

        LocalDate fecha;
        ArrayList<Partido> result = new ArrayList<>();
        TorneoPorGrupos grupos = new TorneoPorGrupos();

        grupos.inscribirequipo("A cabeza de serie");
        grupos.inscribirequipo("B");
        grupos.inscribirequipo("C");
        grupos.inscribirequipo("D");
        grupos.inscribirequipo("E cabeza de serie");
        grupos.inscribirequipo("F");
        grupos.inscribirequipo("G");
        grupos.inscribirequipo("H");

        fecha = LocalDate.now();

        result = grupos.generarPartido("Coruña", fecha, 'a');
        copyresult[0] = result.get(0).getParticipanteLocal() + result.get(0).getParticipanteVisitante();
        copyresult[1] = result.get(1).getParticipanteLocal() + result.get(1).getParticipanteVisitante();
        copyresult[2] = result.get(2).getParticipanteLocal() + result.get(2).getParticipanteVisitante();
        copyresult[3] = result.get(3).getParticipanteLocal() + result.get(3).getParticipanteVisitante();

        assertTrue(partidos.contains(copyresult[0]));
        assertTrue(partidos.contains(copyresult[1]));
        assertTrue(partidos.contains(copyresult[2]));
        assertTrue(partidos.contains(copyresult[3]));

    }

    @Test
    public void testgenerarPartidoCabezadeserie() {
        List<String> partidos = Arrays.asList("A cabeza de serieB", "A cabeza de serieC", "A cabeza de serieD", 
                                              "A cabeza de serieE cabeza de serie", "A cabeza de serieF", "A cabeza de serieG", "A cabeza de serieH",
                "BA cabeza de serie", "BC", "BD", "BE cabeza de serie", "BF", "BG", "BH",
                "CA cabeza de serie", "CB", "CD", "CE cabeza de serie", "CF", "CG", "CH",
                "DA cabeza de serie", "DB", "DC", "DE cabeza de serie", "DF", "DG", "DH",
                "E cabeza de serieA cabeza de serie", "E cabeza de serieB", "E cabeza de serieC", 
                "E cabeza de serieD", "E cabeza de serieF", "E cabeza de serieG", "E cabeza de serieH",
                "FA cabeza de serie", "FB", "FC", "FD", "FE cabeza de serie", "FG", "FH",
                "GA cabeza de serie", "GB", "GC", "GD", "GE cabeza de serie", "GF", "GH",
                "HA cabeza de serie", "HB", "HC", "HD", "HE cabeza de serie", "HF", "HG");
        String[] copyresult = new String[20];

        LocalDate fecha;
        ArrayList<Partido> result = new ArrayList<>();
        TorneoPorGrupos grupos = new TorneoPorGrupos();

        grupos.inscribirequipo("A cabeza de serie");
        grupos.inscribirequipo("E cabeza de serie");
        grupos.inscribirequipo("B");
        grupos.inscribirequipo("C");
        grupos.inscribirequipo("D");
        grupos.inscribirequipo("F");
        grupos.inscribirequipo("G");
        grupos.inscribirequipo("H");

        fecha = LocalDate.now();

        result = grupos.generarPartido("Coruña", fecha, 'c');
        copyresult[0] = result.get(0).getParticipanteLocal() + result.get(0).getParticipanteVisitante();
        copyresult[1] = result.get(1).getParticipanteLocal() + result.get(1).getParticipanteVisitante();
        copyresult[2] = result.get(2).getParticipanteLocal() + result.get(2).getParticipanteVisitante();
        copyresult[3] = result.get(3).getParticipanteLocal() + result.get(3).getParticipanteVisitante();

        
        assertFalse(copyresult.toString().contains(partidos.get(3)));
        assertFalse(copyresult.toString().contains(partidos.get(28)));
    }

    @Test
    public void seguimientoTest() {
        String[] arraycheck = new String[20];
        TorneoPorGrupos grupos = new TorneoPorGrupos();
        ArrayList<Partido> result = new ArrayList<>();
        String[] copyresult = new String[20];

        ArrayList<Partido> antecesores = new ArrayList<>();
        String[] copyantecesores = new String[20];
        String[] antecesorescheck = new String[20];

        LocalDate fecha1, fecha2;

        fecha1 = LocalDate.now();

        arraycheck[0] = "A" + "B";
        arraycheck[1] = "C" + "D";
        arraycheck[2] = "E" + "F";
        arraycheck[3] = "G" + "H";
        fecha1 = fecha1.plusDays(4);
        arraycheck[4] = fecha1 + "Coruña";
        fecha1 = fecha1.plusDays(1);
        arraycheck[5] = fecha1 + "Coruña";
        fecha1 = fecha1.plusDays(1);
        arraycheck[6] = fecha1 + "Coruña";

        grupos.inscribirequipo("A");
        grupos.inscribirequipo("B");
        grupos.inscribirequipo("C");
        grupos.inscribirequipo("D");
        grupos.inscribirequipo("E");
        grupos.inscribirequipo("F");
        grupos.inscribirequipo("G");
        grupos.inscribirequipo("H");

        fecha2 = LocalDate.now();

        result = grupos.generarPartido("Coruña", fecha2, 'c');

        copyresult[0] = result.get(0).getParticipanteLocal() + result.get(0).getParticipanteVisitante();
        copyresult[1] = result.get(1).getParticipanteLocal() + result.get(1).getParticipanteVisitante();
        copyresult[2] = result.get(2).getParticipanteLocal() + result.get(2).getParticipanteVisitante();
        copyresult[3] = result.get(3).getParticipanteLocal() + result.get(3).getParticipanteVisitante();
        copyresult[4] = result.get(4).getFechapartido() + result.get(4).getUbicacion();
        copyresult[5] = result.get(5).getFechapartido() + result.get(5).getUbicacion();
        copyresult[6] = result.get(6).getFechapartido() + result.get(6).getUbicacion();

        assertArrayEquals(arraycheck, copyresult);

        antecesorescheck[0] = "E" + "F";
        antecesorescheck[1] = "G" + "H";

        antecesores = grupos.seguimientoantecesor(grupos.seguimientosucesor(result.get(1)));
        copyantecesores[0] = antecesores.get(0).getParticipanteLocal() + antecesores.get(0).getParticipanteVisitante();
        copyantecesores[1] = antecesores.get(1).getParticipanteLocal() + antecesores.get(1).getParticipanteVisitante();
        assertArrayEquals(antecesorescheck, copyantecesores);
    }
}
