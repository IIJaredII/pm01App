package com.example.pm01app.Activitys;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pm01app.Funciones.imageUtils;
import com.example.pm01app.R;

public class DetallesClientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalles_clientes);

        ImageView detalleImagen = findViewById(R.id.detalleImagen);
        TextView detalleNombre = findViewById(R.id.detalleNombre);
        TextView detalleCorreo = findViewById(R.id.detalleCorreo);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombre");
            String correo = extras.getString("correo");
            String imagenBitmap = extras.getString("imagen");

            detalleNombre.setText(nombre);
            detalleCorreo.setText(correo);

            if (imagenBitmap != null && !imagenBitmap.isEmpty()) {
                Bitmap imagen = imageUtils.decodeFromBase64(imagenBitmap);
                detalleImagen.setImageBitmap(imagen);
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}