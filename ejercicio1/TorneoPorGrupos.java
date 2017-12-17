package ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class TorneoPorGrupos extends Torneo {

    ArrayList<Partido> encuentros = new ArrayList<>(); //Array de encuentros
    ArrayList<String> participantesjugando = new ArrayList<>(); //Cada vez que un participante se inserta en un partido se borra del array de inscritos y se inserta en este
    //ArrayList<Integer> encuentrossuc = new ArrayList<>(); //Array que contiene la posicion que le corresponde a sus partidos sucesores
    //ArrayList<Integer> encuentrosant = new ArrayList<>();//Array que contien la posicion que le corresponde a sus partidos antecesores
    LocalDate fechaact;
    BinaryTree arbol = new BinaryTree();

    //Metodo para inscribir equipos
    @Override
    public void inscribirequipo(String equipo) throws IllegalArgumentException {
        int i = 0;
        boolean encontrado = false;

        while ((!encontrado) && (i < participantesinscritos.size())) {
            if (participantesinscritos.get(i).equals(equipo)) {
                encontrado = true;
            }
            i++;
        }
        if (encontrado) {
            throw new IllegalArgumentException();
        } else {
            participantesinscritos.add(equipo);
        }
    }

    //Metodo que inicializa el array de antecesores
//    private void initantecesores() {
//        int i = 0, decrecimiento = 2;
//        while (i < encuentros.size() - 1) {
//            encuentrosant.add(((encuentros.size() - 1) - decrecimiento));
//            decrecimiento += 2;
//            i += 2;
//        }
//    }
//
//    //Metodo que inicializa el array de sucesores
//    private void initsucesores() {
//        int i = 0, cont = 1, aumento = 0;
//
//        while (i < encuentros.size()) {
//            if (cont == 2) {
//                cont = 0;
//                aumento += 1;
//            }
//            encuentrossuc.add((encuentros.size() / 2 + aumento));
//            cont += 1;
//            i++;
//        }
//    }
    //Metodo auxiliar para compromar si la cantidad de participantes es potencia de dos
    private boolean potenciaDeDos(int numero) {
        int cuadrado = 1;

        while (numero >= cuadrado) {
            if (numero == cuadrado) {
                return true;
            }
            cuadrado = cuadrado * 2;
        }
        return false;
    }

    // Metodo que genera la lista de torneo con emparejamientos aleatorios
    private BinaryTree generarPartidoAleatorio(String ubicacion, LocalDate fecha) throws IllegalArgumentException {
        int i = 0, size;
        //ArrayList<Partido> encuentrosAleatorios = new ArrayList<>();

        //Genero los emparejamientos sin equipos, solo ubicacion, fecha e identificador
        fechaact = fecha;
        size = participantesinscritos.size();
        fechaact = fechaact.plusDays((size / 2) - 1);
        while (i < size / 2) {
            Partido porGrupos = new Partido(ubicacion, fechaact, size - 1);
            arbol.insert(porGrupos);
            //encuentrosAleatorios.add(porGrupos);
            fechaact = fechaact.minusDays(1);
            i++;
            size--;
        }
        //Genero los emparejamientos de la primera ronda del torneo
        i = 0;
        fechaact = fecha;
        if (potenciaDeDos(participantesinscritos.size())) {
            while ((i + 2) <= participantesinscritos.size()) {
                Partido porGrupos = new Partido(ubicacion, fechaact, size - 1);
                Random aleatorio = new Random();
                int intAleatorio = aleatorio.nextInt(participantesinscritos.size());
                porGrupos.equipoLocal(participantesinscritos.get(intAleatorio));
                participantesjugando.add(participantesinscritos.get(intAleatorio));
                participantesinscritos.remove(intAleatorio);

                Random aleatorio2 = new Random();
                int intAleatorio2 = aleatorio2.nextInt(participantesinscritos.size());
                porGrupos.equipoVisitante(participantesinscritos.get(intAleatorio2));
                participantesjugando.add(participantesinscritos.get(intAleatorio2));
                participantesinscritos.remove(intAleatorio2);

                arbol.insert(porGrupos);
                //encuentrosAleatorios.add(porGrupos);
                fechaact = fechaact.plusDays(1);
                size--;
            }
        } else {
            throw new IllegalArgumentException();
        }
        //initsucesores();
        //initantecesores();
        return arbol;
    }

    //Metodo que genera el torneo donde las cabezas de serie no pueden ser emparejadas
    private BinaryTree generarPartidoCabezas(String ubicacion, LocalDate fecha) throws IllegalArgumentException {
        int i = 0, j = 1, size;

        //Genero los emparejamientos sin equipos, solo ubicacion, fecha e identificador
        fechaact = fecha;
        size = participantesinscritos.size();
        fechaact = fechaact.plusDays((size / 2) - 1);
        while (i < size / 2) {
            Partido porGrupos = new Partido(ubicacion, fechaact, size - 1);
            //encuentros.add(porGrupos);
            arbol.insert(porGrupos);
            fechaact = fechaact.minusDays(1);
            i++;
        }

        //Genero los emparejamientos de la primera ronda del torneo
        i = 0;
        fechaact = fecha;
        if (potenciaDeDos(participantesinscritos.size())) {
            while ((i + 2) <= participantesinscritos.size()) {
                Partido porGrupos = new Partido(ubicacion, fechaact, size - 1);
                porGrupos.equipoLocal(participantesinscritos.get(i));
                if (participantesinscritos.get(i).contains("cabeza de serie")) {
                    while (participantesinscritos.get(i + j).contains("cabeza de serie")) {
                        j += 1;
                    }
                }
                porGrupos.equipoVisitante(participantesinscritos.get(i + j));
                participantesinscritos.remove(i);
                participantesinscritos.remove(i + (j - 1));
                //encuentros.add(porGrupos);
                arbol.insert(porGrupos);
                fechaact = fechaact.plusDays(1);
                j = 1;
            }
        } else {
            throw new IllegalArgumentException();
        }
//        initsucesores();
//        initantecesores();
        return arbol;
    }

    //Metodo que da a elegir que tipo de creacion de torneo se quiere aleatorio o por cabezas de serie
    @Override
    public BinaryTree generarPartido(String ubicacion, LocalDate fecha, char op) throws IllegalArgumentException {
        if ((op == 'A') || (op == 'a')) {
            arbol = generarPartidoAleatorio(ubicacion, fecha);
        } else {
            if ((op == 'C') || (op == 'c')) {
                arbol = generarPartidoCabezas(ubicacion, fecha);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return arbol;
    }

    //Metodo que devuelve el partido sucesor de un partido pasado por parametro
    public Partido seguimientosucesor(Partido partido) throws IllegalArgumentException {
        return arbol.buscarSucesor(partido);
    }

    //Metodo que devuelve el partido antecesor de un partido pasado por parametro
    public ArrayList<Partido> seguimientoantecesor(Partido partido) throws IllegalArgumentException {
        ArrayList<Partido> partidosant = new ArrayList<>();
        partidosant = (arbol.buscarAntecesores(partido));
        return partidosant;
    }

    //Metodo que se encarga de pasar de ronda el ganador de cada encuentro (NO PROBADO)
    public ArrayList<Partido> pasarRonda() {
        int i = 0;
        String ganador;

        while (i < encuentros.size()) {
            if (seguimientosucesor(encuentros.get(i)) == null) {
                break;
            } else {
                if (encuentros.get(i).estado == EstadosPartido.FINALIZADO) {
                    ganador = encuentros.get(i).resultado();
                    if (seguimientosucesor(encuentros.get(i)).participanteLocal == null) {
                        seguimientosucesor(encuentros.get(i)).equipoLocal(ganador);
                    } else {
                        seguimientosucesor(encuentros.get(i)).equipoVisitante(ganador);
                    }
                }
                i++;
            }
        }
        return encuentros;
    }
}
