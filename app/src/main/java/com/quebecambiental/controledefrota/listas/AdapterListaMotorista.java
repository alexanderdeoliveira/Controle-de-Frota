package com.quebecambiental.controledefrota.listas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quebecambiental.controledefrota.R;
import com.quebecambiental.controledefrota.negocio.Motorista;

import java.util.List;

public class AdapterListaMotorista extends RecyclerView.Adapter<MotoristaListItem> {

    List<Motorista> motoristas;

    public AdapterListaMotorista(List<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    @NonNull
    @Override
    public MotoristaListItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_motorista, parent, false);

        MotoristaListItem motoristaListItem = new MotoristaListItem(view);

        return motoristaListItem;
    }

    @Override
    public void onBindViewHolder(@NonNull MotoristaListItem holder, int position) {
        holder.setItem(motoristas.get(position));

    }

    @Override
    public int getItemCount() {
        return motoristas.size();
    }
}
