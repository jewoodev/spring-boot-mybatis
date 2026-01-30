package data.spring.mybatis

import data.spring.mybatis.adapter.out.persistence.product.ProductEntityMapper
import data.spring.mybatis.adapter.out.persistence.product.ProductPersister
import data.spring.mybatis.application.required.product.ProductRepository
import org.apache.ibatis.logging.stdout.StdOutImpl
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource

@Configuration
@MapperScan("data.spring.mybatis.adapter.out.persistence")
class RepositoryTestConfig {
    @Bean
    fun datasource(): DataSource {
        return EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:schema.sql")
            .build()
    }

    @Bean
    fun sqlSessionFactoryBean(dataSource: DataSource): SqlSessionFactory {
        val factoryBean = SqlSessionFactoryBean()
        factoryBean.setDataSource(dataSource)

        factoryBean.setMapperLocations(
            *PathMatchingResourcePatternResolver()
                .getResources("classpath*:data/spring/mybatis/adapter/out/persistence/*.xml")
        )

        factoryBean.setTypeAliasesPackage("data.spring.mybatis.adapter.out.persistence")

        val conf = org.apache.ibatis.session.Configuration()
        conf.isMapUnderscoreToCamelCase = true
        conf.setLogImpl(StdOutImpl::class.java)

        factoryBean.setConfiguration(conf)

        return factoryBean.getObject()!!
    }

    @Bean
    fun transactionManager(dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean
    fun productRepository(productEntityMapper: ProductEntityMapper): ProductRepository {
        return ProductPersister(productEntityMapper)
    }
}