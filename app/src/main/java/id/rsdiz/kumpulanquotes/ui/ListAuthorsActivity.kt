package id.rsdiz.kumpulanquotes.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.yarolegovich.discretescrollview.DSVOrientation
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import id.rsdiz.kumpulanquotes.R
import id.rsdiz.kumpulanquotes.databinding.ActivityListAuthorsBinding
import id.rsdiz.kumpulanquotes.models.Authors
import id.rsdiz.kumpulanquotes.models.Quotes
import id.rsdiz.kumpulanquotes.services.RetrofitClient
import id.rsdiz.kumpulanquotes.ui.adapter.ListAuthorsAdapter
import id.rsdiz.kumpulanquotes.utils.Const
import id.rsdiz.kumpulanquotes.utils.viewBinding
import retrofit2.Call
import retrofit2.Response

class ListAuthorsActivity : AppCompatActivity(R.layout.activity_list_authors) {

    private val _binding: ActivityListAuthorsBinding by viewBinding()

    private val listQuotes = ArrayList<Quotes>()
    private val listAuthors = ArrayList<Authors>()

    private lateinit var infiniteScrollAdapter: InfiniteScrollAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)

        getDataFromApi()
    }

    private fun setRecyclerAdapter() {
        val adapter = ListAuthorsAdapter(listAuthors) { selectedItem: Authors ->
            listItemClicked(selectedItem)
        }
        infiniteScrollAdapter = InfiniteScrollAdapter.wrap(adapter)
        _binding.itemListAuthors.let {
            it.setOrientation(DSVOrientation.HORIZONTAL)
            it.adapter = infiniteScrollAdapter
            it.setItemTransitionTimeMillis(150)
            it.setItemTransformer(
                ScaleTransformer.Builder()
                    .setMinScale(0.8F).build()
            )
        }
    }

    private fun listItemClicked(selectedItem: Authors) {
        val intent = Intent(this, ListQuotesActivity::class.java)
        val bundle = bundleOf(
            Const.AUTHOR to selectedItem.name,
            Const.TOTAL to selectedItem.total,
            Const.LIST_QUOTES to selectedItem.quotes
        )
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun getDataFromApi() {
        RetrofitClient.instance?.getQuotes()?.enqueue(object :
                retrofit2.Callback<ArrayList<Quotes>> {
                override fun onFailure(call: Call<ArrayList<Quotes>>, t: Throwable) {
                    Toast.makeText(
                        this@ListAuthorsActivity,
                        "TERJADI MASALAH!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<ArrayList<Quotes>>,
                    response: Response<ArrayList<Quotes>>
                ) {
                    response.body()?.let {
                        listQuotes.addAll(it)
                    }
                    listQuotes.groupBy({ it.author }, { it.text }).forEach {
                        listAuthors.add(Authors(it.key ?: "Anonim", it.value.size, it.value))
                    }

                    setRecyclerAdapter()
                }
            })
    }
}
