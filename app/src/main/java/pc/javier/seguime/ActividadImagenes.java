package pc.javier.seguime;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.control.ControlPantallaImagenes;
import pc.javier.seguime.vista.PantallaRegistros;

/**
 * Created by Javier on 16/07/2019.
 */

public class ActividadImagenes extends AppCompatActivity {

    private ControlPantallaImagenes controlPantallaImagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registros_imagenes);

        Preferencias preferencias = new Preferencias(this);
        PantallaRegistros pantallaRegistros = new PantallaRegistros(this);


        controlPantallaImagenes = new ControlPantallaImagenes ( pantallaRegistros, preferencias);

    }






    // Menú superior ---------------
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        controlPantallaImagenes.menu (item);
        return true;
    }









}
