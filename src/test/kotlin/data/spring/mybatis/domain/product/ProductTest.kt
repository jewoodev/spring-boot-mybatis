package data.spring.mybatis.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class ProductTest {
    @Test
    fun `product is created successfully`() {
        val product = Product.create("Test Product", 1000, 100)
        assertThat(product)
            .extracting("productName", "price", "quantity")
            .containsExactly("Test Product", 1000, 100)
    }
    
    @Test
    fun `product quantity is increased successfully`() {
        // given
        val original = Product.create("Test Product", 1000, 100)
        
        // when
        val increased = original.increaseQuantity(50)
        
        // then
        assertThat(increased).extracting("quantity").isEqualTo(150)
        assertThat(increased.updatedAt).isAfterOrEqualTo(original.updatedAt)
    }

    @Test
    fun `product quantity is decreased successfully`() {
        // given
        val product = Product.create("Test Product", 1000, 100)
        
        // when
        val decreasedProduct = product.decreaseQuantity(50)
        
        // then
        assertThat(decreasedProduct).extracting("quantity").isEqualTo(50)
        assertThat(decreasedProduct.updatedAt).isAfterOrEqualTo(product.updatedAt)
    }

    @Test
    fun `product info is updated successfully`() {
        // given
        val product = Product.create("Test Product", 1000, 100)
        val newName = "Updated Product"
        val newPrice = 2000
        
        // when
        val updatedProduct = product.updateInfo(newName, newPrice)
        
        // then
        assertThat(updatedProduct).extracting("productName").isEqualTo(newName)
        assertThat(updatedProduct).extracting("price").isEqualTo(newPrice)
        assertThat(updatedProduct.updatedAt).isAfterOrEqualTo(product.updatedAt)
    }

    @Test
    fun `product name can be updated alone`() {
        // given
        val product = Product.create("Test Product", 1000, 100)
        val newName = "Updated Product"
        
        // when
        val updatedProduct = product.updateInfo(newName = newName)
        
        // then
        assertThat(updatedProduct).extracting("productName").isEqualTo(newName)
        assertThat(updatedProduct.price).isEqualTo(product.price)
    }

    @Test
    fun `product price can be updated alone`() {
        // given
        val original = Product.create("Test Product", 1000, 100)
        val newPrice = 2000
        
        // when
        val updated = original.updateInfo(newPrice = newPrice)
        
        // then
        assertThat(updated.productName).isEqualTo(original.productName)
        assertThat(updated).extracting("price").isEqualTo(newPrice)
    }

    @Test
    fun `product info update fails when no info is provided`() {
        // given
        val product = Product.create("Test Product", 1000, 100)
        
        // when & then
        assertThatThrownBy { product.updateInfo(null, null) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상품 수정의 필수 조건이 만족되지 않았습니다.")
    }

    @Test
    fun `product is deleted successfully`() {
        // given
        val product = Product.create("Test Product", 1000, 100)

        // when
        val deletedProduct = product.delete()

        // then
        assertThat(deletedProduct.productName.value).startsWith("Test Product$")
        assertThat(deletedProduct.updatedAt).isAfterOrEqualTo(product.updatedAt)
    }
}