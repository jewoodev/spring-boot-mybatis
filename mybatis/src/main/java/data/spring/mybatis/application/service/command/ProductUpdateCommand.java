package data.spring.mybatis.application.service.command;

public record ProductUpdateCommand(
        Long productId,
        String productName,
        Integer price,
        Integer quantity
){
    public static ProductUpdateCommand of(Long productId, String productName, Integer price, Integer quantity) {
        return new ProductUpdateCommand(productId, productName, price, quantity);
    }
}
