package com.example.myplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class Inicio : Fragment() {

    private lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        dbHelper = DBHelper(requireContext())  // Inicializa el helper de la base de datos
        val activities = dbHelper.getAllActivities()  // Obtiene todas las actividades

        val linearLayout = view.findViewById<LinearLayout>(R.id.linear_layout_activities)

        // Itera sobre las actividades y las muestra en la interfaz
        for (activity in activities) {
            val activityView = layoutInflater.inflate(R.layout.item_activity, null)

            // Referencias a los elementos de cada actividad
            val titleTextView = activityView.findViewById<TextView>(R.id.titulo)
            val descriptionTextView = activityView.findViewById<TextView>(R.id.descripcion)
            val dateTextView = activityView.findViewById<TextView>(R.id.dia)
            val timeTextView = activityView.findViewById<TextView>(R.id.hora)
            val deleteButton = activityView.findViewById<Button>(R.id.btnEliminar)

            // Asigna los datos de la actividad a los elementos de la interfaz
            titleTextView.text = activity.title
            descriptionTextView.text = activity.description
            dateTextView.text = activity.date
            timeTextView.text = activity.time

            // Configura el botón de eliminar
            deleteButton.setOnClickListener {
                val deletedRows = dbHelper.deleteActivity(activity.id)  // Elimina la actividad de la base de datos
                if (deletedRows > 0) {
                    Toast.makeText(requireContext(), "Actividad eliminada", Toast.LENGTH_SHORT).show()
                    // Refresca la vista de actividades
                    parentFragmentManager.beginTransaction().detach(this).attach(this).commit()
                } else {
                    Toast.makeText(requireContext(), "Error al eliminar la actividad", Toast.LENGTH_SHORT).show()
                }
            }

            // Crear un nuevo LayoutParams para configurar solo el margen inferior
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 20)  // Margen inferior de 16 píxeles

            activityView.layoutParams = layoutParams

            linearLayout.addView(activityView)  // Agrega la vista de la actividad al layout
        }

        return view
    }
}
