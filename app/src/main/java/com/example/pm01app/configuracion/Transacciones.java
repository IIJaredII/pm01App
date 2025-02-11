package com.example.pm01app.configuracion;

import com.example.pm01app.models.Cliente;

import java.util.ArrayList;

public class Transacciones {
    //nombre de la base de datos
    public static final String NameDB="PM01DB";

    // Tabla
    public static final String Tabla = "cliente";

    //Campos
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String correos = "correos";
    public static final String imagen = "imagen";

    //DDL Create Table
    public static final String CreateTableClient = "CREATE TABLE "+ Tabla+" ("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            nombres+" TEXT,"+
            apellidos+" TEXT,"+
            correos+" TEXT,"+
            imagen+ " TEXT)";

    public static final String DROPTableClient = "DROP TABLE IF EXISTS "+ Tabla;

    //DML
    public static final String SelectTableClient ="SELECT * FROM " + Tabla;

}
