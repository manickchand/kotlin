package com.br.kotlinexample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast


import com.br.kotlinexample.adapter.AdapterRV
import com.br.kotlinexample.interfaces.ReciclerViewOnClickListenerHack
import com.br.kotlinexample.model.BdCore
import com.br.kotlinexample.model.PeopleModel

/**
 * Created by Ocean on 30/10/2017.
 */

// extende AppCompatActivity e implementa ReciclerViewOnClickListenerHack
class MainActivityK: AppCompatActivity(),
        ReciclerViewOnClickListenerHack{

    /*OS COMPONENTES DO LAYOUT PODEM SER ACESSADOS DIRETAMENTE PELO ID,
    SEM PRECISAREM SER DECLARADOS COMO ATRIBUTOS
    COMO ESTAO OS EDITTEXTS DE AddPeopleActivity.kt
    */

    val bdCore = BdCore(this)// instancia do banco sqlite
    lateinit var listaPessoas: ArrayList<PeopleModel> //lista que vai receber pessoas do banco, lateinit quer dizer que sera inicializada depois
    lateinit var rv:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // seta titulo da tolbar
        var mToolbar:Toolbar = findViewById(R.id.toolbarHome) as Toolbar
        mToolbar.title="Kotlin Example"

        //fab
        var fab:FloatingActionButton = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener{
            //vai para AddPeopleActivity
            val intent = Intent(this, AddPeopleActivity::class.java)
            startActivity(intent)
        }

        //layout maneger do recyclerview
        var llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        rv = findViewById(R.id.rv) as RecyclerView
        rv.setHasFixedSize(true)
        rv.layoutManager = llm
    }

    override fun onResume() {
        super.onResume()

        listaPessoas = bdCore.listar()// recebe lista do banco

        var adapter = AdapterRV(this,listaPessoas)
        adapter.setReciclerViewOnClickListenerHack(this)
        rv.adapter = adapter

        adapter.notifyDataSetChanged()

        // mostra pessoas no log
        for (i in listaPessoas){
            Log.i("Pessoa","id ${i.id}")
            Log.i("Pessoa","nome ${i.nome}")
            Log.i("Pessoa","email ${i.email}")
            Log.i("Pessoa","idade ${i.idade}")
        }

    }

    override fun onClickListener(v: View, position: Int) {
        Toast.makeText(this,"clicado no ${listaPessoas.get(position).nome}",Toast.LENGTH_SHORT).show()
    }

}