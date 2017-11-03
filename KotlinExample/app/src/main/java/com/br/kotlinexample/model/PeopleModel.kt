package com.br.kotlinexample.model

import java.io.Serializable

/**
 * Created by Ocean on 30/10/2017.
 */

// extends Serializable
// existe um construtor vazio implicito
// caso quisesse passar parametros seria class PeopleModel constructor(nome:String,....):Serializable
class PeopleModel:Serializable{

//ao criar variáveis os getters e setters sao gerados implicitamentes
var nome:String?
var email:String?
var idade:Int?
var id:Int?

//bloco de inicializacao de atributos
init {
    this.nome=null
    this.email=null
    this.idade=null
    this.id=null
}
}