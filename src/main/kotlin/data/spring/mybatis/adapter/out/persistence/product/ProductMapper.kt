package data.spring.mybatis.adapter.out.persistence.product

import data.spring.mybatis.application.service.product.command.ProductSearchCommand
import data.spring.mybatis.domain.product.Product
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductMapper {
    fun save(product: Product): Int

    fun update(product: Product): Int

    fun truncate(): Int

    fun findById(productId: Long): Product?
    fun findWithCond(searchCommand: ProductSearchCommand): List<Product>
}