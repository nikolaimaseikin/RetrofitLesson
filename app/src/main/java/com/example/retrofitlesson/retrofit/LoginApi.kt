package com.example.retrofitlesson.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LoginApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): User

    @GET("products")
    suspend fun getAllProducts(): Products

    @Headers(
        "ContentType: application/json"
    )
    @GET("products/search")
    suspend fun getProductsByName(@Header("Authorization") token: String, @Query("q") name: String): Products
}