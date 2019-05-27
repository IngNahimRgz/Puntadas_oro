package com.example.arrec.navigation_view.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arrec.navigation_view.R;
import com.example.arrec.navigation_view.adapters.MensajesAdapter;
import com.example.arrec.navigation_view.model.Mensaje;
import com.example.arrec.navigation_view.model.MensajeEnviar;
import com.example.arrec.navigation_view.model.MensajeRecibir;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatRealTime extends AppCompatActivity {

    private CircleImageView imgFotoPerfil;
    private TextView tvNombre;
    private RecyclerView rvMensajes;
    private EditText etMensaje;
    private Button btnEnviarMensaje;
    private MensajesAdapter adapter;
    private ImageButton btnEnviarImagen;
    private String FotoPerfilCadena;


    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("Packs");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fotoReferencia.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri u = uri;
                            MensajeEnviar m = new MensajeEnviar("User te ha enviado una foto", tvNombre.getText().toString(), FotoPerfilCadena,  "2", u.toString(), ServerValue.TIMESTAMP);
                            databaseReference.push().setValue(m);
                        }
                    });
                }
            });
        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("Packs");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fotoReferencia.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri u = uri;
                            FotoPerfilCadena = u.toString();
                            MensajeEnviar m = new MensajeEnviar("User ha actualizado su foto de perfil", tvNombre.getText().toString(), FotoPerfilCadena,  "2", u.toString(), ServerValue.TIMESTAMP);
                            databaseReference.push().setValue(m);
                            Glide.with(ChatRealTime.this).load(u.toString()).into(imgFotoPerfil);
                        }
                    });
                }
            });
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("foto_perfil");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fotoReferencia.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri u = uri;
                            FotoPerfilCadena = u.toString();
                            MensajeEnviar m = new MensajeEnviar("User ha cambiado su foto de perfil", tvNombre.getText().toString(), FotoPerfilCadena, "2", u.toString(), ServerValue.TIMESTAMP);
                            databaseReference.push().setValue(m);
                            Glide.with(ChatRealTime.this).load(u.toString()).into(imgFotoPerfil);
                        }
                    });
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_real_time);

        imgFotoPerfil = findViewById(R.id.ivFotoPerfil);
        tvNombre = findViewById(R.id.tvNombreChat);
        rvMensajes = findViewById(R.id.rvMensajes);
        etMensaje = findViewById(R.id.etMensaje);
        btnEnviarMensaje = findViewById(R.id.btnEnviarMensajeChat);
        btnEnviarImagen = findViewById(R.id.btnEnviarImagen);

        adapter = new MensajesAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMensajes.setLayoutManager(layoutManager);
        rvMensajes.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat"); // sala de chat ('nombre')
        storage = FirebaseStorage.getInstance();


        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new MensajeEnviar(etMensaje.getText().toString(), tvNombre.getText().toString(), "", "1", ServerValue.TIMESTAMP));
               etMensaje.setText("");
            }
        });

        btnEnviarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una imagen"), 1);
            }
        });

        imgFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una imagen"), 2);
            }
        });


        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();

            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setScrollbar() {
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }
}
