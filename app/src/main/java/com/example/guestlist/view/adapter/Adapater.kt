package com.example.guestlist.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.guestlist.R
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.view.listner.ListenerGuest
import com.example.guestlist.view.viewHolder.ViewHolderGuest


//recycleView e listView. RecycleVIew e superior a perofrmace porque o metodo onCreateViewHolder e chamado apenas uma vez,no momento de montar usa tela
//apos isto e o bind que fica responsavel por criar e linhas,isto garante performace porque nao precisa renderiza toda a tela novamente.
//no react native existe mesmo conceito para flatlist,ela cria os componentes suficiente para sua tela,nao renderiza componentes desnecessário.

class AdapterGuest : RecyclerView.Adapter<ViewHolderGuest>() {

    private var mListGuest: List<GuestModel> = arrayListOf()
    private lateinit var mListener: ListenerGuest

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGuest {
        //crio o layout que vai ficar nossos filhos
        //ultimo parâmetro do inflate,seria true se nao possuísse um pai
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return ViewHolderGuest(item,mListener)
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
    fun attachListener(listener: ListenerGuest){
        mListener = listener
    }

}


