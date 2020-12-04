package com.banistmo.practica_103;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.banistmo.practica_103.db.DbHelper;
import com.banistmo.practica_103.db.EstructuraDb;

public class MostrarActivity extends AppCompatActivity {

    private TextView etInformacion;
    private DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        etInformacion = (TextView) findViewById(R.id.informacion);
        helper = new DbHelper(this);
        cargaInfo();
    }

    public void cargaInfo() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] proyeccion = {EstructuraDb.COL1,EstructuraDb.COL2, EstructuraDb.COL3};
        String seleccion = null; //Este es el where de la busqueda
        String[] seleccionArts = null; //Valores del where
        String sort = EstructuraDb.COL1 + " ASC";
        try {
            Cursor cursor = db.query(
                    EstructuraDb.NOMBRE_TABLA,
                    proyeccion,
                    seleccion,
                    seleccionArts,
                    null,
                    null,
                    sort
            );
            while (cursor.moveToNext()){
                etInformacion.append(cursor.getInt(0)+"\t");
                etInformacion.append(cursor.getString(1)+"\t");
                etInformacion.append(cursor.getString(2)+"\r\n");
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            limpiar();
            Toast.makeText(this, "Fallo de conexión", Toast.LENGTH_LONG).show();
        }

    }

    private void limpiar() {

    }
}