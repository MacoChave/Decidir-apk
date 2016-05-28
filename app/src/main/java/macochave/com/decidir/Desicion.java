package macochave.com.decidir;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by u on 22/05/2016.
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

    public String desidirRapido(Context context) {
        int indice;
        int tope;
        String resultado;

        tope = listaOpcion.size();
        if (tope > 0) {
            Log.i("Lista de opciones", "Hay " + tope + " en la lista");
            Toast.makeText(context, "Encontré " + (tope) + " elementos :D", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Pero si no ingresaste nada :|", Toast.LENGTH_SHORT).show();
        }

        Random random = new Random();
        indice = random.nextInt(tope);
        Log.i("Lista de opciones", "Elemento No. " + indice);

        resultado = listaOpcion.get(indice);
        Log.i("Lista de opciones", "Elemento " + resultado);
        Log.i("Lista de opciones", "---------------");

        return resultado;
    }
}
