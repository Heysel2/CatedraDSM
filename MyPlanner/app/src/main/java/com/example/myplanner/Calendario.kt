package com.example.myplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import java.util.*

class Calendario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obteiene una referencia al CalendarView definido en el layout
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)

        // Crea una instancia de Calendar para obtener la fecha actual
        val calendar = Calendar.getInstance()

        // Obteniene la fecha actual en milisegundos
        val currentDate = calendar.timeInMillis

        // Establecer la fecha actual en el CalendarView
        calendarView.date = currentDate
    }
}
