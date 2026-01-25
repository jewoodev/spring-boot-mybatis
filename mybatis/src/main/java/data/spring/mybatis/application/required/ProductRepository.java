package data.spring.mybatis.application.required;

import data.spring.mybatis.adapter.in.dto.ProductSearchCond;
import data.spring.mybatis.application.service.command.ProductUpdateCommand;
import data.spring.mybatis.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void save(Product product);

    int saveAll(List<Product> products);

    void update(ProductUpdateCommand updateCommand);

    Optional<Product> findById(Long productId);

    List<Product> findAll(ProductSearchCond searchCond);

    int deleteAll();
}
