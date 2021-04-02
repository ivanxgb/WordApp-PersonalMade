package me.ivaangb.wordapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import me.ivaangb.wordapp.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {
    private var _binding: FragmentWordListBinding? = null
    private lateinit var letterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = _binding?.recyclerviewWordList
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        recyclerView?.adapter = WordAdapter(letterId, requireContext())

        recyclerView?.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

}