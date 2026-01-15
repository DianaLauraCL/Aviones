package mx.fca.aviones;

import android.util.Log;
import java.util.ArrayList;

public class Plano {

    public ArrayList<Avion> aviones;
    public ArrayList<Colision> colisiones;
    public int col;
    public int row;
    public int noPaso;

    Plano(int noPaso, ArrayList<Avion> aviones, ArrayList<Colision> colisiones) {
        this.noPaso = noPaso;
        this.aviones = aviones;
        this.colisiones = colisiones;

        int tmpX = 0;
        int tmpY = 0;
        if (aviones != null) {
            for (Avion avion: aviones) {
                if (avion.x > tmpX) {
                    tmpX = avion.x;
                }
                if (avion.y > tmpY) {
                    tmpY = avion.y;
                }
            }
        }
        col = tmpX;
        row = tmpY;
    }

    public Plano(Plano otroPlano) {
        this.noPaso = otroPlano.noPaso;
        this.col = otroPlano.col;
        this.row = otroPlano.row;

        this.aviones = new ArrayList<>();
        if (otroPlano.aviones != null) {
            for (Avion avion : otroPlano.aviones) {
                this.aviones.add(new Avion(avion));
            }
        }

        this.colisiones = new ArrayList<>();
        if (otroPlano.colisiones != null) {
            for (Colision colision : otroPlano.colisiones) {
                this.colisiones.add(new Colision(colision));
            }
        }
    }

    public Plano next() {
        Log.i("Plano.next()", "Calculando el siguiente paso desde el paso: " + this.noPaso);
        return Analizador.next(this.noPaso + 1, new Plano(this));
    }

    public int getNumeroPaso() {
        return this.noPaso;
    }

    public int getNumeroColisiones() {
        if (colisiones == null) {
            return 0;
        }
        return colisiones.size();
    }

    public int getNumeroAviones() {
        if (aviones == null) {
            return 0;
        }
        return aviones.size();
    }
}

