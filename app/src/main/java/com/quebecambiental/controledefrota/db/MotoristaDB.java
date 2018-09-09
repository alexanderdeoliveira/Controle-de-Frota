package com.quebecambiental.controledefrota.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quebecambiental.controledefrota.negocio.Motorista;

import java.util.ArrayList;
import java.util.List;

public class MotoristaDB {
    private static MotoristaDB instancia;
    private Context contexto;

    private MotoristaDB(Context context) {
        contexto = context;
    }

    public static synchronized MotoristaDB getInstancia(Context contexto) {
        if (instancia == null)
            instancia = new MotoristaDB(contexto);
        return instancia;
    }

    public void salvarMotorista(Motorista motorista) {
        ContentValues valor = new ContentValues();

        valor.put("MOCODMOTORISTA", motorista.getCodigo());
        valor.put("MONOME", motorista.getNome());
        valor.put("MONUMEROCELULAR", motorista.getNumeroCelular());
        valor.put("MORFID", motorista.getRfId());

        SQLiteDatabase db = BancoDeDados.getInstance(contexto)
                .getWritableDatabase();

        db.insert("MOTORISTA", null, valor);
    }

    public List<Motorista> buscarMotoristas() {
        List<Motorista> motoristas = new ArrayList<>();

        SQLiteDatabase db = BancoDeDados.getInstance(contexto)
                .getReadableDatabase();

        String consulta = "SELECT MONOME, MOCODMOTORISTA, MONUMEROCELULAR, MORFID FROM MOTORISTA";

        Cursor cursor = db.rawQuery(consulta, null);

        while (cursor.moveToNext())
        {
            Motorista motorista = new Motorista();
            motorista.setNome(cursor.getString(cursor.getColumnIndex("MONOME")));
            motorista.setCodigo(cursor.getInt(cursor.getColumnIndex("MOCODMOTORISTA")));
            motorista.setNumeroCelular(cursor.getString(cursor.getColumnIndex("MONUMEROCELULAR")));
            motorista.setRfId(cursor.getString(cursor.getColumnIndex("MORFID")));

            motoristas.add(motorista);
        }

        cursor.close();

        return motoristas;
    }
}
