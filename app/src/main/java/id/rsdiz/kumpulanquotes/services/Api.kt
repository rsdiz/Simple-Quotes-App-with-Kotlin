package id.rsdiz.kumpulanquotes.services

import id.rsdiz.kumpulanquotes.models.Quotes
import id.rsdiz.kumpulanquotes.utils.Const
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET(Const.GET_API_DATA)
    fun getQuotes(): Call<ArrayList<Quotes>>
}
