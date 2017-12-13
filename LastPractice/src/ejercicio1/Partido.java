package ejercicio1;

import java.time.LocalDate;

public class Partido {

    private final String ubicacion;
    protected String participanteLocal;
    protected String participanteVisitante;
    private int puntoLocal = 0;
    private int puntoVisitante = 0;

    protected EstadosPartido estado;
    private final LocalDate fechapartido;

    public Partido(String ubicacion, LocalDate fecha) {
        this.ubicacion = ubicacion;
        this.fechapartido = fecha;
        this.estado = EstadosPartido.EN_ESPERA;
    }

    public void equipoLocal(String participanteLocal) throws OperacionNoValidaException {
        if (estado != EstadosPartido.EN_ESPERA) {
            throw new OperacionNoValidaException();
        } else {
            if (this.participanteLocal != null) {
                throw new OperacionNoValidaException();
            } else {
                this.participanteLocal = participanteLocal;
                if (this.participanteVisitante != null) {
                    this.estado = EstadosPartido.NO_JUGADO;
                }
            }
        }

    }

    public void equipoVisitante(String participanteVisitante) throws OperacionNoValidaException {
        if (estado != EstadosPartido.EN_ESPERA) {
            throw new OperacionNoValidaException();
        } else {
            if (this.participanteVisitante != null) {
                throw new OperacionNoValidaException();
            } else {
                this.participanteVisitante = participanteVisitante;
                if (this.participanteLocal != null) {
                    this.estado = EstadosPartido.NO_JUGADO;
                }
            }
        }
    }

    public EstadosPartido getEstado() {
        return estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public LocalDate getFechapartido() {
        return fechapartido;
    }

    public String getParticipanteLocal() {
        return participanteLocal;
    }

    public String getParticipanteVisitante() {
        return participanteVisitante;
    }

    public void finalizarPartido() throws OperacionNoValidaException {
        if (estado == EstadosPartido.NO_JUGADO || estado == EstadosPartido.FINALIZADO) {
            throw new OperacionNoValidaException();
        } else {
            this.estado = EstadosPartido.FINALIZADO;
        }
    }

    protected void puntosLocal(int puntoLocal) throws OperacionNoValidaException {
        if (estado == EstadosPartido.FINALIZADO || estado == EstadosPartido.EN_ESPERA) {
            throw new OperacionNoValidaException();
        } else {
            estado = EstadosPartido.EN_JUEGO;
            this.puntoLocal += puntoLocal;
        }
    }

    protected void puntosVisitante(int puntoVisitante) throws OperacionNoValidaException {
        if (estado == EstadosPartido.FINALIZADO || estado == EstadosPartido.EN_ESPERA) {
            throw new OperacionNoValidaException();
        } else {
            estado = EstadosPartido.EN_JUEGO;
            this.puntoVisitante += puntoVisitante;
        }
    }

    public String resultado() throws OperacionNoValidaException {

        if (estado != EstadosPartido.FINALIZADO) {
            throw new OperacionNoValidaException();
        } else {
            if (puntoLocal > puntoVisitante) {
                return participanteLocal;
            } else {
                if (puntoVisitante > puntoLocal) {
                    return participanteVisitante;
                } else {
                    return "Empate";
                }
            }
        }
    }

    public String datos() {
        return "Partido{ubicacion=" + ubicacion + ", participanteLocal=" + participanteLocal
                + ", participanteVisitante=" + participanteVisitante + ", fecha=" + fechapartido
                + ", puntoLocal=" + puntoLocal + ", puntoVisitante=" + puntoVisitante
                + ", estado=" + estado + '}';
    }

}
