package com.br.kotlinexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.br.kotlinexample.model.BdCore
import com.br.kotlinexample.model.PeopleModel
import kotlinx.android.synthetic.main.activity_add_people.*

class AddPeopleActivity : AppCompatActivity() {

    var pessoa = PeopleModel()
    var bdCore = BdCore(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)

        buttonSalvarPessoa.setOnClickListener {
            adicionarPessoa()
        }
    }

    // seta os atributos da pessoa e manda para salvar no banco
    fun adicionarPessoa(){

        if(verificaCampos()){
            pessoa.nome = editTextNome.text.toString()//acessa o componente direto pelo ID
            pessoa.email = editTextEmail.text.toString()
            pessoa.idade = Integer.parseInt(editTextIdade.text.toString())

            Log.i("pessoa","nome: ${pessoa.nome}")
            Log.i("pessoa","email: ${pessoa.email}")
            Log.i("pessoa","idade: ${pessoa.idade}")

            bdCore.inserir(pessoa)
            Toast.makeText(this,"${pessoa.nome} adicionado(a)!",Toast.LENGTH_SHORT).show()
            finish()
        }
        else{
            Toast.makeText(this,"Adicione todos os campos !",Toast.LENGTH_SHORT).show()
        }

    }

    // retorna true se todos os campos estiverem preenchidos, false caso o contr'ario
    fun verificaCampos():Boolean = if(editTextNome.text.length>0 && editTextEmail.text.length>0 && editTextIdade.text.length>0)true else false
}
