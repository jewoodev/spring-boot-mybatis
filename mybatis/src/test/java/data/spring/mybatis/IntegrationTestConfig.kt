package data.spring.mybatis

import data.spring.mybatis.adapter.out.persistence.product.ProductEntityMapper
import data.spring.mybatis.adapter.out.persistence.product.ProductPersister
import data.spring.mybatis.application.provided.product.ProductUseCase
import data.spring.mybatis.application.required.product.ProductRepository
import data.spring.mybatis.application.service.product.ProductService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(RepositoryTestConfig::class)
class IntegrationTestConfig {
    @Bean
    fun productRepository(productEntityMapper: ProductEntityMapper): ProductRepository {
        return ProductPersister(productEntityMapper)
    }

    @Bean
    fun productUseCase(productRepository: ProductRepository): ProductUseCase {
        return ProductService(productRepository)
    }
}