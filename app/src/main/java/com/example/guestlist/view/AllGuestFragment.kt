package com.example.guestlist.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guestlist.databinding.FragmentAllBinding
import com.example.guestlist.service.constants.GuestConstants
import com.example.guestlist.view.adapter.AdapterGuest
import com.example.guestlist.view.listner.ListenerGuest
import com.example.guestlist.viewModel.GuestViewModel

class AllGuestFragment : Fragment() {

    private lateinit var guestViewModel: GuestViewModel
    private lateinit var mListener: ListenerGuest
    private val mAdapter = AdapterGuest()
    private var _binding: FragmentAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        guestViewModel =
            ViewModelProvider(this).get(GuestViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root
        observe()

        //nossa interface e uma classe anônima então nao pode ser chamada direto
        mListener = object : ListenerGuest {
            override fun onCLick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                //para passar parâmetros em para outras activy usando navegacao usamos o putString,se desejamos passar string,
                //putInt para passar inteiros
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
        mAdapter.listenerGuest(mListener)


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

    //fragment seque mesmo ciclo de vida que uma activity então  o método onCreateView e chamado so no momento criar a tela.
    //se deseja que meus dados carreguem a cada foco na activity sem destruir, preciso chamar o onResume
    //dentro do escopo onResume a cada foco na activity sera carregado meus dados do banco
    override fun onResume() {
        guestViewModel.load(GuestConstants.FILTER_ID.ALL)
        super.onResume()
    }

    private fun observe() {
        //por estarmos em um fragment nao possui uma activity, então nao existe o this.
        //viewLifecycleOwner sera responsável por isso
        guestViewModel.guestModel.observe(viewLifecycleOwner, {
            mAdapter.updatesGuest(it)
        })
        // quem faz a união entre o fragment  e o banco,e adapter entao sera chamado um método no adapter para mandar nossos intens dalis
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}