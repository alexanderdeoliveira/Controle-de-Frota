package com.quebecambiental.controledefrota.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quebecambiental.controledefrota.negocio.GrupoFrota;
import com.quebecambiental.controledefrota.negocio.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class GrupoFrotaDB {
        private static GrupoFrotaDB instancia;
        private Context contexto;

        private GrupoFrotaDB(Context context) {
            contexto = context;
        }

        public static synchronized GrupoFrotaDB getInstancia(Context contexto) {
            if (instancia == null)
                instancia = new GrupoFrotaDB(contexto);
            return instancia;
        }

        public void salvarGrupoFrota(GrupoFrota grupoFrota) {
            ContentValues valor = new ContentValues();

            valor.put("GFCODGRUPOFROTA", grupoFrota.getCodGrupo());
            valor.put("GFNOMEGRUPOFROTA", grupoFrota.getNomeGrupoFrota());

            SQLiteDatabase db = BancoDeDados.getInstance(contexto)
                    .getWritableDatabase();

            db.insert("GRUPOFROTA", null, valor);
        }

        public List<GrupoFrota> buscarGruposFrota() {
            List<GrupoFrota> grupos = new ArrayList<>();

            SQLiteDatabase db = BancoDeDados.getInstance(contexto)
                    .getReadableDatabase();

            String consulta = "SELECT GFCODGRUPOFROTA, GFNOMEGRUPOFROTA FROM GRUPOFROTA";

            Cursor cursor = db.rawQuery(consulta, null);

            while (cursor.moveToNext())
            {
                GrupoFrota grupoFrota = new GrupoFrota();

                grupoFrota.setCodGrupo(cursor.getInt(cursor.getColumnIndex("GFCODGRUPOFROTA")));
                grupoFrota.setNomeGrupoFrota(cursor.getString(cursor.getColumnIndex("GFNOMEGRUPOFROTA")));

                grupos.add(grupoFrota);
            }

            cursor.close();

            return grupos;
        }
}
