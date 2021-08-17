package pc.javier.seguime.control;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Javier on 14/07/2019.
 */

public class BDFoto {

    private SQLiteDatabase sql;
    private static String nombre = "fotografias";
    public static String TABLA = "" +
            "Create Table if not exists " + nombre + "(" +
            "ID integer primary key autoincrement," +
            "fecha text," +
            "imagen text," +
            "envios int," +
            "recibido int," +
            "fechaEnviado text,"+
            "extra text," +
            "codigo text" +
            ");"
            ;

    public static String RENOMBRARTABLA = "ALTER TABLE " + nombre + " RENAME TO " + nombre + "copia ;";



    public BDFoto (SQLiteDatabase db) {
        this.sql = db;
    }



    public boolean insertar(String fecha, String imagen, String codigo, String extra) {
        ContentValues valor = new ContentValues();
        valor.put("fecha", fecha);
        valor.put("imagen", imagen);
        valor.put("envios", 0);
        valor.put("recibido", 0);
        valor.put("extra", extra);
        valor.put("codigo", codigo);

        int respuesta = (int) sql.insert(nombre,null, valor);

        valor.clear();
        return  (respuesta > 0);
    }

    public void marcar(int id, String fecha) {

        ContentValues valor = new ContentValues();
        valor.put("recibido", "1");
        valor.put("fechaEnviado", fecha);
        mensajeLog ("marcando como recibido id= " + id);
        sql.update(nombre,valor,"id = " + id, null);
    }

    public void marcar(String codigo, String fecha) {
        ContentValues valor = new ContentValues();
        valor.put("recibido", "1");
        valor.put("fechaEnviado", fecha);
        mensajeLog ("marcando como recibido codigo= " + codigo);
        sql.update(nombre,valor,"codigo = " + codigo, null);
    }


    public boolean eliminar (int id) {
        return (sql.delete(nombre,"id = " + id, null) > 0);
    }

    //elimina todo
    public boolean eliminar () {
        return (sql.delete(nombre,null, null) > 0);
    }


    public Cursor obtener () {
        //Cursor c = sql.rawQuery("*",null);
        //Cursor c= sql.query(nombre,new String[]{"*"},null,null,null,null, null);
        return sql.query(nombre,new String[]{"*"},null,null,null,null,"fecha desc","1000");
    }


    public Cursor obtenerNuevas () {
        return  obtenerNuevas(5);
    }

    public Cursor obtenerNuevas (int cantidad) {
        return sql.query(nombre,new String[]{"*"},"recibido=0",null,null,null,"fecha desc",String.valueOf(cantidad));
    }


    public Cursor obtenerUltimaNoEnviada () {
        return sql.query(nombre,new String[]{"*"},"recibido=0",null,null,null,"fecha desc","1");
    }

    public Cursor obtenerUltima () {
        return sql.query(nombre,new String[]{"*"},null,null,null,null,"fecha desc","1");
    }




    private void mensajeLog (String texto) {
        Log.d("Basededatos FOTOGRAFIAS", texto);
    }

}
