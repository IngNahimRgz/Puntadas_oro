package com.example.arrec.navigation_view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.arrec.navigation_view.R;
import com.example.arrec.navigation_view.holders.HolderMensajes;
import com.example.arrec.navigation_view.model.Mensaje;
import com.example.arrec.navigation_view.model.MensajeRecibir;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MensajesAdapter extends RecyclerView.Adapter<HolderMensajes> {

    private List<MensajeRecibir> mensajeList = new ArrayList<>();
    private Context context;

    public MensajesAdapter( Context context) {
        this.context = context;
    }

    public void addMensaje(MensajeRecibir m){
        mensajeList.add(m);
        notifyItemInserted(mensajeList.size());
    }

    @NonNull
    @Override
    public HolderMensajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_mensajes, parent, false);
        return new HolderMensajes(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensajes holder, int position) {
        holder.getNombreMensaje().setText(mensajeList.get(position).getNombre());
        holder.getMensajeMensaje().setText(mensajeList.get(position).getMensaje());
        //holder.getHoraMensaje().setText(mensajeList.get(position).getHora());
        if (mensajeList.get(position).getTipo_mensaje().equals("2")){
            holder.getFotoMensajeEnviada().setVisibility(View.VISIBLE);
            holder.getMensajeMensaje().setVisibility(View.VISIBLE);
            Glide.with(context).load(mensajeList.get(position).getUrlFoto()).into(holder.getFotoMensajeEnviada());

        } else if(mensajeList.get(position).getTipo_mensaje().equals("1")){
            holder.getFotoMensajeEnviada().setVisibility(View.GONE);
            holder.getMensajeMensaje().setVisibility(View.VISIBLE);
        }

        Long codigoHora = mensajeList.get(position).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");// a para pm o am
        holder.getHoraMensaje().setText(sdf.format(d));

    }

    @Override
    public int getItemCount() {
        return mensajeList.size();
    }
}
