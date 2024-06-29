package com.example.whatsappdireto.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.whatsappdireto.model.Countries

class SpinnerCountriesAdapter(context: Context, resource: Int, objects: List<Countries>) :
    ArrayAdapter<Countries>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val objeto = getItem(position)
        if (objeto != null) {
            textView.text = objeto.toString() // Exibe ambos os índices no título
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val objeto = getItem(position)
        if (objeto != null) {
            textView.text = objeto.toString() // Exibe ambos os índices no dropdown
        }
        return view
    }
}