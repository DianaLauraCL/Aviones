package mx.fca.aviones;

import java.util.ArrayList;

public class Colision {
    public ArrayList<Avion> avionesInvolucrados;
    public Colision(ArrayList<Avion> avionesInvolucrados) {
        this.avionesInvolucrados = avionesInvolucrados;
    }
    public Colision(Colision otraColision) {
        this.avionesInvolucrados = new ArrayList<>();
        for (Avion avion : otraColision.avionesInvolucrados) {
            this.avionesInvolucrados.add(new Avion(avion));
        }
    }
}


