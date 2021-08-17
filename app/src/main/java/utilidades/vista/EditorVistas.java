package utilidades.vista;

import android.app.Activity;
import android.graphics.Color;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

/**
 * Javier 2021.
 * Facilita el control de objetos de Pantalla
 * Es para utilizar principalmente por receptores de eventos
 *
 */

public abstract class EditorVistas {

    protected Activity actividad;
    protected View vistaPrincipal;

    protected boolean usarCache = true;
    protected View cache;
    private int idCache = -1;

    public final int VISIBILIDAD_OCULTA = View.GONE;
    public final int VISIBILIDAD_VISIBLE = View.VISIBLE;
    public final int VISIBILIDAD_INVISIBLE = View.INVISIBLE;

    // permite control total
    public EditorVistas (Activity activity) {
        this.actividad  =activity;
    }

    // permite manejo de elementos dentro de una vista especifica (ej: listas),
    // findViewById se ejecuta detro de una vista (ej: elemento)
    public EditorVistas (View  vista) {
        this.vistaPrincipal = vista;
    }


    // otro tipo de manejos
    public EditorVistas () { }



    // gets

    public ToggleButton getToggle (int id) { return (ToggleButton) getView(id); }
    public CheckBox getCheckBox (int id)   { return  (CheckBox) getView(id); }
    public RadioButton getRadio (int id) { return (RadioButton) getView(id); }
    public RadioGroup getGrupoRadio (int id) { return (RadioGroup) getView(id); }
    public EditText getEditText (int id) { return (EditText) getView(id); }
    public TextView getTextView (int id) { return (TextView) getView(id); }
    public ImageView getImageView (int id) { return (ImageView) getView(id); }
    public Button getButton (int id) { return (Button) getView(id); }
    public ImageButton getIButton (int id) { return (ImageButton) getView(id); }
    public NumberPicker getNumberPicker (int id) { return (NumberPicker) getView(id); }
    public ListView getListView (int id) { return (ListView) getView(id); }

    // Layout
    public LinearLayout getLinearLayout (int id) { return (LinearLayout) getView(id); }




    // gets cache

    public ToggleButton getToggle () { return (ToggleButton) cache; }
    public CheckBox getCheckBox ()   { return  (CheckBox) cache; }
    public RadioGroup getGrupoRadio () { return (RadioGroup) cache; }
    public RadioButton getRadio () { return (RadioButton) cache; }
    public EditText getEditText () { return (EditText) cache; }
    public TextView getTextView () { return (TextView) cache; }
    public ImageView getImageView () { return (ImageView) cache; }
    public Button getButton () { return (Button) cache; }
    public ImageButton getIButton () { return (ImageButton) cache; }
    public NumberPicker getNumberPicker () { return (NumberPicker) cache; }
    public ListView getListView () { return (ListView) cache; }



    public View getView (int id) {
        if (usarCache)
            if (id == idCache)
                return cache;
        idCache = id;
        cache = obtenerView(id);
        return cache;
    }

    private View obtenerView (int id) {
        if (vistaPrincipal != null)
            return vistaPrincipal.findViewById(id);
        if (actividad != null)
            return actividad.findViewById(id);

        // evita el error "null"
        // if (vistaPrincipal != null) return new View(vistaPrincipal.getContext());
        // if (activity != null) return new View(activity);

        return null;

    }


    // set ( id - valor )

    public void setCheckBox (int id, boolean chequeado) {
        CheckBox elemento = getCheckBox (id);
        if (elemento!= null)
            elemento.setChecked(chequeado);
    }

    public void setToggle (int id, boolean chequeado) {
        ToggleButton toggleButton = getToggle(id);
        if (toggleButton!= null)
            toggleButton.setChecked(chequeado);
    }

    public void setRadio (int id, boolean chequeado) {
        RadioButton radioButton = getRadio(id);
        if (radioButton!= null)
            radioButton.setChecked(chequeado);
    }

    public void setEditText (int id, String texto) {
        EditText editText = getEditText(id);
        if (editText != null)
            editText.setText(texto);
    }

    public void setTextView (int id, String texto) {
        TextView textView = getTextView(id);
        if (textView != null)
            textView.setText(texto);
    }

    public void setTextView (int id, int idTexto) {
        setTextView(id, getString(idTexto));
    }

    public void setNumberPicker (int id, int valor) {
        NumberPicker numberPicker = getNumberPicker(id);
        if (numberPicker != null)
            numberPicker.setValue(valor);
    }


    public void setButtonText (int id, String texto) {
        Button boton = getButton(id);
        if (boton != null)
            boton.setText(texto);
    }


    public void setButtonText (int id, int idTexto) {
        setButtonText(id, getString(idTexto));
    }







    // setview ( view, valor )

    public void setView (EditText view, String texto) {
        if (view != null)
            view.setText(texto);
    }

    public void setView (TextView view, String texto) {
        if (view != null)
            view.setText(texto);
    }

    public void setView (NumberPicker view, int valor) {
        if (view != null)
            view.setValue(valor);
    }

    public void setView (View view, int visibilidad) {
        if (view != null)
            view.setVisibility(visibilidad);
    }




    // vista principal ------------
    public View getVistaPrincipal() {
        return vistaPrincipal;
    }

    public void setVistaPrincipal(View vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
    }
    public void setVistaPrincipal(int id) {
        this.vistaPrincipal = getView(id);
    }







    /*
        VISIBILIDAD
     */


    public void setVisibilidad (int id, boolean visibilidad) {
        if (visibilidad)
            setVisibilidad(id, View.VISIBLE);
        else
            setVisibilidad(id, View.INVISIBLE);
    }

