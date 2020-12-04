package com.banistmo.practica_103.db;

public class EstructuraDb {

    public static final String NOMBRE_TABLA = "Usuarios";
    public static final String COL2 = "Nombre";
    public static final String COL3 = "Correo";
    public static final String COL1 = "Id";
    public static final String TEXT_TYPE = " TEXT";
    public static final String SEP = ", ";

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+EstructuraDb.NOMBRE_TABLA+" ("
            +EstructuraDb.COL1 + " INTEGER PRIMARY KEY"+ EstructuraDb.SEP
            +EstructuraDb.COL2 + EstructuraDb.TEXT_TYPE+ EstructuraDb.SEP
            +EstructuraDb.COL3 + EstructuraDb.TEXT_TYPE+ ")";


    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+EstructuraDb.NOMBRE_TABLA;

    private EstructuraDb(){

    }
}
