package com.example.guestlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.guestlist.R
import com.example.guestlist.service.constants.GuestConstants
import com.example.guestlist.viewModel.GuestFormViewModel


class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mGuestViewModel: GuestFormViewModel
    private var mGuestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        mGuestViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        val presenceId = findViewById<RadioButton>(R.id.presentsButton)
        presenceId.isChecked = true

        //load normalmente gasta maior tempo para ser processado
        listeners()
        observes()
        load()

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSalve) {
            val nameId: TextView = findViewById(R.id.editName)
            val name = nameId.text.toString()
            val presenceId: RadioButton = findViewById<RadioButton>(R.id.presentsButton)
            val presence = presenceId.isChecked
            //no modelo MVVM quem cuida da regra de logica e a viewModel
            //observa que mGuestId no topo  da aplicacao inicia 0,
            // depois que a tela ja esta criada,ela nao vai mais sofrer alteracao,valor vai ser o que esta no load
            //caso o bundle nao seja nullo
            mGuestViewModel.salve(mGuestId, name, presence)

        }
    }

    private fun load() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mGuestViewModel.load(mGuestId)
        }
    }


    private fun observes() {
        mGuestViewModel.presentModel.observe(this, {
            if (it) {
                Toast.makeText(applicationContext, getString(R.string.sucess), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
            //fecha activy
            finish()
        })

        mGuestViewModel.guestUser.observe(this, {
            val name = findViewById<EditText>(R.id.editName)
            name.setText(it.name)
            val presentsButton = findViewById<RadioButton>(R.id.presentsButton)
            val absentsButton = findViewById<RadioButton>(R.id.absentsButton)
            if (it.presence) {
                presentsButton.isChecked = true
            } else {
                absentsButton.isChecked = true
            }
        })


    }

    private fun listeners() {
        val buttonSalve: Button = findViewById(R.id.buttonSalve)
        buttonSalve.setOnClickListener(this)
    }


}