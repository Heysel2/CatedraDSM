import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myplanner.DBHelper
import com.example.myplanner.R
import java.util.*

class Agregar : Fragment() {

    private lateinit var dbHelper: DBHelper
    private lateinit var dateEditText: EditText

    // Método que se llama al crear la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_agregar, container, false)

        dbHelper = DBHelper(requireContext())

        // Inicializa los elementos de la interfaz
        val titleEditText = view.findViewById<EditText>(R.id.txtEmail)
        val descriptionEditText = view.findViewById<EditText>(R.id.editTextTextMultiLine2)
        val timeEditText = view.findViewById<EditText>(R.id.editTextTime)
        dateEditText = view.findViewById(R.id.editTextDate)
        val addButton = view.findViewById<Button>(R.id.btnregistrarse)

        // Muestra el selector de fecha al hacer clic en el campo de fecha
        dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        // Agrega la actividad al hacer clic en el botón
        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val time = timeEditText.text.toString()
            val date = dateEditText.text.toString()

            // Verifica que todos los campos estén completos
            if (title.isNotEmpty() && description.isNotEmpty() && time.isNotEmpty() && date.isNotEmpty()) {
                dbHelper.addActivity(title, description, date, time)
                Toast.makeText(requireContext(), "Actividad agregada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    // Para el selector de fecha
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dateEditText.setText(formattedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}
