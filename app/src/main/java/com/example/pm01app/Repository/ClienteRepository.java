package com.example.pm01app.Repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pm01app.configuracion.SQLiteConexion;
import com.example.pm01app.configuracion.Transacciones;
import com.example.pm01app.models.Cliente;

import java.util.ArrayList;

public class ClienteRepository
{
    private Context context;

    public ClienteRepository(Context context) {
        this.context = context;
    }

    public ArrayList<Cliente> mostrarClientes(){
        SQLiteConexion conexion = new SQLiteConexion(context);

        SQLiteDatabase db = conexion.getWritableDatabase();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente = null;
        Cursor cursor=null;

        cursor = db.rawQuery(Transacciones.SelectTableClient,null);

        if(cursor.moveToFirst()){
            do {
                cliente = new Cliente();
                cliente.setId(cursor.getInt(0));
                cliente.setNombres(cursor.getString(1));
                cliente.setApellidos((cursor.getString(2)));
                cliente.setCorreo(cursor.getString(3));
                cliente.setImagen(cursor.getString(4));
                listaClientes.add(cliente);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listaClientes;
    }
}
