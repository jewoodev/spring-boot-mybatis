package data.spring.mybatis.adapter.in.dto;

import data.spring.mybatis.application.service.command.ProductUpdateCommand;
import jakarta.validation.Valid;

import java.util.List;

public class ProductUpdateBatchRequest {
    List<@Valid ProductUpdateRequest> updateRequests;

    public ProductUpdateBatchRequest(List<ProductUpdateRequest> updateRequests) {
        this.updateRequests = updateRequests;
    }

    public List<ProductUpdateRequest> getUpdateRequests() {
        return this.updateRequests;
    }

    public List<ProductUpdateCommand> toCommands() {
        return this.updateRequests.stream()
                .map(request ->
                        ProductUpdateCommand.of(
                                request.productId(),
                                request.productName(),
                                request.price(),
                                request.quantity()
                ))
                .toList();
    }
}
