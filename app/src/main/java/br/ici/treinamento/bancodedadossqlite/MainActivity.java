package br.ici.treinamento.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //Criar banco de dados
            SQLiteDatabase bancoDeDados = openOrCreateDatabase("app",MODE_PRIVATE,null);
            //Criar tabela
            bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT , nome VARCHAR, idade INT(3))");
            //bancoDeDados.execSQL("DROP TABLE pessoas");
            //Inserir dados
            //bancoDeDados.execSQL("INSERT INTO pessoas (nome, idade)VALUES('Augusta',18)");
            //bancoDeDados.execSQL("INSERT INTO pessoas (nome, idade)VALUES('Rodrigo',32)");

            //bancoDeDados.execSQL("UPDATE pessoas SET idade=19 WHERE nome = 'maria'");

            //Deletar dados da linha
            bancoDeDados.execSQL("DELETE FROM pessoas WHERE id = 1");



            //Recuperar os dados
            /*String consulta = "SELECT nome, idade FROM pessoas " +
                                "WHERE nome='jean' AND idade=28";*/

            /*String consulta = "SELECT nome, idade FROM pessoas " +
                                "WHERE idade >=28";*/

            /*String consulta = "SELECT nome, idade FROM pessoas " +
                                "WHERE nome IN('jean','Ceara')";*/

            /*String consulta = "SELECT nome, idade FROM pessoas " +
                                "WHERE idade BETWEEN 30 AND 54";*/
            /*String filtro = "mar";
            String consulta = "SELECT nome, idade FROM pessoas " +
                              "WHERE nome LIKE '%" +filtro+ "%'";*/

            String consulta = "SELECT id, nome, idade FROM pessoas " +
                    "WHERE 1=1 ORDER BY nome ASC";

            Cursor cursor = bancoDeDados.rawQuery(consulta ,null);
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor!=null){
                String nomeRecebe = cursor.getString(indiceNome);
                String idadeRecebe = cursor.getString(indiceIdade);
                String idRecebe = cursor.getString(indiceId);

                Log.i("Resultado - ID",idRecebe+" NOME: "+nomeRecebe +" IDADE: "+ idadeRecebe );
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