    public void setVisibilidad (int id, int visibilidad) {
        View view = getView(id);
        if (view != null)
            view.setVisibility(visibilidad);
    }

    public void intercambiar_visibilidad (int id) {
        View view = getView(id);
        if (view == null)
            return;

        if (view.getVisibility() == VISIBILIDAD_VISIBLE)
            setOculto(id);
        else
            setVisible(id);
    }







    public void setVisible (int id) {
        setVisibilidad(id, VISIBILIDAD_VISIBLE);
    }

    public void setInvisible (int id) {
        setVisibilidad(id, VISIBILIDAD_INVISIBLE);
    }


    public void setOculto (int id) {
        setVisibilidad(id, VISIBILIDAD_OCULTA);
    }


    public void setOculto (int id, boolean es_oculto) {
        if (es_oculto)
            setOculto(id);
        else
            setVisible(id);
    }



    public void setHabilitado (int id, boolean habilitado) {
        View view = getView(id);
        if (view != null)
            view.setEnabled(habilitado);
    }





    // rotación - inclinación

    public boolean estaModoPaisaje (View view) {
        if (view != null)
            return (view.getResources().getConfiguration().ORIENTATION_LANDSCAPE == inclinacionDePantalla(view));
        return false;
    }

    public boolean estaModoPortarretrato (View view) {
        if (view != null)
            return (view.getResources().getConfiguration().ORIENTATION_LANDSCAPE == inclinacionDePantalla(view));
        return true;
    }

    public int inclinacionDePantalla (View view) {
        if (view != null)
            return view.getResources().getConfiguration().orientation;
        return 0;
    }



    // bocadillo

    public void bocadillo(int idString) {
        bocadillo(getString(idString));
    }

    public void bocadillo(String mensaje) {

        if (vistaPrincipal != null) {
            Snackbar.make(vistaPrincipal, mensaje, 1000).show();
            return;
        }

        if (actividad != null)
            if (actividad.getCurrentFocus() != null) {
                Snackbar.make(actividad.getCurrentFocus(), mensaje, 10000).show();
                return;
            }




        toast (mensaje);
    }

    public void  toast (String mensaje) {
        if (actividad != null) {
            Toast.makeText(actividad, mensaje, Toast.LENGTH_LONG).show();
            return;
        }

        if (vistaPrincipal != null) {
            Toast.makeText(vistaPrincipal.getContext(), mensaje, Toast.LENGTH_LONG).show();
            return;
        }
    }






    public Activity getActivity () {
        return actividad;
    }

    public String getString (int id) {
        if (actividad != null)
            return actividad.getString(id);
        return "";
    }







    /*
    public void salir (View v) {
        // este codigo CIERRA LA APLICACION
        // fuente: https://jcristoballopez.wordpress.com/2015/03/20/anadir-boton-de-salida-con-android-studio/
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    */


    public void finalizarActividad () {
        if (actividad != null)
            actividad.finish();
    }


    // colores
    public int colorGris () { return Color.GRAY; }
    public int colorGrisOscuro () { return Color.DKGRAY; }
    public int colorVerde () { return Color.GREEN; }
    public int colorRojo () { return Color.RED; }

    public void setColor (View view, int color) { view.setBackgroundColor(color); }
    public void setColor (int id, int color) { setColor (getView(id), color); }

    public void setTextColor (TextView view, int color) { view.setTextColor(color); }
    public void setTextColor (int id, int color) { setTextColor (getTextView(id), color); }

    public void setFondo (View view, int fondo) { view.setBackgroundResource(fondo); }
    public void setFondo (int id, int fondo) { setFondo (getView(id), fondo); }



    /*
        Radio
     */

    public int getIdRadioSeleecionado (int id_grupo) {
        RadioGroup grupo = getGrupoRadio(id_grupo);
        if (grupo != null)
            return grupo.getCheckedRadioButtonId();
        return -1;
    }

    public RadioButton getRadioSeleecionado (int id_grupo) {
        RadioGroup grupo = getGrupoRadio(id_grupo);
        if (grupo != null)
            return getRadio(grupo.getCheckedRadioButtonId());

        return null;
    }


    public void agregarRadio (int id_grupo, View radioButton) {
        agregarRadio (getGrupoRadio(id_grupo), radioButton);
    }

    public void agregarRadio (RadioGroup grupo, View radioButton) {
        if (grupo == null)
            return;
        grupo.addView(radioButton);
    }



    public void radio_seleccionar_etiqueta (RadioGroup grupo, String etiqueta) {
        if (grupo == null)
            return;
        for (int x = 1; x < grupo.getChildCount(); x++) {
            if (grupo.getChildAt(x).getTag().toString().equals(etiqueta)) {
                ((RadioButton) grupo.getChildAt(x)).setChecked(true);
                return;
            }
        }
    }

    public void radio_seleccionar_etiqueta (int id_grupo, String etiqueta) {
        radio_seleccionar_etiqueta( getGrupoRadio(id_grupo), etiqueta);
    }



    // varios


    public String texto(int id) {
        return getEditText(id).getText().toString();
    }
    public void texto (int id, String texto) {
        getEditText(id).setText(texto);
    }


    public boolean checkbox (int id) {
        return getCheckBox(id).isChecked();
    }
    public void checkbox (int id, boolean chequeado) {
        getCheckBox(id).setChecked(chequeado);
    }

    public boolean toogle (int id) {
        return getToggle (id).isChecked();
    }
    public void toogle (int id, boolean chequeado) {
        getToggle (id).setChecked(chequeado);
    }


}
