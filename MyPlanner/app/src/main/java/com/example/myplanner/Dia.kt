package com.example.myplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Dia : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento y devuelve la vista
        val view = inflater.inflate(R.layout.fragment_dia, container, false)

        // Obtiene una referencia al TextView definido en el layout usando su ID
        val textView: TextView = view.findViewById(R.id.text_view_fecha)

        // Crea un objeto SimpleDateFormat para dar formato a la fecha actual
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        // Establece el texto del TextView con la fecha actual formateada
        textView.text = currentDate

        // Devuelve la vista inflada, que ahora incluye la fecha actual en el TextView
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Dia().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
