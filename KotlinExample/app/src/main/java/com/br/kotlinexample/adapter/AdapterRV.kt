package com.br.kotlinexample.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.br.kotlinexample.R

import com.br.kotlinexample.model.PeopleModel
import kotlinx.android.synthetic.main.lista_layout.view.*
import com.br.kotlinexample.interfaces.ReciclerViewOnClickListenerHack


/**
 * Created by Ocean on 31/10/2017.
 */

// AdapterRV construtor() extende RecyclerView.Adapter construtor()
class AdapterRV (context: Context, lista: ArrayList<PeopleModel>): RecyclerView.Adapter<AdapterRV.MyViewHolder>() {

    var mContext =context
    var mLista = lista
    var mlayoutInflater:LayoutInflater = mContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater //
    var mReciclerViewOnClickListenerHack: ReciclerViewOnClickListenerHack? = null // interface de click
    lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder{
        view = mlayoutInflater.inflate(R.layout.lista_layout,parent,false)// qual layout vai ter cada linha
        return MyViewHolder(view)
    }

    // seta dados de cada pessoa em cada linha
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvNome.text = mLista.get(position).nome
        holder.tvEmail.text = mLista.get(position).email
        holder.tvIdade.text = mLista.get(position).idade.toString()
    }

    override fun getItemCount(): Int {
        return mLista.size
    }

    fun setReciclerViewOnClickListenerHack(r: ReciclerViewOnClickListenerHack) {
        this.mReciclerViewOnClickListenerHack = r
    }

    // inner class para poder acessar os metodos e atributos da classe pai
    inner class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var tvNome:TextView
        var tvEmail:TextView
        var tvIdade:TextView

        // bloco de inicializacao
        init {
            itemView.setOnClickListener(this)
             tvNome = itemView.textViewNome
             tvEmail = itemView.textViewEmail
             tvIdade = itemView.textViewIdade
        }

        // clickListener de cada posicao do adapter
        override fun onClick(v: View?) {
            if (mReciclerViewOnClickListenerHack != null) {
               mReciclerViewOnClickListenerHack!!.onClickListener(v!!, adapterPosition);
            }
        }
    }

}
