package com.example.retrofitlesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitlesson.adapter.ProductAdapter
import com.example.retrofitlesson.databinding.ActivityMainBinding
import com.example.retrofitlesson.retrofit.AuthRequest
import com.example.retrofitlesson.retrofit.LoginApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ProductAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(LoginApi::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val productObj = mainApi.getAllProducts()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(productObj.products)
                }
            }
        }
    }
}