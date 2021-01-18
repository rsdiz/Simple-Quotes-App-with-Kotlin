package id.rsdiz.kumpulanquotes.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import id.rsdiz.kumpulanquotes.R
import id.rsdiz.kumpulanquotes.databinding.ActivityListQuotesBinding
import id.rsdiz.kumpulanquotes.utils.Const
import id.rsdiz.kumpulanquotes.utils.viewBinding

class ListQuotesActivity : AppCompatActivity(R.layout.activity_list_quotes) {
    private val _binding: ActivityListQuotesBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)

        if (intent.extras != null) {
            val bundle = intent.extras!!
            _binding.authorName.text = bundle.getString(Const.AUTHOR)
            _binding.totalQuotes.text = "Total Quotes: ${bundle.getInt(Const.TOTAL)}"
            if (bundle[Const.LIST_QUOTES] is List<*>) {
                val listQuotes = (bundle[Const.LIST_QUOTES] as List<*>).filterIsInstance<String>()
                _binding.listQuotes.adapter =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, listQuotes)
            }
        }
    }
}
