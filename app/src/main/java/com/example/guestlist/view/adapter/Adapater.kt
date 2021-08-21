package com.example.guestlist.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.guestlist.R
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.view.viewHolder.ViewHolderGuest


class AdapterGuest : RecyclerView.Adapter<ViewHolderGuest>() {

    private var mListGuest: List<GuestModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGuest {
        //crio o layout que vai ficar nossos filhos
        //ultimo parâmetro do inflate,seria true se nao possuísse um pai
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return ViewHolderGuest(item)
    }

    //este método sera responsável por percorrer a lista e mostrar os atributos dela,exemplo, nome,email...
    override fun onBindViewHolder(holder: ViewHolderGuest, position: Int) {
        holder.bind(mListGuest[position])
    }

    override fun getItemCount(): Int {
        return mListGuest.count()
    }

    fun updatesGuest(list: List<GuestModel>) {
        mListGuest = list
        notifyDataSetChanged()

    }

}


