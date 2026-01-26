package data.spring.mybatis.application.required;

import data.spring.mybatis.application.service.command.ProductSearchCommand;
import data.spring.mybatis.application.service.command.ProductUpdateCommand;
import data.spring.mybatis.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void save(Product productEntity);

    int saveAll(List<Product> productEntities);

    void update(ProductUpdateCommand updateCommand);

    Optional<Product> findById(Long productId);

    List<Product> findAll(ProductSearchCommand searchCommand);

    int deleteAll();
}
