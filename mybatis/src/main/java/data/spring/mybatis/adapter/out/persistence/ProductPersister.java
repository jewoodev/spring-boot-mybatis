package data.spring.mybatis.adapter.out.persistence;

import data.spring.mybatis.application.required.ProductRepository;
import data.spring.mybatis.application.service.command.ProductSearchCommand;
import data.spring.mybatis.application.service.command.ProductUpdateCommand;
import data.spring.mybatis.domain.Product;

import java.util.List;
import java.util.Optional;

public class ProductPersister implements ProductRepository {
    private final ProductMapper productMapper;

    public ProductPersister(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public void save(Product entity) {
        this.productMapper.save(ProductEntity.fromDomain(entity));
    }

    @Override
    public int saveAll(List<Product> entities) {
        return this.productMapper.saveAll(entities.stream().map(ProductEntity::fromDomain).toList());
    }

    @Override
    public void update(ProductUpdateCommand updateCommand) {
        this.productMapper.update(updateCommand);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return this.productMapper.findById(productId).map(ProductEntity::toDomain);
    }

    @Override
    public List<Product> findAll(ProductSearchCommand searchCond) {
        return this.productMapper.findAll(searchCond).stream().map(ProductEntity::toDomain).toList();
    }

    @Override
    public int deleteAll() {
        return this.productMapper.deleteAll();
    }
}
