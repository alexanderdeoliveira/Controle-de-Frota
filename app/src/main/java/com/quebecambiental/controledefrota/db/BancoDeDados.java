package com.quebecambiental.controledefrota.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper
{
    protected Context context;
    private static BancoDeDados	instancia;

    public BancoDeDados(Context context) {
        super(context, "controleDeFrota.db", null, 1);
        this.context = context;
    }

    public static synchronized BancoDeDados getInstance(Context context) {
        if (instancia == null)
            instancia = new BancoDeDados(context);
        return instancia;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MOTORISTA(" +
                "  MOCODMOTORISTA    INTEGER PRIMARY KEY" +
                ", MONOME    TEXT     " +
                ", MONUMEROCELULAR  TEXT     " +
                ", MORFID    INTEGER     " +
                ")");

        db.execSQL("CREATE TABLE VEICULO(" +
                "  VECODVEICULO    INTEGER PRIMARY KEY" +
                ", VEPLACAVEICULO    TEXT     " +
                ", VEGRUPOFROTA    TEXT     " +
                ", FOREIGN KEY(VEGRUPOFROTA) REFERENCES GRUPOFROTA(GFCODGRUPOFROTA)"+
                ")");

        db.execSQL("CREATE TABLE GRUPOFROTA(" +
                "  GFCODGRUPOFROTA    INTEGER PRIMARY KEY" +
                ", GFNOMEGRUPOFROTA    TEXT     " +
                ")");

        db.execSQL("CREATE TABLE ITEM(" +
                "  ITCODITEM    INTEGER PRIMARY KEY" +
                ", ITNOMEITEM    TEXT     " +
                ", ITCODGRUPOFROTA INTEGER     " +
                ", FOREIGN KEY(ITCODGRUPOFROTA) REFERENCES GRUPOFROTA(GFCODGRUPOFROTA)"+
                ")");

        db.execSQL("CREATE TABLE ITEMMARCADO(" +
                "  IMCODITEM    INTEGER " +
                ", IMCAMINHOINFOTO    TEXT     " +
                ", IMCAMINHOEXFOTO INTEGER     " +
                ", IMCODHISTORICOMARCACAO    INTEGER " +
                ", FOREIGN KEY(IMCODITEM) REFERENCES ITEM(ITCODITEM)"+
                ", FOREIGN KEY(IMCODHISTORICOMARCACAO) REFERENCES HISTORICOMARCACAO(HMCODHISTORICOMARCACAO)"+
                ")");


        db.execSQL("CREATE TABLE HISTORICOMARCACAO(" +
                "  HMCODHISTORICOMARCACAO    INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", HMDATA    INTEGER" +
                ", HMCODMOTORISTA    INTEGER     " +
                ", FOREIGN KEY(HMCODMOTORISTA) REFERENCES MOTORISTA(MOCODMOTORISTA)"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
