package data.spring.mybatis.application.service.product

import data.spring.mybatis.application.exception.HackingDoubtException
import data.spring.mybatis.application.exception.NoDataFoundException
import data.spring.mybatis.application.provided.product.ProductUseCase
import data.spring.mybatis.application.provided.product.dto.ProductCreateCommand
import data.spring.mybatis.application.provided.product.dto.ProductDeleteCommand
import data.spring.mybatis.application.provided.product.dto.ProductSearchCond
import data.spring.mybatis.application.provided.product.dto.ProductUpdateCommand
import data.spring.mybatis.application.required.product.ProductRepository
import data.spring.mybatis.domain.product.Product
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

open class ProductService(
    val productRepository: ProductRepository
) : ProductUseCase {
    override fun save(createCommand: ProductCreateCommand): Int {
        return productRepository.save(Product.create(
            productName = createCommand.productName,
            price = createCommand.price,
            quantity = createCommand.quantity
        ))
    }

    @Transactional
    override fun saveAll(createCommands: List<ProductCreateCommand>): Int {
        return createCommands.sumOf { productRepository.save(
            Product.create(
                productName = it.productName,
                price = it.price,
                quantity = it.quantity
            )
        ) }
    }

    @Transactional
    override fun update(updateCommand: ProductUpdateCommand): Int {
        val product = productRepository.findById(updateCommand.productId) ?: throw HackingDoubtException("존재하지 않는 상품을 수정할 수 없습니다: ${updateCommand.productId}.")

        return productRepository.save(product.updateInfo(
            newName = updateCommand.productName,
            newPrice = updateCommand.price
        ))
    }

    @Transactional
    override fun updateAll(updateCommands: List<ProductUpdateCommand>): Int {
        return updateCommands.sumOf { update(it) }
    }

    @Transactional
    override fun deleteAll(deleteCommands: List<ProductDeleteCommand>): Int {
        return deleteCommands.sumOf {
            val product = productRepository.findById(it.productId) ?: throw HackingDoubtException("존재하지 않는 상품을 삭제할 수 없습니다: ${it.productId}.")
            productRepository.save(product.delete())
        }
    }

    override fun truncate(): Int {
        return productRepository.truncate()
    }

    override fun findById(productId: Long): Product {
        return productRepository.findById(productId) ?: throw NoDataFoundException("해당 식별자를 갖는 상품이 없습니다: ${productId}.")
    }

    override fun findByCond(
        searchCond: ProductSearchCond?,
        createdAt: LocalDateTime?,
        productId: Long?,
        size: Int
    ): List<Product> {
        return productRepository.findByCond(searchCond, createdAt, productId, size)
            .ifEmpty { throw NoDataFoundException("해당 검색 조건을 만족하는 상품이 없습니다: ${searchCond}.") }
    }

    override fun findAll(): List<Product> {
        return productRepository.findAll()
            .ifEmpty { throw NoDataFoundException("저장된 상품이 없습니다.") }
    }
}