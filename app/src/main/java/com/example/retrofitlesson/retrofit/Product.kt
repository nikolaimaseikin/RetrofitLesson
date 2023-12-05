package com.example.retrofitlesson.retrofit

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPrecentage: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)
