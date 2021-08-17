package aplicacion.panico;

import android.app.Activity;

import pc.javier.seguime.R;
import pc.javier.seguime.adaptador.Pantalla;

/**
 * Created by Javier on 26/7/2019.
 */

public class PantallaPanico extends Pantalla {
    public PantallaPanico(Activity activity) {
        super(activity);
    }




    public void setPanicoBloquear (boolean valor) {
        setToggle(R.id.opciones_panico_bloquear, valor);
    }




    public boolean getPanicoBloquear () {
        return getToggle (R.id.opciones_panico_bloquear).isChecked();
    }

    public void setPanicoBloquearInactivo () {
        getToggle(R.id.opciones_panico_bloquear).setEnabled(false);
    }

}
