package com.example.retrofitlesson.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): User

    @GET("products")
    suspend fun getAllProducts(): Products
}