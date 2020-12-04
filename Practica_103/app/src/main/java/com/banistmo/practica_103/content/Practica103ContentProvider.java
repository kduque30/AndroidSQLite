package com.banistmo.practica_103.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.banistmo.practica_103.db.DbHelper;
import com.banistmo.practica_103.db.EstructuraDb;

public class Practica103ContentProvider extends ContentProvider {
    public static final String AUTORIDAD = "banistmo";
    public static final String URI = "content://" + AUTORIDAD + "/" + "Practica103ContentProvider";
    public static final Uri CONTENT_URI = Uri.parse(URI);

    public static final int ALL_ELEMENT = 1;
    public static final int SINGLE_ELEMENT = 2;
    private static UriMatcher URI_MATCHER = null;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTORIDAD, "Practica103ContentProvider", ALL_ELEMENT);
        URI_MATCHER.addURI(AUTORIDAD, "Practica103ContentProvider/#", SINGLE_ELEMENT);
    }
    private SQLiteDatabase database;
    public Practica103ContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)){
            case ALL_ELEMENT:
                return AUTORIDAD + " " + ALL_ELEMENT;
            case SINGLE_ELEMENT:
                return AUTORIDAD + " " + SINGLE_ELEMENT;
            default:
                throw new IllegalArgumentException("El uri no es correcto: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        DbHelper dbHelper = new DbHelper(getContext());
        database = dbHelper.getReadableDatabase();
        return database != null && database.isOpen();
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String id = null;
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(EstructuraDb.NOMBRE_TABLA);
        if (URI_MATCHER.match(uri) == SINGLE_ELEMENT){
            id = uri.getPathSegments().get(1);
            builder.appendWhere(EstructuraDb.COL1 + " = " + id);
        }
        return builder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}