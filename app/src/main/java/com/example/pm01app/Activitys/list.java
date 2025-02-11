package com.example.pm01app.Activitys;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pm01app.R;
import com.example.pm01app.Repository.ClienteRepository;
import com.example.pm01app.adapters.ClienteAdapterList;
import com.example.pm01app.configuracion.SQLiteConexion;
import com.example.pm01app.configuracion.Transacciones;
import com.example.pm01app.models.Cliente;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    ListView listaCliente;
    ArrayList<Cliente> lista;
    ClienteRepository clienteRepository = new ClienteRepository(this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        listaCliente=(ListView)findViewById(R.id.listCliente);

        lista=clienteRepository.mostrarClientes();

        ListAdapter adapter = new ClienteAdapterList(this,lista);
        listaCliente.setAdapter(adapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}