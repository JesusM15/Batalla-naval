public class Jugador {
    private String nombre;
    private String id;
    // private Oceano oceano;
    public Jugador(String nombre, String id) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    /*
    public Oceano getOceano(){
        return oceano;
    }
    */
}
