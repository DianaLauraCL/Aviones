package mx.fca.aviones;

public class Avion {

    public int id;    public int x;
    public int y;
    public Direccion direccion;
    public boolean esVisible;
    public enum Direccion {
        NORTE,
        SUR,
        ESTE,
        OESTE
    }

    public Avion(int id, int x, int y, Direccion direccion) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.esVisible = true;
    }

    public Avion(Avion otroAvion) {
        this.id = otroAvion.id;
        this.x = otroAvion.x;
        this.y = otroAvion.y;
        this.direccion = otroAvion.direccion;
        this.esVisible = otroAvion.esVisible;
    }
}
