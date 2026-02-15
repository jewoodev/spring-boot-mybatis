package data.spring.mybatis.adapter.`in`.product.request

import data.spring.mybatis.application.provided.product.dto.ProductDeleteCommand
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty

data class ProductDeleteBatchRequest(
    @field:Valid
    @field:NotEmpty
    val deleteRequests: List<ProductDeleteRequest>
) {
    fun toCommands(): List<ProductDeleteCommand> {
        return deleteRequests.map { ProductDeleteCommand(it.productId) }
    }
}
