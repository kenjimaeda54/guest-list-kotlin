package com.example.guestlist.view.viewHolder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guestlist.R
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.view.listner.ListenerGuest

//classe ViewHolderGuest extend de RecyclerView, então os parâmetros vai na classe, nao na extensão
class ViewHolderGuest(itemView: View, private val listener: ListenerGuest) :
    RecyclerView.ViewHolder(itemView) {
    //viewHolder so trata das atribuições.
    // Navegações,eventos de click, sao responsabilidades do  fragment,view .
    // Então vai ser criado uma interface para pegar acao de click da viewHolder e
    //compartilhar com a fragment
    //ela vai tratar das atribuições dos valores para layout
    //exemplo o text dos nomes dos convidados
    fun bind(guest: GuestModel) {
        val name = itemView.findViewById<TextView>(R.id.text_name_row)
        name.text = guest.name
        name.setOnClickListener {
            listener.onCLick(guest.id)
        }
        name.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.confirmacao) { dialog, wich ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }

    }

}