package com.example.guestlist.service.model

//quando voce pretende passar varias informacoes para funcao que esta em outra classe e boa pratica criar um model,assim preserva mesmo contrato
//entre as funcoes que estao executando esse metodo
data class GuestModel(var name: String, var presence: Boolean)