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

    public String decidirRapido(Context context) {
        int indice;
        int tope;
        String resultado;

        tope = listaOpcion.size();
        if (tope > 0) {
            Log.i("Lista de opciones", "Hay " + tope + " en la lista");
            Toast.makeText(context, "Encontré " + (tope) + " elementos :D", Toast.LENGTH_SHORT).show();

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

    public String decidir(Context context, String selectItem) {
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
            Log.i("Lista de opciones", "Hay " + tope + " en la lista");
            Toast.makeText(context, "Encontré " + (tope) + " elementos :D", Toast.LENGTH_SHORT).show();

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
}
