package com.banistmo.practica_103;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.banistmo.practica_103.db.DbHelper;
import com.banistmo.practica_103.db.EstructuraDb;

public class CapturarActivity extends AppCompatActivity {

    private EditText etId, etNombre, etCorreo;
    private DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturar);
        etId = (EditText) findViewById(R.id.id);
        etNombre = (EditText) findViewById(R.id.nombre);
        etCorreo = (EditText) findViewById(R.id.correo);
        helper = new DbHelper(this);

    }

    public void salvarInformacion(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EstructuraDb.COL1, etId.getText().toString());
        values.put(EstructuraDb.COL2, etNombre.getText().toString());
        values.put(EstructuraDb.COL3, etCorreo.getText().toString());
        long nr = db.insert(EstructuraDb.NOMBRE_TABLA, null, values);
        db.close();
        limpiarCampos();
        Toast.makeText(this, "El registro: " + nr, Toast.LENGTH_LONG).show();
    }

    private void limpiarCampos() {
        etId.setText("");
        etNombre.setText("");
        etCorreo.setText("");
    }

    public void consultarInformacion(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] proyeccion = {EstructuraDb.COL1, EstructuraDb.COL2, EstructuraDb.COL3};
        String seleccion = EstructuraDb.COL1 + " = ?"; //Este es el where de la busqueda
        String[] seleccionArts = {etId.getText().toString()}; //Valores del where
        String sort = null;
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
            if (cursor.moveToNext()) {
                limpiarCampos();
                etNombre.append(cursor.getString(1));
                etCorreo.append(cursor.getString(2));
                etId.append(cursor.getInt(0) + "");
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            limpiarCampos();
            Toast.makeText(this, "Fallo de conexión", Toast.LENGTH_LONG).show();
        }

    }

    public void actualizarInformacion(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String seleccion = EstructuraDb.COL1 + " = ?";
        String[] seleccionArts = {etId.getText().toString()};
        values.put(EstructuraDb.COL1, etId.getText().toString());
        values.put(EstructuraDb.COL2, etNombre.getText().toString());
        values.put(EstructuraDb.COL3, etCorreo.getText().toString());
        long nr = db.update(EstructuraDb.NOMBRE_TABLA, values, seleccion, seleccionArts);
        db.close();
        limpiarCampos();
        Toast.makeText(this, "El registro: " + nr + " fue actualizado", Toast.LENGTH_LONG).show();

    }

    public void borrarInformacion(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String seleccion = EstructuraDb.COL1 + " = ?";
        String[] seleccionArts = {etId.getText().toString()};
        long nr = db.delete(EstructuraDb.NOMBRE_TABLA, seleccion, seleccionArts);
        db.close();
        limpiarCampos();
        Toast.makeText(this, "El registro: " + nr + " fue borrado", Toast.LENGTH_LONG).show();
    }
}