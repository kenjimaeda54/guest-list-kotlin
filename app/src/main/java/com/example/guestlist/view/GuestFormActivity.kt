package com.example.guestlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.guestlist.R
import com.example.guestlist.service.constants.GuestConstants
import com.example.guestlist.viewModel.GuestFormViewModel


class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mGuestViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        mGuestViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        load()
        listeners()
        observes()

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSalve) {
            val nameId: TextView = findViewById(R.id.editName)
            val name = nameId.text.toString();
            val presenceId: RadioButton = findViewById(R.id.presentsButton)
            val presence = presenceId.isChecked;
            mGuestViewModel.salve(name, presence)

        }
    }

    private fun load() {
        //nao preciso instance intent ja esta sendo feita pelo AppCompatActivity
        val bundle = intent.extras
        //preciso tratar possibilidade do bundle vim nullo
        if (bundle != null) {
            mGuestViewModel.load(bundle.getInt(GuestConstants.GUESTID))
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
            val presenceButton = findViewById<RadioButton>(R.id.presentsButton)
            val absenteesButton = findViewById<RadioButton>(R.id.absentsButton)
            name.setText(it.name)
            if (it.presence) {
                presenceButton.isChecked = true
            } else {
                absenteesButton.isChecked = true
            }
        })

    }

    private fun listeners() {
        val buttonSalve: Button = findViewById(R.id.buttonSalve)
        buttonSalve.setOnClickListener(this)
    }


}