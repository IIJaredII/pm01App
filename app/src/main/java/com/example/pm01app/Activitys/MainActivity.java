package com.example.pm01app.Activitys;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pm01app.R;
import com.example.pm01app.Repository.ClienteRepository;
import com.example.pm01app.configuracion.SQLiteConexion;
import com.example.pm01app.configuracion.Transacciones;
import com.example.pm01app.models.Cliente;

import java.util.ArrayList;

import com.example.pm01app.adapters.ClienteAdapter;

public class MainActivity extends AppCompatActivity {

    ClienteRepository clienteRepository = new ClienteRepository(this);
    RecyclerView listaClientes;
    ArrayList<Cliente> arrayListClientes;
    ClienteAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaClientes = findViewById(R.id.clientes);

        listaClientes.setLayoutManager(new LinearLayoutManager(this));
        arrayListClientes = clienteRepository.mostrarClientes();
        adapter = new ClienteAdapter(this, arrayListClientes);
        listaClientes.setAdapter(adapter);
    }
}