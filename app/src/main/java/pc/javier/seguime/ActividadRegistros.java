package pc.javier.seguime;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.control.ControlPantallaRegistros;
import pc.javier.seguime.vista.PantallaRegistros;

public class ActividadRegistros extends AppCompatActivity {



    private ControlPantallaRegistros controlPantallaRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registros);


        Preferencias preferencias = new Preferencias(this);
        PantallaRegistros pantallaRegistros = new PantallaRegistros(this);
        controlPantallaRegistros = new ControlPantallaRegistros(pantallaRegistros, preferencias);

    }





    // Menú superior ---------------
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        controlPantallaRegistros.menu (item);
        return true;
    }



    private void mensajeLog (String texto) {
        Log.d("Actividad Registro", texto);
    }


}
