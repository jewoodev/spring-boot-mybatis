package data.spring.mybatis.adapter.`in`.product.request

import jakarta.validation.constraints.Min

data class ProductDeleteRequest(
    @field:Min(value = 1, message = "클라이언트 내부 오류가 있습니다.")
    val productId: Long
)
