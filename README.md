# guest-list-kotlin

Aplicativo lista de convidados


## Tabelas de conteudos

- Visão geral
  - <a href='#Desafio' > Desafio </a>
  - <a href='#Construção' > Construção </a>
  - <a href='#o-que-eu-aprendi' > Aprendizado </a>
  - <a href='#Feature' > Feature </a>
  - <a href='#Dependencias'> Dependencias </a>

## Visão Geral

## Desafio

- Construir uma aplicaco usando MVVM, banco de dados SQL e anti-parting Sngleton



## Construção

- React
- Android
- Kotlin
- Data Class

## O que eu aprendi

Aplicar conceitos do MVVM, aplicar conceitos do anti-parting Singleton,utilizacao do banco de dados SQL Lite,usar Recycle View.
 

Para lidar com listagens no Android usamos conceito de RecycleView, este, categoria de listagem se mostra mais performático que ListView, motivo que os itens são renderizados apenas uma vez, ou seja
ele renderiza aquilo que cabe na tela é mostra para usuário, List View nao tem esse tratamento. </br>
Para criar essa categoria de listagem ele segue 3 passos importantes, primeiro e obter Recycler, aqui foi usando o binding ao invés do findById, motivo foi apenas, porque no momento de criar o Drawer navigation
ele usou o método de binding para os fragmentos, mas para obter o recycle e apenas usar o “id” do android-recycleVIew.</br>
Apos isto você precisa criar o layout, este layout fica por conta do layoutManger. No caso usei o LinearLayoutManager() no caso vai ser renderizado uma lista linear de forma vertical.</br>
Por fim precisa do adapter ele que vai criar toda logica de negócio, sera a união entre os fragments e nossas lógicas bancos, api...

 
```kotlin


  private var _binding: FragmentAllBinding? = null
  
 // This property is only valid between onCreateView and
 // onDestroyView.
 private val binding get() = _binding!! 

 //para lidar com listagens nos criamos recycleView
//ele precisa 3 passo
//Obter Recycler
//binding foi criado pelo próprio android studio no momento de criar navegacao em drawer,
//bining.Id da view.
// Metodo findByid  nao referencia nest caso

//Obter o recycle
val recycle = binding.allGuest

//criar o layout
//layout linear por padrão vertical
recycle.layoutManager = LinearLayoutManager(context)

// adapter
recycle.adapter = mAdapter

        return root
 }
    
 ```
 
 ```xml
 //----------------------------------------------------------------------//
      <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/all_guest"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintLeft_toRightOf="parent"

        />
```

Abaixo este o exemplo de uma classe adapter. Nesse caso, criei uma classe e estendi da  ‘interface’ RecycleView.Adpater. Essa ‘interface’ possui três métodos o onCreate,Bind,getItem</br>
No onCreate criamos o layout que vai ser responsável por renderizar cada linha do nosso recleView, por esse motivo nesse layout a altura normalmente vai ser usado wrap content.
Se usarmos match parent, cada linha vai ocupar tela toda, gerando assim diferença de espaço enorme entre os itens é visualmente ficara ruim. Para nosso caso criamos um layout linearLayout
Bind sera responsável pelos atributos do nosso layout,por exemplo, usamos TextView para mostrar os itens na tela, esse textView e tratado no bind.</br>
Essa classe  herda interface ViewHolder, nos criamos a nossa própria e implementamos os membros. Por fim o método getIcon  apenas as contagens dos nossos filhos  da lita
Observacao: Nosso viewlHolder não trata da parte logica só dos atributos, recuperando os, id, editando os campos. Se desejamos logica da aplicação como lidar com banco, mudar de navegação, isto e a ViewModel,
, então criamos uma interface ListenerGuest, essa interface vai lidar com a lógica de adicionar deletar usurário.</br>
Com  intent.put(extras ) passamos parâmetros nas rotas, no caso passamos o id que vai ser usado como referência para adicionar ou deletar usuários.

```kotlin

class AdapterGuest : RecyclerView.Adapter<ViewHolderGuest>() {

    private var mListGuest: List<GuestModel> = arrayListOf()
    private lateinit var mListener: ListenerGuest

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGuest {
     
    }

    //este método sera responsável por percorrer a lista e mostrar os atributos dela,exemplo, nome,email...
    override fun onBindViewHolder(holder: ViewHolderGuest, position: Int) {
        
    }

    override fun getItemCount(): Int {
        
    }
}

//----------------------------------------------------------------------------//

class ViewHolderGuest(itemView: View, private val listener: ListenerGuest) :
    RecyclerView.ViewHolder(itemView) {

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

//---------------------------------------------//

interface ListenerGuest {
    fun onCLick(id: Int)
    fun onDelete(id: Int)
}





//-----------------------------------------//

class AllGuestFragment : Fragment() {


  mListener = object : ListenerGuest {
            override fun onCLick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                guestViewModel.delete(id)
                guestViewModel.load(GuestConstants.FILTER_ID.ALL)
            }

        }

}
  mAdapter.listenerGuest(mListener)


//-------------------------------------//

//-------------------------------------//


class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
  private fun load() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mGuestViewModel.load(mGuestId)
        }
    }

}

//----------------------------------------//
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="25dp">
<!--   altura precisa ser wrap_content por que o layout renderiza linha por linha,se for
altura total,cada linha vai ocupar total da tela-->

    <TextView
        android:id="@+id/text_name_row"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ola"
        android:textColor="@color/black"
        android:textSize="23sp" />
</LinearLayout>


```





# Feature
- MVVM
- SINGLETON
- SQL
- ANDROID
