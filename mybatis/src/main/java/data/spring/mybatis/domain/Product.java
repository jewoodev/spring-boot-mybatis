package data.spring.mybatis.domain;


public record Product(
        Long productId,
        String productName,
        int price,
        int quantity
) {
    public static Product of(String productName, int price, int quantity) {
        return new Product(null, productName, price, quantity);
    }

    public static Product of(Long productId, String productName, int price, int quantity) {
        return new Product(productId, productName, price, quantity);
    }
}
