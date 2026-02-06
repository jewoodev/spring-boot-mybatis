package data.spring.mybatis.application.provided.product

import data.spring.mybatis.IntegrationTestSupport
import data.spring.mybatis.application.exception.NoDataFoundException
import data.spring.mybatis.application.service.product.command.ProductSearchCommand
import data.spring.mybatis.domain.product.Product
import data.spring.mybatis.domain.product.ProductName
import data.spring.mybatis.domain.product.Price
import data.spring.mybatis.domain.product.Quantity
import data.spring.mybatis.application.service.product.command.ProductUpdateCommand
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class ProductUseCaseTest: IntegrationTestSupport() {
    @AfterEach
    fun tearDown() {
        super.productUseCase.deleteAll()
    }

    @Test
    fun saveProductsAndFindAllSuccessfully() {
        val sut = super.productUseCase
        val products = listOf(
            Product.create(productName = ProductName("상품1"), price = Price(20000), quantity = Quantity(10)),
            Product.create(productName = ProductName("상품2"), price = Price(30000), quantity = Quantity(20)),
            Product.create(productName = ProductName("상품3"), price = Price(40000), quantity = Quantity(30))
        )
        products.forEach { sut.save(it) }

        val saved = sut.findWithCond(ProductSearchCommand(null, null))

        assertThat(saved).extracting("productName").containsExactly("상품1", "상품2", "상품3")
        assertThat(saved).extracting("price").containsExactly(20000, 30000, 40000)
        assertThat(saved).extracting("quantity").containsExactly(10, 20, 30)
        for (i in 0..1) {
            assertThat(saved[i].createdAt).isBefore(saved[i + 1].createdAt)
            assertThat(saved[i].updatedAt).isBefore(saved[i + 1].updatedAt)
        }
    }

    @Test
    fun findByIdSuccessfully() {
        val sut = super.productUseCase
        val product = Product.create(productName = ProductName("리얼 마이바티스"), price = Price(30000), quantity = Quantity(100))
        sut.save(product)

        val saved = sut.findById(1L)

        assertThat(saved!!.productName).isEqualTo(product.productName)
        assertThat(saved.price).isEqualTo(product.price)
        assertThat(saved.quantity).isEqualTo(product.quantity)
    }

    @Test
    fun findWithCondSuccessfully() {
        val sut = super.productUseCase
        val products = listOf(
            Product.create(productName = ProductName("리얼 마이바티스"), price = Price(30000), quantity = Quantity(100)),
            Product.create(productName = ProductName("리얼 제이디비씨"), price = Price(30000), quantity = Quantity(100)),
            Product.create(productName = ProductName("리얼 비쌈"), price = Price(50000), quantity = Quantity(100))
        )
        products.forEach { sut.save(it) }

        val saved = sut.findWithCond(ProductSearchCommand(productName = null, maxPrice = 30000))
        assertThat(saved).hasSize(2)
        assertThat(saved).extracting("productName").contains("리얼 마이바티스", "리얼 제이디비씨")
    }

    @Test
    fun updateProductsSuccessfully() {
        // given
        val sut = super.productUseCase
        val products = listOf(
            Product.create(productName = ProductName("상품1"), price = Price(20000), quantity = Quantity(10)),
            Product.create(productName = ProductName("상품2"), price = Price(30000), quantity = Quantity(20)),
            Product.create(productName = ProductName("상품3"), price = Price(40000), quantity = Quantity(30))
        )
        products.forEach { sut.save(it) }
        val updateCommands = listOf(
            ProductUpdateCommand(1L, "상품4", null, null),
            ProductUpdateCommand(2L, "상품5", null, null),
            ProductUpdateCommand(3L, "상품6", null, null)
        )

        // when
        updateCommands.forEach { sut.update(it) }

        // then
        val saved = sut.findWithCond(ProductSearchCommand(productName = null, maxPrice = null))
        assertThat(saved).extracting("productName").containsExactly("상품4", "상품5", "상품6")
    }

    @Test
    fun findWhenNoProduct() {
        val sut = super.productUseCase

        assertThatThrownBy { sut.findById(1L) }
                .isInstanceOf(NoDataFoundException::class.java);
    }

    @Test
    fun findAllWhenNoProduct() {
        val sut = super.productUseCase

        assertThatThrownBy { sut.findWithCond(ProductSearchCommand(productName = null, maxPrice = null)) }
            .isInstanceOf(NoDataFoundException::class.java)
    }
}