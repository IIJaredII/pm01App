package com.example.pm01app.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pm01app.Funciones.imageUtils;
import com.example.pm01app.R;
import com.example.pm01app.models.Cliente;

import java.util.ArrayList;

import com.example.pm01app.Activitys.DetallesClientes;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Cliente> listaClientes;

    public ClienteAdapter(Context context, ArrayList<Cliente> listaClientes) {
        this.context = context;
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente cliente = listaClientes.get(position);
        holder.txtNombre.setText(cliente.getNombres() + " " + cliente.getApellidos());
        holder.txtCorreo.setText(cliente.getCorreo());
        Bitmap bitmap = imageUtils.decodeFromBase64(cliente.getImagen());
        holder.imageviwe.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetallesClientes.class);
            intent.putExtra("nombre", cliente.getNombres() + " " + cliente.getApellidos());
            intent.putExtra("correo", cliente.getCorreo());
            intent.putExtra("imagen", cliente.getImagen());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtCorreo;
        ImageView imageviwe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCorreo = itemView.findViewById(R.id.txtCorreo);
            imageviwe = itemView.findViewById(R.id.imageView);
        }
    }
}
