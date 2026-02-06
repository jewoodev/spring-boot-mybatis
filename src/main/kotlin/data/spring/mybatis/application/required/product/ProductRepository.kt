package data.spring.mybatis.application.required.product

import data.spring.mybatis.application.service.product.command.ProductSearchCommand
import data.spring.mybatis.domain.product.Product

interface ProductRepository {
    fun save(product: Product): Int

    fun truncate(): Int

    fun findById(productId: Long): Product?
    fun findWithCond(searchCommand: ProductSearchCommand): List<Product>
}