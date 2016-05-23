package macochave.com.decidir;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        spec3.setContent(R.id.tab4);
        spec3.setIndicator("Agregar categoría", resources.getDrawable(android.R.drawable.btn_dropdown));
        tabHost.addTab(spec3);

        tabHost.setCurrentTab(0);
    }
}
