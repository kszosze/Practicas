package ejercicio1;

import java.util.ArrayList;

public class BinaryTree {

    class Node {

        Partido partido;
        Node left;
        Node right;

        public Node(Partido partido) {
            this.partido = partido;
            left = null;
            right = null;
        }
    }

    public static Node raiz;

    public BinaryTree() {
        this.raiz = null;
    }

    public Partido buscarSucesor(Partido partido) {
        Node raizright = raiz;
        
        if (raiz.partido.equals(partido)) {
            return null;
        }

        if (raiz.left != null && (raiz.left.partido.participanteLocal.equals(partido.participanteLocal)
                || raiz.left.partido.participanteVisitante.equals(partido.participanteVisitante)
                || raiz.left.partido.participanteLocal.equals(partido.participanteVisitante)
                || raiz.left.partido.participanteVisitante.equals(partido.participanteLocal))) {
            return raiz.partido;
        } else {
            if (raiz.right != null && (raiz.right.partido.participanteLocal.equals(partido.participanteLocal)
                    || raiz.right.partido.participanteVisitante.equals(partido.participanteVisitante)
                    || raiz.right.partido.participanteLocal.equals(partido.participanteVisitante)
                    || raiz.right.partido.participanteVisitante.equals(partido.participanteLocal))) {
                return raiz.partido;
            } else {
                raiz = raiz.left;
                Partido left = buscarSucesor(partido);
                raiz = raizright;
                Partido right = buscarSucesor(partido);
                raiz = raizright;
                if (left != null) {
                    return left;
                } else {
                    return right;
                }
            }
        }
    }

    public ArrayList<Partido> buscarAntecesores(Partido partido) {
        Node raizright = raiz;
        
        ArrayList<Partido> partidosant = new ArrayList<>();

        if (raiz.left == null && raiz.right == null) {
            return null;
        }
        if (raiz.left != null && (raiz.left.partido.participanteLocal.equals(partido.participanteLocal)
                || raiz.left.partido.participanteVisitante.equals(partido.participanteVisitante)
                || raiz.left.partido.participanteLocal.equals(partido.participanteVisitante)
                || raiz.left.partido.participanteVisitante.equals(partido.participanteLocal))) {
            partidosant.add(raiz.left.partido);
        }

        if (raiz.right != null && (raiz.right.partido.participanteLocal.equals(partido.participanteLocal)
                || raiz.right.partido.participanteVisitante.equals(partido.participanteVisitante)
                || raiz.right.partido.participanteLocal.equals(partido.participanteVisitante)
                || raiz.right.partido.participanteVisitante.equals(partido.participanteLocal))) {
            partidosant.add(raiz.right.partido);
        }

        if (partidosant.isEmpty()) {
            raiz = raiz.left;
            if ((partidosant = buscarAntecesores(partido)).isEmpty()) {
            } else {
                raiz = raizright;
                if ((partidosant = buscarAntecesores(partido)).isEmpty()) {
                }
            }
        }
        raiz = raizright;
        return partidosant;
    }

    public void insert(Partido partido) {
        Node nuevoNode = new Node(partido);

        if (raiz == null) {
            raiz = nuevoNode;
            return;
        }
        Node actual = raiz;
        Node padre = null;

        while (true) {
            padre = actual;
            if (actual.left == null) {
                padre.left = nuevoNode;
                return;
            } else {
                if (actual.right == null) {
                    padre.right = nuevoNode;
                    return;
                }
            }
            actual = actual.left;
        }
    }

    public void imprimir(Node root, int tab) {
        String tabulacion = "";

        for (int i = 0; i < tab; i++) {
            tabulacion += "\t";
        }

        if (root != null && root.left == null && root.right == null) {
            System.out.print(tabulacion + root.partido.getParticipanteLocal());
            System.out.print(tabulacion + root.partido.getParticipanteVisitante());
        } else {
            if (root.partido.getEstado() == EstadosPartido.EN_ESPERA) {
                System.out.print(tabulacion + root.partido.getIdentificador() + ": " + root.partido.getEstado() + ". Antecesores: {");
            } else {
                if (root.partido.getEstado() == EstadosPartido.FINALIZADO) {
                    System.out.print(tabulacion + root.partido.getIdentificador() + ": " + root.partido.getEstado() + ", jugado entre " + root.partido.getParticipanteLocal()
                            + " y " + root.partido.getParticipanteVisitante() + ". Antecesores: {");
                } else {
                    if (root.partido.getEstado() == EstadosPartido.EN_JUEGO) {
                        System.out.print(tabulacion + root.partido.getIdentificador() + ":" + root.partido.getEstado() + " entre " + root.partido.getParticipanteLocal()
                                + " y " + root.partido.getParticipanteVisitante() + ". Antecesores: {");
                    }
                }
            }
            imprimir(root.left, tab + 1);
            imprimir(root.right, tab + 1);
            System.out.print(tabulacion + "} (fin resumen partido " + root.partido.getIdentificador() + " )");
        }
    }
}
