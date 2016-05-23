package macochave.com.decidir;

/**
 * Created by u on 22/05/2016.
 */
public class Opcion {

    private String opcion;
    private String urlImagen;
    private int idCategoria;

    public Opcion() {
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
