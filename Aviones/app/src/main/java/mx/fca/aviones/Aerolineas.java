package mx.fca.aviones;

import java.util.ArrayList;
public class Aerolineas {

    public static ArrayList<Avion> AEROMEXICO(){
        ArrayList<Avion> aviones = new ArrayList<>();

        aviones.add(new Avion(0, 0, 0, Avion.Direccion.ESTE));
        aviones.add(new Avion(1, 2, 0, Avion.Direccion.OESTE));

        return aviones;
    }
}


