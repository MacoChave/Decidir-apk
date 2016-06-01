package macochave.com.decidir;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    private static boolean mostrarToast;

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

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_informacion:
                Toast.makeText(getApplicationContext(), "APP Decidir", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_ayuda:
                Toast.makeText(getApplication(), "Esto debería tener una ayuda", Toast.LENGTH_SHORT).show();
            case R.id.action_salir:
                Toast.makeText(getApplication(), "Debería de salir", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarSpinner(Spinner spinner) {
        try {
            DBManager manager = new DBManager(getApplicationContext());

            ArrayList<Categoria> list = manager.selectAllCategoria();
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(getApplication(), R.layout.item_spinner_totto, list);

            adapter.setDropDownViewResource(R.layout.dropdown_spinner_totto);

            categoriaT1.setAdapter(adapter);
            categoriaT2.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("Cargar spinner", e.getMessage().toString());
        }
    }

    private void configTab() {
        Resources resources = getResources();
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("mitab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("", resources.getDrawable(R.mipmap.ic_decision_rapida));
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("mitab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("", resources.getDrawable(R.mipmap.ic_decision));
        tabHost.addTab(spec2);

        TabHost.TabSpec spec3 = tabHost.newTabSpec("mitab3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("", resources.getDrawable(R.mipmap.ic_agregar_opcion));
        tabHost.addTab(spec3);

        TabHost.TabSpec spec4 = tabHost.newTabSpec("mitab4");
        spec4.setContent(R.id.tab4);
        spec4.setIndicator("", resources.getDrawable(R.mipmap.ic_agregar_categoria));
        tabHost.addTab(spec4);

        tabHost.setCurrentTab(0);
    }

    @Override
    public void onClick(View v) {
        Desicion d = new Desicion();
        switch (v.getId()){
            case R.id.btnAddOpcionF:
                String opcion = addOpcion.getText().toString();
                try {
                    if (opcion.isEmpty() || opcion.equals(" ")){
                        Toast.makeText(getApplicationContext(), "No ingresaste una opcion :(", Toast.LENGTH_SHORT).show();
                    } else {
                        d.addOpcionRapida(opcion);
                        addOpcion.setText("");
                        Toast.makeText(getApplicationContext(), "Ya agregué tu opción :D", Toast.LENGTH_SHORT).show();
                        mostrarToast = true;
                    }
                } catch (Exception e) {
                    Log.e("Agregar opcion", e.getMessage().toString());
                }
                break;
            case R.id.btnDecidirF:
                try {
                    String resultado;
                    if (mostrarToast) {
                        resultado = d.decidirRapido(getApplication(), mostrarToast);
                        Resultado1.setText(resultado);
                        mostrarToast = false;
                    } else {
                        resultado = d.decidirRapido(getApplication(), mostrarToast);
                        Resultado1.setText(resultado);
                    }
                    //Log.i("Boton", "Boton decidir rápido presionado");
                } catch (Exception e) {
                    Log.e("Decidir rapido", e.getMessage().toString());
                    Toast.makeText(getApplicationContext(), "No pude decidir :'(, lo siento!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDecidirC:
                try{
                    String resultado;
                    if (mostrarToast) {
                        resultado = d.decidir(getApplicationContext(), selectItem, mostrarToast);
                        Resultado2.setText(resultado.toString());
                        mostrarToast = false;
                    } else {
                        resultado = d.decidir(getApplicationContext(), selectItem, mostrarToast);
                        Resultado2.setText(resultado.toString());
                    }
                } catch (Exception e) {
                    Log.e("Decidir por categoria", e.getMessage().toString());
                }
                break;
            case R.id.btnAddNewOpcion:
                try {
                    String addOpcion = addNewOpcion.getText().toString();
                    d.addNewOpcion(getApplicationContext(), addOpcion, selectItem);
                    addNewOpcion.setText("");
                } catch (Exception e) {
                    Log.e("Nueva opcion", e.getMessage().toString());
                }

                break;
            case R.id.btnAddCategoria:
                try {
                    String addNewCategoria = addCategoria.getText().toString();
                    d.addNewCategoria(getApplicationContext(), addNewCategoria);
                    addCategoria.setText("");
                } catch (Exception e) {
                    Log.e("Nueva categoria", e.getMessage().toString());
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.posicion = position;

        switch (parent.getId()) {
            case R.id.spinnerDecidir:
                selectItem = parent.getItemAtPosition(position).toString();
                mostrarToast = true;
                Log.i("Spinner seleccionado", "Seleccion actual: " + selectItem);
                break;
            case R.id.spinnerAgregar:
                selectItem = parent.getItemAtPosition(position).toString();
                mostrarToast = true;
                Log.i("Spinner seleccionado", "Seleccion actual: " + selectItem);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}