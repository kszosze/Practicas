package ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class TorneoPorGrupos extends Torneo {

    ArrayList<Partido> encuentros = new ArrayList<>();
    ArrayList<String> participantesjugando = new ArrayList<>();
    ArrayList<Integer> encuentrossuc = new ArrayList<>();
    ArrayList<Integer> encuentrosant = new ArrayList<>();
    LocalDate fechaact;

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

    private void initantecesores() {
        int i = 0, decrecimiento = 2;
        while (i < encuentros.size() - 1) {
            encuentrosant.add(((encuentros.size() - 1) - decrecimiento));
            decrecimiento += 2;
            i += 2;
        }
    }

    private void initsucesores() {
        int i = 0, cont = 1, aumento = 0;

        while (i < encuentros.size()) {
            if (cont == 2) {
                cont = 0;
                aumento += 1;
            }
            encuentrossuc.add((encuentros.size() / 2 + aumento));
            cont += 1;
            i++;
        }
    }

    public boolean comprobarComienzoTorneo() {
        boolean comienzo = true;

        if (encuentros.isEmpty()) {
            comienzo = false;
        }

        return comienzo;
    }
    
    

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

    private ArrayList<Partido> generarPartidoAleatorio(String ubicacion, LocalDate fecha) throws IllegalArgumentException {
        int i = 0, tam = 0;
        ArrayList<Partido> encuentrosAleatorios = new ArrayList<>();

        fechaact = fecha;
        if (potenciaDeDos(participantesinscritos.size())) {
            while ((i + 2) <= participantesinscritos.size()) {
                Partido porGrupos = new Partido(ubicacion, fechaact);
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

                encuentrosAleatorios.add(porGrupos);
                fechaact = fechaact.plusDays(1);
            }
            i = 0;
            tam = encuentrosAleatorios.size() - 1;
            while (i < tam) {
                Partido porGrupos = new Partido(ubicacion, fechaact);
                encuentrosAleatorios.add(porGrupos);
                fechaact = fechaact.plusDays(1);
                i++;;
            }
        } else {
            throw new IllegalArgumentException();
        }
        initsucesores();
        initantecesores();
        return encuentrosAleatorios;
    }

    private ArrayList<Partido> generarPartidoCabezas(String ubicacion, LocalDate fecha) throws IllegalArgumentException {
        int i = 0, j = 1, tam = 0;

        fechaact = fecha;
        if (potenciaDeDos(participantesinscritos.size())) {
            while ((i + 2) <= participantesinscritos.size()) {
                Partido porGrupos = new Partido(ubicacion, fechaact);
                porGrupos.equipoLocal(participantesinscritos.get(i));
                if (participantesinscritos.get(i).contains("cabeza de serie")) {
                    while (participantesinscritos.get(i + j).contains("cabeza de serie")) {
                        j += 1;
                    }
                }
                porGrupos.equipoVisitante(participantesinscritos.get(i + j));
                participantesinscritos.remove(i);
                participantesinscritos.remove(i + (j - 1));
                encuentros.add(porGrupos);
                fechaact = fechaact.plusDays(1);
                j = 1;
            }
            i = 0;
            tam = encuentros.size() - 1;
            while (i < tam) {
                Partido porGrupos = new Partido(ubicacion, fechaact);
                encuentros.add(porGrupos);
                fechaact = fechaact.plusDays(1);
                i++;;
            }
        } else {
            throw new IllegalArgumentException();
        }
        initsucesores();
        initantecesores();
        return encuentros;
    }

    @Override
    public ArrayList<Partido> generarPartido(String ubicacion, LocalDate fecha, char op) throws IllegalArgumentException {
        if ((op == 'A') || (op == 'a')) {
            encuentros = generarPartidoAleatorio(ubicacion, fecha);
        } else {
            if ((op == 'C') || (op == 'c')) {
                encuentros = generarPartidoCabezas(ubicacion, fecha);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return encuentros;
    }

    public Partido seguimientosucesor(Partido partido) throws IllegalArgumentException {
        int i = 0, encontrado = 0;

        while ((i <= encuentros.size()) && (encontrado == 0)) {
            if ((encuentros.get(i).getParticipanteVisitante().equals(partido.getParticipanteVisitante()))
                    && (encuentros.get(i).getParticipanteLocal().equals(partido.getParticipanteLocal()))) {
                encontrado = 1;
            } else {
                i++;
            }
        }
        if (i > encuentros.size()) {
            return null;
        }

        if (encontrado == 0) {
            throw new IllegalArgumentException();
        } else {
            if (i == (encuentros.size() - 1)) {
                throw new IllegalArgumentException();
            }
        }
        return encuentros.get(encuentrossuc.get(i));
    }

    public ArrayList<Partido> seguimientoantecesor(Partido partido) throws IllegalArgumentException {
        int i = 0, encontrado = 0, indice = 0;
        ArrayList<Partido> partidosant = new ArrayList<>();

        while ((i <= encuentros.size()) && (encontrado == 0)) {
            if ((partido.getParticipanteVisitante() == null) || (partido.getParticipanteLocal() == null)) {
                if (encuentros.get(i).getFechapartido().equals(partido.getFechapartido())) {
                    encontrado = 1;
                } else {
                    i++;
                }
            } else {
                if ((encuentros.get(i).getParticipanteVisitante().equals(partido.getParticipanteVisitante()))
                        && (encuentros.get(i).getParticipanteLocal().equals(partido.getParticipanteLocal()))) {
                    encontrado = 1;
                } else {
                    i++;
                }
            }
        }
        if (encontrado == 0) {
            throw new IllegalArgumentException();
        } else {
            if (i < encuentros.size() / 2) {
                throw new IllegalArgumentException();
            }
        }

        indice = (encuentros.size() - i) - 2;
        partidosant.add(encuentros.get(encuentrosant.get(indice)));
        partidosant.add(encuentros.get(encuentrosant.get(indice) + 1));

        return partidosant;
    }

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
