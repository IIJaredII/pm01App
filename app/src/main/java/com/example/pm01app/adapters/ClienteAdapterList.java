package com.example.pm01app.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pm01app.Funciones.imageUtils;
import com.example.pm01app.R;
import com.example.pm01app.models.Cliente;

import java.util.ArrayList;

public class ClienteAdapterList extends BaseAdapter {

    private Context context;
    private ArrayList<Cliente> clienteArrayList;

    public ClienteAdapterList(Context context, ArrayList<Cliente> clientes) {
        this.context = context;
        this.clienteArrayList = clientes;
    }

    @Override
    public int getCount() {
        return clienteArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return clienteArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cliente, parent, false);
            holder = new ViewHolder();
            holder.txtNombre = convertView.findViewById(R.id.txtNombre);
            holder.txtCorreo = convertView.findViewById(R.id.txtCorreo);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cliente cliente = clienteArrayList.get(position);
        holder.txtNombre.setText(cliente.getNombres() + " " + cliente.getApellidos());
        holder.txtCorreo.setText(cliente.getCorreo());
        Bitmap bitmap = imageUtils.decodeFromBase64(cliente.getImagen());
        holder.imageView.setImageBitmap(bitmap);

        return convertView;
    }

    static class ViewHolder {
        TextView txtNombre, txtCorreo;

        ImageView imageView;
    }
}
