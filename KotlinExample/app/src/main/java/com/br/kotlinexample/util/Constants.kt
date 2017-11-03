package com.br.kotlinexample.util

/**
 * Created by Ocean on 31/10/2017.
 */

// val nao pode ser alterado o valor depois de atribuido. equivale ao final
    val nome_BD = "KotlinExemplo"
    val versao_BD = 1
    val tabela_BD = "Pessoas"
    val sqlCreateTabela_bd = "CREATE TABLE $tabela_BD (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT, idade INTEGER )";
    val sqlDropTabela_bd = "DROP TABLE IF EXISTS $tabela_BD";
