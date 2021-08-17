package utilidades.menu_lateral_anterior;

import android.app.Activity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


/**
 * Javier 2019.
 */

public class PanelLateral {
    protected DrawerLayout drawer;
    protected Activity activity;
    //private int id = R.id.drawer_layout;

    public PanelLateral(Activity activity, int id) {
        drawer = (DrawerLayout) activity.findViewById(id);
        this.activity= activity;


    }


    public void abrir () {
                drawer.openDrawer(GravityCompat.START);
    }

    public void cerrar () {
                drawer.closeDrawer(GravityCompat.START);
    }

    public void alternar () {
            if (drawer.isDrawerOpen(GravityCompat.START))
                drawer.closeDrawer(GravityCompat.START);
            else
                drawer.openDrawer(GravityCompat.START);
    }

    // consulta si el menú está abierto
    public boolean abierto () {
            return drawer.isDrawerOpen(GravityCompat.START);
    }


    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

}
