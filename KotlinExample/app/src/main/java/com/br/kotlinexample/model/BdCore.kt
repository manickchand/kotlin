package com.br.kotlinexample.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.br.kotlinexample.util.*

/**
 * Created by Ocean on 31/10/2017.
 */


//BdCore(Construtor bdcore) extende SQLiteOpenHelper (construtor SQLiteOpenHelper)

class BdCore(context: Context): SQLiteOpenHelper(context,nome_BD , null, versao_BD){

    //se db != null cria tabela
    override fun onCreate(db: SQLiteDatabase?) {
       db!!.execSQL(sqlCreateTabela_bd)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(sqlDropTabela_bd)
        onCreate(db);
    }

    fun inserir(pessoa:PeopleModel){

        //getWritableDatabase()
        val bd = writableDatabase

        // new ContentValues
        var cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("email", pessoa.email)
        cv.put("idade", pessoa.idade)

        bd.insert(tabela_BD,null,cv)
        bd.close()
    }

    fun listar():ArrayList<PeopleModel>{

        //getReadableDatabase()
        val bd = readableDatabase

        var lista:ArrayList<PeopleModel> = ArrayList()
        val sqlListar = "SELECT * FROM $tabela_BD"

        var cursor = bd.rawQuery(sqlListar,null)

        if(cursor.moveToFirst()){
            do {

                var p = PeopleModel()

                p.id = cursor.getInt(0)
                p.nome = cursor.getString(1)
                p.email = cursor.getString(2)
                p.idade = cursor.getInt(3)

               // Log.i("Pessoa","id ${p.id}")
               // Log.i("Pessoa","nome ${p.nome}")
                //Log.i("Pessoa","email ${p.email}")
                //Log.i("Pessoa","idade ${p.idade}")

                lista.add(p)

            }while (cursor.moveToNext())
        }
        bd.close()
        return lista
    }

}


