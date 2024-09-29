package com.example.geoquiz_v4_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private Button mBotaoVerdadeiro;
    private Button mBotaoFalso;
    private Button mBotaoProximo;
    private Button mBotaoMostra;
    private Button mBotaoDeleta;
    private Button mBotaoCola;


    private TextView mTextViewQuestao;
    private TextView mTextViewQuestoesArmazenadas;

    private static final String TAG = "QuizActivity";
    private static final String CHAVE_INDICE = "INDICE";
    private static final int CODIGO_REQUISICAO_COLA = 0;

    private Questao[] mBancoDeQuestoes = new Questao[]{
            new Questao(R.string.questao_suez, true),
            new Questao(R.string.questao_alemanha, false)
    };

    QuestaoDB mQuestoesDb;
    private int mIndiceAtual = 0;
    private boolean mEhColador;

    @Override
    protected void onCreate(Bundle instanciaSalva) {
        super.onCreate(instanciaSalva);
        setContentView(R.layout.activity_main);

        // Inicializando os botões e TextViews
        mTextViewQuestao = (TextView) findViewById(R.id.view_texto_da_questao);
        atualizaQuestao();

        mBotaoVerdadeiro = (Button) findViewById(R.id.botao_verdadeiro);
        mBotaoVerdadeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificaResposta(true);
            }
        });

        mBotaoFalso = (Button) findViewById(R.id.botao_falso);
        mBotaoFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificaResposta(false);
            }
        });

        mBotaoProximo = (Button) findViewById(R.id.botao_proximo);
        mBotaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIndiceAtual = (mIndiceAtual + 1) % mBancoDeQuestoes.length;
                mEhColador = false;
                atualizaQuestao();
            }
        });

        mBotaoCola = (Button) findViewById(R.id.botao_cola);
        mBotaoCola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean respostaEVerdadeira = mBancoDeQuestoes[mIndiceAtual].isRespostaCorreta();
                Intent intent = ColaActivity.novoIntent(MainActivity.this, respostaEVerdadeira);
                startActivityForResult(intent, CODIGO_REQUISICAO_COLA);
            }
        });

        mBotaoMostra = (Button) findViewById(R.id.botao_mostra_questoes);
        mBotaoMostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuestoesDb == null) return;
                if (mTextViewQuestoesArmazenadas == null) {
                    mTextViewQuestoesArmazenadas = (TextView) findViewById(R.id.texto_questoes_a_apresentar);
                } else {
                    mTextViewQuestoesArmazenadas.setText("");
                }
                Cursor cursor = mQuestoesDb.queryQuestao(null, null);
                if (cursor != null) {
                    if (cursor.getCount() == 0) {
                        mTextViewQuestoesArmazenadas.setText("Nada a apresentar");
                        Log.i("MSGS", "Nenhum resultado");
                    }
                    try {
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            String texto = cursor.getString(cursor.getColumnIndex(QuestoesDbSchema.QuestoesTbl.Cols.TEXTO_QUESTAO));
                            Log.i("MSGS", texto);
                            mTextViewQuestoesArmazenadas.append(texto + "\n");
                            cursor.moveToNext();
                        }
                    } finally {
                        cursor.close();
                    }
                } else
                    Log.i("MSGS", "cursor nulo!");
            }
        });

        mBotaoDeleta = (Button) findViewById(R.id.botao_deleta);
        mBotaoDeleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mQuestoesDb != null) {
                    mQuestoesDb.removeBanco();
                    if (mTextViewQuestoesArmazenadas == null) {
                        mTextViewQuestoesArmazenadas = (TextView) findViewById(R.id.texto_questoes_a_apresentar);
                    }
                    mTextViewQuestoesArmazenadas.setText("");
                }
            }
        });

    }

    private void atualizaQuestao() {
        int questao = mBancoDeQuestoes[mIndiceAtual].getTextoRespostaId();
        mTextViewQuestao.setText(questao);
    }

    private void verificaResposta(boolean respostaPressionada) {
        boolean respostaCorreta = mBancoDeQuestoes[mIndiceAtual].isRespostaCorreta();
        int idMensagemResposta = 0;

        if (mEhColador) {
            idMensagemResposta = R.string.toast_julgamento;
        } else {
            if (respostaPressionada == respostaCorreta) {
                idMensagemResposta = R.string.toast_correto;
            } else
                idMensagemResposta = R.string.toast_incorreto;
        }

        Toast.makeText(this, idMensagemResposta, Toast.LENGTH_SHORT).show();

        // Registrar a resposta no banco
        if (mQuestoesDb != null) {
            String uuidQuestao = mBancoDeQuestoes[mIndiceAtual].getId().toString(); // Atualizar aqui
            int respostaCorretaInt = respostaCorreta ? 1 : 0;
            String respostaOferecida = respostaPressionada ? "verdadeiro" : "falso";
            int colou = mEhColador ? 1 : 0; // Se o usuário colou
            mQuestoesDb.inserirResposta(uuidQuestao, respostaCorretaInt, respostaOferecida, colou);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle instanciaSalva) {
        super.onSaveInstanceState(instanciaSalva);
        Log.i(TAG, "onSaveInstanceState()");
        instanciaSalva.putInt(CHAVE_INDICE, mIndiceAtual);
    }

    @Override
    protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent dados) {
        if (codigoResultado != Activity.RESULT_OK) {
            return;
        }
        if (codigoRequisicao == CODIGO_REQUISICAO_COLA) {
            if (dados != null) {
                mEhColador = ColaActivity.foiMostradaResposta(dados);
            }
        }
    }
}
