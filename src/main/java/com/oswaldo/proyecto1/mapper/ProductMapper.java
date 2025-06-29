package com.oswaldo.proyecto1.mapper;

import com.oswaldo.proyecto1.product.dto.InternalProductDTO;
import com.oswaldo.proyecto1.product.dto.ProductRequest;
import com.oswaldo.proyecto1.product.dto.ProductResponse;
import com.oswaldo.proyecto1.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public InternalProductDTO modelToInternalDTO(Product product) {
        InternalProductDTO dto = new InternalProductDTO();

        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setPrice(product.getPrice());

        return dto;
    }

    public Product requestToModel(ProductRequest productRequest) {
        Product model = new Product();

        model.setName(productRequest.getName());
        model.setPrice(productRequest.getPrice());
        model.setStock(productRequest.getStock());

        return model;
    }

    public ProductResponse modelToResponse(Product product) {
        ProductResponse response = new ProductResponse();

        response.setInternalProductDTO(modelToInternalDTO(product));
        response.setStock(product.getStock());

        return response;
    }

    public List<ProductResponse> modelsToResponses(List<Product> products) {
        return products.stream().map(this::modelToResponse).collect(Collectors.toList());
    }
}
