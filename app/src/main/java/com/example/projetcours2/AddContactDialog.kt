import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.projetcours2.R



class AddContactDialog(private val listener: ContactDialogListener) : DialogFragment() {
    // Interface pour renvoyer les données à l’activité
    interface ContactDialogListener {
        fun onContactAdded(name: String, phone: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_contact, null)
        builder.setView(dialogView)
        builder.setTitle("Ajouter Contact")
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextPhone = dialogView.findViewById<EditText>(R.id.editTextPhone)
        val buttonOk = dialogView.findViewById<Button>(R.id.confirm_button)

        buttonOk.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                listener.onContactAdded(name, phone)  // Envoie les données
            }
            this.dismiss()
        }
        /*
        // utiliser PositiveButton par defaut de la dialogue

        builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                val name = editTextName.text.toString()
                val phone = editTextPhone.text.toString()
               if (name.isNotEmpty() && phone.isNotEmpty()) {
                   listener.onContactAdded(name, phone)  // Envoie les données
               }
            }
        })
        */

        return builder.create()
    }
}
