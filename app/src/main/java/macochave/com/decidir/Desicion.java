package macochave.com.decidir;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import macochave.com.decidir.DataBase.DBManager;

/**
 * Created by MacoChave on 22/05/2016.
 */
public class Desicion {
    private static ArrayList<String> listaOpcion = new ArrayList();

    public void addOpcionRapida(String opcion) {
        listaOpcion.add(opcion);
        for (int i = 0; i < listaOpcion.size(); i++) {
            Log.i("Lista de opciones", i + "-" + listaOpcion.get(i));
        }
        Log.i("Lista de opciones", "---------------");
    }

    public String decidirRapido(Context context, Boolean mostrarToast) {
        int indice;
        int tope;
        String resultado;

        tope = listaOpcion.size();
        if (tope > 0) {
            if (mostrarToast) {
                Log.i("Lista de opciones", "Hay " + tope + " en la lista");
                Toast.makeText(context, "Encontré " + (tope) + " elementos :D", Toast.LENGTH_SHORT).show();
            }

            Random random = new Random();
            indice = random.nextInt(tope);
            Log.i("Lista de opciones", "Elemento No. " + indice);

            resultado = listaOpcion.get(indice);
            Log.i("Lista de opciones", "Elemento " + resultado);
            Log.i("Lista de opciones", "---------------");

            return resultado;
        } else {
            Toast.makeText(context, "Pero si no ingresaste nada :|", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public String decidir(Context context, String selectItem, Boolean mostrarToast) {
        int idCategoria;
        int indice;
        int tope;
        String resultado;
        ArrayList<Opcion> list = new ArrayList<>();
        DBManager manager = new DBManager(context);

        idCategoria = manager.selectIdCategoria(selectItem);
        list = manager.selectOpcionCategoria(idCategoria);

        tope = list.size();
        if (tope > 0) {
            if (mostrarToast) {
                Log.i("Lista de opciones", "Hay " + tope + " en la lista");
                Toast.makeText(context, "Encontré " + (tope) + " elementos :D", Toast.LENGTH_SHORT).show();
            }

            Random random = new Random();
            indice = random.nextInt(tope);
            Log.i("Lista de opciones", "Elemento No. " + indice);

            resultado = list.get(indice).getOpcion();
            Log.i("Lista de opciones", "Elemento " + resultado);
            Log.i("Lista de opciones", "---------------");

            return resultado;
        } else {
            Toast.makeText(context, "No encontré elementos :|", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void addNewOpcion(Context context, String addOpcion, String selectItem) {
        DBManager manager = new DBManager(context);
        int opcion, categoria;
        categoria = manager.selectIdCategoria(selectItem);
        Log.i("Agregar opcion", "id categoria: " + categoria);
        opcion = manager.selectIdOpcion(addOpcion, categoria);

        if (opcion == 0) {
            Opcion opcion1 = new Opcion();
            opcion1.setOpcion(addOpcion);
            opcion1.setIdCategoria(categoria);
            manager.insertOpcion(opcion1);

            Toast.makeText(context, "Ya ingresé tu opción :D", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ya está ingresada la opción", Toast.LENGTH_SHORT).show();
        }
    }

    public void addNewCategoria(Context context, String addNewCategoria) {
        int categoria;
        DBManager manager = new DBManager(context);
        categoria = manager.selectIdCategoria(addNewCategoria);
        Log.i("Agregar categoria", "id categoria" + categoria);

        if (categoria == 0) {
            Categoria categoria1 = new Categoria();
            categoria1.setNombre(addNewCategoria);
            manager.insertCategoria(categoria1);

            Toast.makeText(context, "Ya ingresé tu categoría :D", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ya está ingresada la opción", Toast.LENGTH_SHORT).show();
        }
    }
}
