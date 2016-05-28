package macochave.com.decidir;

/**
 * Created by MacoChave on 27/05/2016.
 */
public class Categoria {
    private int id;
    private String nombre;

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
}
