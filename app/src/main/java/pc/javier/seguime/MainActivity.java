package pc.javier.seguime;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import pc.javier.seguime.adaptador.Menu;
import pc.javier.seguime.adaptador.Preferencias;
import pc.javier.seguime.control.ControlPantallaPrincipal;
import pc.javier.seguime.vista.PantallaPrincipal;
import utilidades.basico.MensajeRegistro;
import utilidades.eventos.MiniTunel;


public class MainActivity extends AppCompatActivity {


    private PantallaPrincipal pantalla;
    private Preferencias preferencias;


    private Menu menu;
    private ControlPantallaPrincipal control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MiniTunel.ACTIVADO = true;
        setContentView(R.layout.activity_main);
        iniciarAplicacion();
    }

    @Override
    protected void onResume () {
        super.onResume();
        control.regresar ();
        personalizarMenu();
    }




    // menú superior ---------------
    @Override
    public boolean onCreateOptionsMenu (android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        if  (preferencias.getSesionIniciada())
            if (pantalla.getTextView(R.id.menu_texto_usuario) != null)
                pantalla.setTextView(R.id.menu_texto_usuario, preferencias.getUsuario());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        menu.alternar();
        return true;
    }






    // -------------------------------------------------------------------------- TECLAS

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU)
            menu.alternar ();
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (menu.abierto())
            menu.cerrar();
        else
            super.onBackPressed();
    }




    @Override
    protected void onDestroy() {
        MensajeRegistro.msj(this, "DESTRUYENDO MAIN ACTIVITY");
        control.destruir();
        super.onDestroy();
    }




    // --------------------------------------------------------------------------



    // prepara el inicio de la aplicación

    private void iniciarAplicacion () {


        menu = new Menu(this);


        Context contexto = getApplicationContext();
        preferencias = new Preferencias(contexto);
        preferencias.configuracionInicial();

        // controlador de pantalla
        pantalla = new PantallaPrincipal(this);

        control = new ControlPantallaPrincipal(pantalla, preferencias);


        // muesta el menu por primera vez
        if (!preferencias.getMenuInicial()) {
            preferencias.setMenuInicial(true);
            menu.abrir();
        }

        // recupera el estado activo de la aplicación
        if (preferencias.getServicioActivo())
            control.iniciarServicio();

    }






















    // -------------------------------------------------------------------------- BOTÓN



    // pulsa el botón de iniciar / detener la aplicación
    public void iniciar (View v) {
        control.botonIniciar();
        personalizarMenu();

    }




    // --------------------------------------------------------------------------







    // clicks en iconos

    public void clickrastreo (View v) {
        pantalla.bocadillo(R.string.principal_rastreo);
        pantalla.animar_toque(v);
    }
    public void clickseguime (View v) {
        pantalla.bocadillo(R.string.principal_aplicacion);
        pantalla.animar_toque(v);
    }
    public void clickalarma (View v) {
        pantalla.bocadillo(R.string.principal_alarma);
        pantalla.animar_toque(v);
    }
    public void clickinternet (View v) {
        pantalla.bocadillo( R.string.principal_internet);
        pantalla.animar_toque(v);
    }
    public void clickalarmaservidor (View v) {
        pantalla.bocadillo(R.string.principal_alarmaservidor);
        pantalla.animar_toque(v);
    }
    public void clickbloqueo (View v) {
        pantalla.bocadillo(R.string.txt_bloqueado);
        pantalla.animar_toque(v);
    }




    // cierra el msj de la pantalla
    public void cerrarMensaje (View v) {
        control.cerrarMensaje();
    }





    // --------------------------------------------------------------------------









    // Menú lateral -----------------------------------------------------------------

    public void menuClick (MenuItem item) {
        menu.cerrar();
        control.click (item);
    }





    private void personalizarMenu () {
        if (preferencias.getVersionRegistrada())
            menu.versionRegistrada();

        if (preferencias.getSesionIniciada())
            menu.sesionIniciada(preferencias.getUsuario());








        menu.aplicacionBloqueada(preferencias.getBloqueado());


        if (!preferencias.getBloqueado())
            menu.aplicacionActiva(control.servicioActivo());

    }





}
