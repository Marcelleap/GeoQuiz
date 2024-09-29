package com.example.geoquiz_v4_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestoesDBHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "questoesDB";

    public QuestoesDBHelper(Context context) {
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ QuestoesDbSchema.QuestoesTbl.NOME+ "("+
                "_id integer PRIMARY KEY autoincrement,"+
                QuestoesDbSchema.QuestoesTbl.Cols.UUID+ "TEXT,"+
                QuestoesDbSchema.QuestoesTbl.Cols.QUESTAO_CORRETA + "INTEGER,"+
                QuestoesDbSchema.QuestoesTbl.Cols.TEXTO_QUESTAO+ "TEXT)");

        db.execSQL("CREATE TABLE " + QuestoesDbSchema.RespostasTbl.NAME + " (" +
                "_id integer PRIMARY KEY autoincrement," +  // I
                QuestoesDbSchema.RespostasTbl.Cols.UUID_QUESTAO + " TEXT," +
                QuestoesDbSchema.RespostasTbl.Cols.RESPOSTA_CORRETA + " INTEGER," +
                QuestoesDbSchema.RespostasTbl.Cols.RESPOSTA_OFERECIDA + " TEXT," +
                QuestoesDbSchema.RespostasTbl.Cols.COLOU + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
            // Política de upgrade é simplesmente descartar o conteúdo e começar novamente
            db.execSQL("DROP TABLE IF EXISTS " + QuestoesDbSchema.QuestoesTbl.NOME);
            db.execSQL("DROP TABLE IF EXISTS " + QuestoesDbSchema.RespostasTbl.NAME);
            onCreate(db);
    }
}