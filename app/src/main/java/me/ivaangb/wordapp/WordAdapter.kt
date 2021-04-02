package me.ivaangb.wordapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(private val letterId: String, context: Context):
    RecyclerView.Adapter<WordAdapter.WordViewHolder>()  {

    private val filteredWords: List<String>

    init {
        //Llama las palabras en el banco de palabras (archivo Array en values)
        val words = context.resources.getStringArray(R.array.words).toList()

        filteredWords = words
            .filter { it.startsWith(letterId, ignoreCase = true) }
            .shuffled()
            .take(5)
            .sorted()
    }

    class WordViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val button: Button =view.findViewById(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return WordViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filteredWords[position]

        // Llamada a startActivity
        val context = holder.view.context

        //Establece el texto en el botón
        holder.button.text = item

        holder.button.setOnClickListener{

            val queryUrl: Uri = Uri.parse("${WordListFragment.SEARCH_PREFIX}${item}")

            //Declaro Intent que va a lanzarse al presionar una palabra.
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = filteredWords.size
}