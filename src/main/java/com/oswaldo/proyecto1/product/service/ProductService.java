package com.oswaldo.proyecto1.product.service;

import com.oswaldo.proyecto1.exception.ProductNameAlreadyUsedException;
import com.oswaldo.proyecto1.exception.ProductNotFoundException;
import com.oswaldo.proyecto1.mapper.ProductMapper;
import com.oswaldo.proyecto1.product.dto.ProductRequest;
import com.oswaldo.proyecto1.product.dto.ProductResponse;
import com.oswaldo.proyecto1.product.model.Product;
import com.oswaldo.proyecto1.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse findById(Integer id) {
        return productMapper.modelToResponse(getModel(id));
    }

    public List<ProductResponse> findAll() {
        return productMapper.modelsToResponses(productRepository.findAll());
    }

    public List<ProductResponse> findByName(String name) {
        return productMapper.modelsToResponses(productRepository.findByNameContainingIgnoreCase(name));
    }

    public List<ProductResponse> findByPrice(double minPrice, double maxPrice) {
        return productMapper.modelsToResponses(productRepository.findByPriceBetween(minPrice, maxPrice));
    }

    @Transactional
    public ProductResponse save(ProductRequest productRequest) {
        return productMapper.modelToResponse(productRepository.save(productMapper.requestToModel(productRequest)));
    }

    @Transactional
    public ProductResponse update(ProductRequest productRequest, Integer id) {
        Product model = getModel(id);

        if (productRepository.existsByNameAndIdIsNot(productRequest.getName(), id)) {
            throw new ProductNameAlreadyUsedException("Name: " + productRequest.getName() + " is already used");
        }

        model.setName(productRequest.getName());
        model.setPrice(productRequest.getPrice());
        model.setStock(productRequest.getStock());

        return productMapper.modelToResponse(productRepository.save(model));
    }

    @Transactional
    public ProductResponse partialUpdate(ProductRequest productRequest, Integer id) {
        Product model = getModel(id);

        if (productRequest.getName() != null) {
            if (productRepository.existsByNameAndIdIsNot(productRequest.getName(), id)) {
                throw new ProductNameAlreadyUsedException("Name: " + productRequest.getName() + " is already used");
            }

            model.setName(productRequest.getName());
        }

        if (productRequest.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            model.setPrice(productRequest.getPrice());
        }

        if (productRequest.getStock() >= 0) {
            model.setStock(productRequest.getStock());
        }

        return productMapper.modelToResponse(productRepository.save(model));
    }

    public void remove(Integer id) {
        productRepository.deleteById(id);
    }

    private Product getModel(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new
                ProductNotFoundException("Product with id: " + id + " not found")
        );
    }
}
