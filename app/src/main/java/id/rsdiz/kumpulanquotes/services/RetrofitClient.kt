package id.rsdiz.kumpulanquotes.services

import id.rsdiz.kumpulanquotes.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    val instance: Api? by lazy {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        retrofit?.create(Api::class.java)
    }
}
