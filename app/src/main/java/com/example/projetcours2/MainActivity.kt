package com.example.projetcours2

import AddContactDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private val contactList: MutableList<Contact> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //générer une liste de contact
        var text: String = ""

        for (i in 1..10) {
            val contact = Contact("contac_${i}", "00000${i}")
            contactList.add(contact)
            text += "${contact.name} : ${contact.phoneNumber} \n"
        }
        //afficher la liste dans la zone de texte
        textView = findViewById(R.id.tv_list_contact)
        textView.text = text

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun addContact(view: View) {
        val listner = object : AddContactDialog.ContactDialogListener {
            override fun onContactAdded(name: String, phone: String) {

                contactList.add(Contact(name,phone))
                var text: String = textView.text.toString()
                text += "$name : $phone \n"
                textView.text = text
            }
        }
        val diag = AddContactDialog(listner)
        diag.show(supportFragmentManager, "ContactAdd")
    }

    fun showListDemoActivity(view: View) {}
}