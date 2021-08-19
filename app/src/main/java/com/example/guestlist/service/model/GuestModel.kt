package com.example.guestlist.service.model

//quando voce pretende passar varias informacoes para funcoes que esta em outras classes e boa pratica criar um model,assim preserva mesmo contrato
//entre as funcoes que estao executando esse método. Por exmeplo este modelo eta sendo usando no viewModel e repository
data class GuestModel(var  id:Int = 0,var name: String, var presence: Boolean)