package com.example.arrec.navigation_view.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arrec.navigation_view.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderMensajes extends RecyclerView.ViewHolder {

    private TextView nombreMensaje, mensajeMensaje, horaMensaje;
    private CircleImageView fotoMensaje;
    private ImageView fotoMensajeEnviada;


    public HolderMensajes(View itemView) {
        super(itemView);

        nombreMensaje = itemView.findViewById(R.id.tvNombreMensaje);
        mensajeMensaje = itemView.findViewById(R.id.tvMensajeMensaje);
        horaMensaje = itemView.findViewById(R.id.tvHoraMensaje);
        fotoMensaje = itemView.findViewById(R.id.fotoMensaje);
        fotoMensajeEnviada = itemView.findViewById(R.id.mensajeFoto);
    }

    public ImageView getFotoMensajeEnviada() {
        return fotoMensajeEnviada;
    }

    public void setFotoMensajeEnviada(ImageView fotoMensajeEnviada) {
        this.fotoMensajeEnviada = fotoMensajeEnviada;
    }

    public TextView getNombreMensaje() {
        return nombreMensaje;
    }

    public void setNombreMensaje(TextView nombreMensaje) {
        this.nombreMensaje = nombreMensaje;
    }

    public TextView getMensajeMensaje() {
        return mensajeMensaje;
    }

    public void setMensajeMensaje(TextView mensajeMensaje) {
        this.mensajeMensaje = mensajeMensaje;
    }

    public TextView getHoraMensaje() {
        return horaMensaje;
    }

    public void setHoraMensaje(TextView horaMensaje) {
        this.horaMensaje = horaMensaje;
    }

    public CircleImageView getFotoMensaje() {
        return fotoMensaje;
    }

    public void setFotoMensaje(CircleImageView fotoMensaje) {
        this.fotoMensaje = fotoMensaje;
    }
}
