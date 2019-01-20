package dsa.upc.edu.minim2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dsa.upc.edu.minim2.R;
import dsa.upc.edu.minim2.object.Producto;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.ProductosViewHolder>
{
    private List<Producto> list;
    private int rowLayout;
    private Context context;

    public AdapterProductos(List<Producto> list, int rowLayout, Context context)
    {
        this.list = list;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    //Elementos de cada entrada de la lista
    public static class ProductosViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout productLayout;
        TextView nombre;
        TextView precio;
        TextView cantidad;

        public ProductosViewHolder(View v)
        {
            super(v);
            productLayout = (LinearLayout) v.findViewById(R.id.item_layout);
            nombre = (TextView) v.findViewById(R.id.nombre);
            precio = (TextView) v.findViewById(R.id.precio);
            cantidad = (TextView) v.findViewById(R.id.cantidad);
        }
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, int position)
    {
        holder.nombre.setText("Producto: "+list.get(position).getNombre());
        holder.precio.setText("Precio: "+list.get(position).getPrecio() + "€");
        holder.cantidad.setText("Cantidad: "+list.get(position).getCantidad());
    }
    @Override
    public int getItemCount() {  return list.size();  }
}