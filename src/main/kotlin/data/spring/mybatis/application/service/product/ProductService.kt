package data.spring.mybatis.application.service.product

import data.spring.mybatis.application.exception.NoDataFoundException
import data.spring.mybatis.application.provided.product.ProductUseCase
import data.spring.mybatis.application.required.product.ProductRepository
import data.spring.mybatis.application.service.product.command.ProductSearchCommand
import data.spring.mybatis.domain.product.Product
import data.spring.mybatis.application.service.product.command.ProductUpdateCommand
import data.spring.mybatis.domain.product.Price
import data.spring.mybatis.domain.product.ProductName
import org.springframework.transaction.annotation.Transactional

open class ProductService(
    val productRepository: ProductRepository
) : ProductUseCase {
    override fun save(product: Product): Int {
        return productRepository.save(product)
    }

    @Transactional
    override fun update(updateCommand: ProductUpdateCommand): Int {
        return updateCommand.let {
                findById(it.productId).updateInfo(
                    it.productName?.let { value -> ProductName(value) },
                    it.price?.let { amount -> Price(amount) }
                )
        }.let { productRepository.save(it) }
    }

    override fun deleteAll(): Int {
        return productRepository.truncate()
    }

    override fun findById(productId: Long): Product {
        return productRepository.findById(productId) ?: throw NoDataFoundException("해당 식별자를 갖는 상품이 없습니다: ${productId}.")
    }

    override fun findWithCond(searchCommand: ProductSearchCommand): List<Product> {
        return productRepository.findWithCond(searchCommand)
            .ifEmpty { throw NoDataFoundException("해당 검색 조건을 만족하는 상품이 없습니다: ${searchCommand}.") }
    }
}