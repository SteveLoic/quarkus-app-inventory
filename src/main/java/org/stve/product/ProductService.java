package org.stve.product;


import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.stve.category.Category;
import org.stve.category.CategoryRepository;

import java.util.List;
import java.util.Objects;

@Dependent
public class ProductService {
    @Inject
    CategoryRepository categoryRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    ModelMapper modelMapper;

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
        Category category = categoryRepository.findByName(productRequest.getCategoryName()).orElseThrow(
                () -> new EntityNotFoundException(String.format("Category with Id %s not found", productRequest.getCategoryName()))
        );
        Product product = modelMapper.map(productRequest, Product.class);
        product.setCategory(category);
        productRepository.persist(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll().list();
        return modelMapper.map(products, new TypeToken<List<ProductResponse>>() {
        }.getType());
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findByIdOptional(productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Product with Id %s not found", productId)
                ));

        return modelMapper.map(product, ProductResponse.class);
    }

    @Transactional
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product existingProduct = productRepository.findByIdOptional(productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Product with Id %s not found", productId)
                ));
        existingProduct = updatedExistingProduct(existingProduct, productRequest);
        productRepository.persist(existingProduct);
        return modelMapper.map(existingProduct, ProductResponse.class);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.findByIdOptional(productId).ifPresentOrElse(
                existingProduct -> productRepository.delete(existingProduct),
                () -> new EntityNotFoundException(String.format("Product with Id %s not found", productId))
        );
    }

    private Product updatedExistingProduct(Product existingProduct, ProductRequest productRequest) {
        existingProduct.setName(Objects.equals(productRequest.getName(), null) ? existingProduct.getName() : productRequest.getName());
        existingProduct.setPrice(Objects.equals(productRequest.getPrice(), null) ? existingProduct.getPrice() : productRequest.getPrice());
        existingProduct.setSku(Objects.equals(productRequest.getSku(), null) ? existingProduct.getSku() : productRequest.getSku());
        existingProduct.setStockQuantity(Objects.equals(productRequest.getStockQuantity(), null) ? existingProduct.getStockQuantity() : productRequest.getStockQuantity());
        existingProduct.setDescription(Objects.equals(productRequest.getDescription(), null) ? existingProduct.getDescription() : productRequest.getDescription());
        return existingProduct;
    }
}
