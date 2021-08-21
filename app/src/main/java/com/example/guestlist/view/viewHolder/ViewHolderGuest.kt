package com.example.guestlist.view.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guestlist.R
import com.example.guestlist.service.model.GuestModel

class ViewHolderGuest(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //ela vai tratar das atribuições dos valores para layout
    //exemplo o text dos nomes dos convidados
    fun bind(guest: GuestModel) {
        val text = itemView.findViewById<TextView>(R.id.text_name_row)
        text.text = guest.name
    }
}