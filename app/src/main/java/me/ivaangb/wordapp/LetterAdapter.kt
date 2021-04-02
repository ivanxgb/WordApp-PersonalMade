package me.ivaangb.wordapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter :
    RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    private val letterList = ('A').rangeTo('Z').toList()

    class LetterViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return LetterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = letterList[position]
        holder.button.text = item.toString()
        holder.button.setOnClickListener {

            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(
                holder.button.text.toString())
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = letterList.size

}
