package data.spring.mybatis.application.provided.product.dto

data class ProductCreateCommand(
    val productName: String,
    val price: Int,
    val quantity: Int
)
