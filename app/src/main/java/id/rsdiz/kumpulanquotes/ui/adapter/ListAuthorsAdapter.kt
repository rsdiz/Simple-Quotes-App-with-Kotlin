package id.rsdiz.kumpulanquotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rsdiz.kumpulanquotes.databinding.ItemListAuthorsBinding
import id.rsdiz.kumpulanquotes.models.Authors

class ListAuthorsAdapter(
    private val list: ArrayList<Authors>,
    private val clickListener: (Authors) -> Unit
) : RecyclerView.Adapter<ListAuthorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListAuthorsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(
        private val binding: ItemListAuthorsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(authors: Authors, clickListener: (Authors) -> Unit) {
            binding.authorName.text = "Nama Author: \n ${authors.name}"
            binding.totalQuotes.text = "Jumlah Quotes: ${authors.total}"
            binding.baseLayout.setOnClickListener {
                clickListener(authors)
            }
        }
    }
}
