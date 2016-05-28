package macochave.com.decidir;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import macochave.com.decidir.DataBase.DBManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TabHost tabHost;
    private EditText addOpcion, addCategoria, addNewOpcion;
    private TextView Resultado1, Resultado2;
    private Button btnAddOpcion, btnDedicir1, btnDecidir2, btnAddNewOpcion, btnAddCategoria;
    private Spinner categoriaT1, categoriaT2;

    private int posicion;
    private static String selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addOpcion = (EditText) findViewById(R.id.edtAddOpcion);
        addCategoria = (EditText) findViewById(R.id.edtAddCategoria);
        addNewOpcion = (EditText) findViewById(R.id.edtAddNewOpcion);

        Resultado1 = (TextView) findViewById(R.id.txtResultadoF);
        Resultado2 = (TextView) findViewById(R.id.txtResultadoC);

        categoriaT1 = (Spinner) findViewById(R.id.spinnerDecidir);
        categoriaT2 = (Spinner) findViewById(R.id.spinnerAgregar);

        cargarSpinner(categoriaT1);
        cargarSpinner(categoriaT2);
        /*
        try {
            DBManager manager = new DBManager(getApplicationContext());

            ArrayList<Categoria> list = manager.selectAllCategoria();
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(getApplication(), android.R.layout.simple_spinner_item, list);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            categoriaT1.setAdapter(adapter);
            categoriaT2.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("Cargar spinner", "Error: " + e.getMessage());
        }*/

        categoriaT1.setOnItemSelectedListener(this);
        categoriaT2.setOnItemSelectedListener(this);

        btnAddOpcion = (Button) findViewById(R.id.btnAddOpcionF);
        btnDedicir1 = (Button) findViewById(R.id.btnDecidirF);
        btnDecidir2 = (Button) findViewById(R.id.btnDecidirC);
        btnAddNewOpcion = (Button) findViewById(R.id.btnAddNewOpcion);
        btnAddCategoria = (Button) findViewById(R.id.btnAddCategoria);

        btnAddOpcion.setOnClickListener(this);
        btnDedicir1.setOnClickListener(this);
        btnDecidir2.setOnClickListener(this);
        btnAddNewOpcion.setOnClickListener(this);
        btnAddCategoria.setOnClickListener(this);

        configTab();
    }

    private void cargarSpinner(Spinner spinner) {
        try {
            DBManager manager = new DBManager(getApplicationContext());

            ArrayList<Categoria> list = manager.selectAllCategoria();
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(getApplication(), android.R.layout.simple_spinner_item, list);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            categoriaT1.setAdapter(adapter);
            categoriaT2.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("Cargar spinner", "Error: " + e.getMessage());
        }
    }

    private void configTab() {
        Resources resources = getResources();
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("mitab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Desición rápida", resources.getDrawable(android.R.drawable.ic_dialog_info));
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("mitab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Desición por categoría", resources.getDrawable(android.R.drawable.ic_media_play));
        tabHost.addTab(spec2);

        TabHost.TabSpec spec3 = tabHost.newTabSpec("mitab3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Agregar opción", resources.getDrawable(android.R.drawable.ic_input_add));
        tabHost.addTab(spec3);

        TabHost.TabSpec spec4 = tabHost.newTabSpec("mitab4");
        spec4.setContent(R.id.tab4);
        spec4.setIndicator("Agregar categoria", resources.getDrawable(android.R.drawable.ic_input_add));
        tabHost.addTab(spec4);

        tabHost.setCurrentTab(0);
    }

    @Override
    public void onClick(View v) {
        Desicion d = new Desicion();
        switch (v.getId()){
            case R.id.btnAddOpcionF:
                String opcion = addOpcion.getText().toString();
                if (opcion.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No ingresaste una opcion :(", Toast.LENGTH_SHORT).show();
                } else {
                    d.addOpcionRapida(opcion);
                    addOpcion.setText("");
                    Toast.makeText(getApplicationContext(), "Ya agregué tu opción :D", Toast.LENGTH_SHORT).show();
                    //Log.i("Boton", "Boton agregar opcion rápido presionado");
                }
                break;
            case R.id.btnDecidirF:
                try {
                    String resultado;
                    resultado = d.decidirRapido(getApplication());
                    Resultado1.setText(resultado);
                    //Log.i("Boton", "Boton decidir rápido presionado");
                } catch (Exception e) {
                    Log.i("Boton decidir rápido", e.getMessage());
                    Toast.makeText(getApplicationContext(), "No pude decidir :'(, lo siento!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDecidirC:
                try{
                    String resultado;
                    resultado = d.decidir(getApplicationContext(), selectItem);
                    Resultado2.setText(resultado.toString());
                } catch (Exception e) {
                    //Log.i("Boton", "Boton decidir con categoria presionado");
                    //Toast.makeText(this, "Este botón no hace nada XD", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnAddNewOpcion:
                Log.i("Boton", "Boton agregar nueva opcion presionado");
                Toast.makeText(this, "Este botón no hace nada XD", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnAddCategoria:
                Log.i("Boton", "Boton agregar nueva categoria presionado");
                Toast.makeText(this, "Este botón no hace nada XD", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.posicion = position;
        selectItem = parent.getItemAtPosition(position).toString();
        Log.i("Spinner seleccionado", "Seleccion actual: " + selectItem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
