package com.example.pm01app.Activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pm01app.Funciones.imageUtils;
import com.example.pm01app.R;
import com.example.pm01app.configuracion.SQLiteConexion;
import com.example.pm01app.configuracion.Transacciones;

public class ActivityPrincipal extends AppCompatActivity {

    private ActivityResultLauncher<Intent> openCamara;
    private ActivityResultLauncher<Intent> galleryLauncher;

    EditText nombre, apellido, correo;
    String imagen="";
    Button btnprocesar,btnGaleria,btnCamara;
    ImageView vistaPrevia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        correo = (EditText) findViewById(R.id.correo);
        btnprocesar = (Button) findViewById(R.id.btnprocesar);
        btnGaleria = (Button) findViewById(R.id.btnGaleria);
        btnCamara = (Button) findViewById(R.id.btnCamara);
        vistaPrevia = (ImageView) findViewById(R.id.imgview);

        btnprocesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {AddClient();}
        });

        openCamara= registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        if (extras != null) {
                            Bitmap imagenBitmap = (Bitmap) extras.get("data");
                            vistaPrevia.setImageBitmap(imagenBitmap);
                            imagen = imageUtils.encodeToBase64(imagenBitmap);
                        }
                    }
                }
        );

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        try {
                            Bitmap imagenBitmap = imageUtils.fixImageRotation(this, selectedImageUri);
                            vistaPrevia.setImageBitmap(imagenBitmap);
                            imagen = imageUtils.encodeToBase64(imagenBitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            openCamara.launch(intent);
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void AddClient() {
        try {
            SQLiteConexion conexion = new SQLiteConexion(this);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(Transacciones.nombres, nombre.getText().toString());
            valores.put(Transacciones.apellidos, apellido.getText().toString());
            valores.put(Transacciones.correos, correo.getText().toString());
            valores.put(Transacciones.imagen,imagen);
            Long Result = db.insert(Transacciones.Tabla,Transacciones.id,valores);

            Toast.makeText(this,getString(R.string.registroingresado),Toast.LENGTH_SHORT).show();
            db.close();
            correo.setText(null);
            nombre.setText(null);
            apellido.setText(null);
            imagen=null;
            vistaPrevia.setImageDrawable(null);
        } catch (Exception ex) {
            Toast.makeText(this,getString(R.string.registroerroneo),Toast.LENGTH_SHORT).show();
        }

    }
}