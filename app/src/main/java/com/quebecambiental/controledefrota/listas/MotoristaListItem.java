package com.quebecambiental.controledefrota.listas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.quebecambiental.controledefrota.R;
import com.quebecambiental.controledefrota.negocio.Motorista;

public class MotoristaListItem extends RecyclerView.ViewHolder {
    TextView tvNomeMotorista;
    TextView tvNumeroCelular;

    public MotoristaListItem(View itemView) {
        super(itemView);

        tvNomeMotorista = itemView.findViewById(R.id.tvNomeMotorista);
        tvNumeroCelular = itemView.findViewById(R.id.tvNumeroCelular);
    }

    public void setItem(Motorista motorista) {
        tvNomeMotorista.setText(motorista.getNome());
        tvNumeroCelular.setText(motorista.getNumeroCelular());
    }

}
