package com.example.droidintro.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.droidintro.R
import com.example.droidintro.wordcountusecase.wordcounter.Word

class WordsRecyclerViewAdapter(private val items:List<WordViewModel>) : RecyclerView.Adapter<WordsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WordsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return WordsItemViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: WordsItemViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

}

class WordsItemViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    val wordView:TextView = view.findViewById(R.id.word)
    val countView:TextView = view.findViewById(R.id.count)

    fun bind(word:WordViewModel) {
        wordView.text = word.value
        countView.text = word.count.toString()
    }
}