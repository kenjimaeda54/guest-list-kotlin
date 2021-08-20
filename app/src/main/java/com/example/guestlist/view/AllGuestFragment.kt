package com.example.guestlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.livedata.core.ktx.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guestlist.databinding.FragmentAllBinding
import com.example.guestlist.view.adapter.AdapterGuest
import com.example.guestlist.viewModel.AllGuestViewModel

class AllGuestFragment : Fragment() {

    private lateinit var allGuestViewModel: AllGuestViewModel
    private var _binding: FragmentAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestViewModel =
            ViewModelProvider(this).get(AllGuestViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //para lidar com listagens nos criamos recycleView
        //ele precisa 3 passo
        //Obter Recycler
        //binding foi criado pelo próprio android studio no momento de criar navegacao em drawer,
        //bining.Id da view.
        // Metodo findByid  nao referencia aui
        val recycle: RecyclerView = binding.allGuest

        //Definir o layout
        //e um layout linear por padrão horizontal,
        recycle.layoutManager = LinearLayoutManager(context)

        //Definir o adpater
        recycle.adapter = AdapterGuest()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}