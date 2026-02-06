package data.spring.mybatis.application.provided.product

import data.spring.mybatis.application.service.product.command.ProductSearchCommand
import data.spring.mybatis.domain.product.Product
import data.spring.mybatis.application.service.product.command.ProductUpdateCommand

interface ProductUseCase {
    fun save(product: Product): Int

    fun update(updateCommand: ProductUpdateCommand): Int

    fun deleteAll(): Int

    fun findById(productId: Long): Product?
    fun findWithCond(searchCommand: ProductSearchCommand): List<Product>
}