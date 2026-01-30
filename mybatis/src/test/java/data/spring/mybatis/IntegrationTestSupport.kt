package data.spring.mybatis

import data.spring.mybatis.application.provided.product.ProductUseCase
import data.spring.mybatis.application.required.product.ProductRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [IntegrationTestConfig::class])
abstract class IntegrationTestSupport {
    @Autowired
    protected lateinit var productRepository: ProductRepository

    @Autowired
    protected lateinit var productUseCase: ProductUseCase
}