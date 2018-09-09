package com.quebecambiental.controledefrota.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quebecambiental.controledefrota.negocio.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class VeiculoDB {
    private static VeiculoDB instancia;
    private Context contexto;

    private VeiculoDB(Context context) {
        contexto = context;
    }

    public static synchronized VeiculoDB getInstancia(Context contexto) {
        if (instancia == null)
            instancia = new VeiculoDB(contexto);
        return instancia;
    }

    public void salvarVeiculo(Veiculo veiculo) {
        ContentValues valor = new ContentValues();

        valor.put("VECODVEICULO", veiculo.getCodVeiculo());
        valor.put("VEPLACAVEICULO", veiculo.getPlaca());
        valor.put("VECODGRUPOFROTA", veiculo.getCodGrupo());

        SQLiteDatabase db = BancoDeDados.getInstance(contexto)
                .getWritableDatabase();

        db.insert("VEICULO", null, valor);
    }

    public List<Veiculo> buscarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();

        SQLiteDatabase db = BancoDeDados.getInstance(contexto)
                .getReadableDatabase();

        String consulta = "SELECT VECODVEICULO, VEPLACAVEICULO, VECODGRUPOFROTA FROM VEICULO";

        Cursor cursor = db.rawQuery(consulta, null);

        while (cursor.moveToNext())
        {
            Veiculo veiculo = new Veiculo();
            veiculo.setCodVeiculo(cursor.getInt(cursor.getColumnIndex("VECODVEICULO")));
            veiculo.setPlaca(cursor.getString(cursor.getColumnIndex("VEPLACAVEICULO")));
            veiculo.setCodGrupo(cursor.getInt(cursor.getColumnIndex("VECODGRUPOFROTA")));

            veiculos.add(veiculo);
        }

        cursor.close();

        return veiculos;
    }
}
