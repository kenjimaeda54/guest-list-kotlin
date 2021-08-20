package com.example.guestlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.guestlist.R
import com.example.guestlist.viewModel.GuestFormViewModel


class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mGuestViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        mGuestViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
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

    }

    private fun listeners() {
        val buttonSalve: Button = findViewById(R.id.buttonSalve)
        buttonSalve.setOnClickListener(this)
    }


}