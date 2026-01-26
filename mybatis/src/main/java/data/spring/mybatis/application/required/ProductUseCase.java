package data.spring.mybatis.application.required;

import data.spring.mybatis.application.service.command.ProductSearchCommand;
import data.spring.mybatis.application.service.command.ProductUpdateCommand;
import data.spring.mybatis.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductUseCase {
    List<Product> findAll(ProductSearchCommand searchCommand);
    void save(Product product);
    int saveAll(List<Product> products);
    Optional<Product> findById(Long productId);
    int updateAll(List<ProductUpdateCommand> updateBatchRequest);
}
