package data.spring.mybatis.application.provided.product

import data.spring.mybatis.application.service.product.command.ProductSearchCommand
import data.spring.mybatis.application.service.product.command.ProductUpdateCommand
import data.spring.mybatis.domain.product.Product
import java.util.*

interface ProductUseCase : ProductUseCaseEx {
    fun findById(productId: Long): Product?

    fun findWithCond(searchCommand: ProductSearchCommand): List<Product>

    fun save(product: Product)

    fun saveAll(products: List<Product>): Int
}