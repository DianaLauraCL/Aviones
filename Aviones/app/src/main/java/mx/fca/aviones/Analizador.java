package mx.fca.aviones;

import java.util.ArrayList;
import java.util.HashMap;

public class Analizador {

    static HashMap<Integer, Plano> memoria = new HashMap<>();

    public static Plano inicializa(Plano plano) {
        memoria.put(0, plano);
        return plano;
    }

    public static Plano next(int noPaso, Plano plano){
        if (memoria.containsKey(noPaso)){
            return memoria.get(noPaso);
        } else {
            Plano planoAnterior = memoria.get(noPaso - 1);
            if (planoAnterior == null) {
                return null;
            }
            ArrayList<Avion> nuevosAviones = new ArrayList<>();

            for (Avion avion : planoAnterior.aviones) {
                Avion nuevoAvion = new Avion(avion);

                switch (nuevoAvion.direccion){
                    case NORTE:
                        nuevoAvion.y = nuevoAvion.y - 1;
                        break;
                    case SUR:
                        nuevoAvion.y = nuevoAvion.y + 1;
                        break;
                    case ESTE:
                        nuevoAvion.x = nuevoAvion.x + 1;
                        break;
                    case OESTE:
                        nuevoAvion.x = nuevoAvion.x - 1;
                        break;
                }
                nuevosAviones.add(nuevoAvion);
            }

            ArrayList<Colision> colisiones = new ArrayList<>();
            HashMap<String, ArrayList<Avion>> posiciones = new HashMap<>();

            for (Avion avion : nuevosAviones) {
                String key = avion.x + "," + avion.y;
                if (!posiciones.containsKey(key)) {
                    posiciones.put(key, new ArrayList<>());
                }
                posiciones.get(key).add(avion);
            }

            for (ArrayList<Avion> avionesEnMismaPosicion : posiciones.values()) {
                if (avionesEnMismaPosicion.size() > 1) {
                    colisiones.add(new Colision(avionesEnMismaPosicion));
                }
            }

            Plano planoNuevo = new Plano(noPaso, nuevosAviones, colisiones);
            memoria.put(noPaso, planoNuevo);
            return planoNuevo;
        }
    }

    public static Plano prev(int noPaso) {
        return memoria.get(noPaso);
    }
}
