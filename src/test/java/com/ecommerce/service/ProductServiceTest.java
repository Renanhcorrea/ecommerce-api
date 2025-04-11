package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setId(1001L);
        product.setName("Parafuso");
        product.setDescription("Test01");
        product.setPrice(0.15);
        product.setUnit("un");
        product.setType("Fixador");
        product.setGroupType(1);
    }

    @Test
    public void testCreateProduct(){
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertEquals("Parafuso", result.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testCreateProduct_InvalidName_ThrowsException(){
        product.setName("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        assertEquals("Name must not be empty.", exception.getMessage());
    }

    @Test
    public void testGetProductById_Sucess(){
        when(productRepository.findById(1001L)).thenReturn(Optional.of(product));

        Product found = productService.getProductById(1001L);
        assertEquals("Parafuso", found.getName());
    }

    @Test
    public void testGetProductById_NotFound(){
        when(productRepository.findById(9999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, ()->productService.getProductById(9999L));
        assertTrue(exception.getMessage().contains("Product not found"));
    }

    @Test
    public void testGetAllProduct(){
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> products= productService.getAllProduct();
        assertEquals(1, products.size());
    }

    @Test
    public void testGetProductByName(){
        when(productRepository.findByNameIgnoreCaseContaining("para"))
                .thenReturn(List.of(product));

        List<Product> products = productService.getProductByName("para");
        assertEquals(1, products.size());
    }

    @Test
    public void testGetProductByName_ThrowsException(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productService.getProductByName(""));
        assertEquals("Name must not be empty.", exception.getMessage());
    }

    @Test
    public void testGetProductByUnit(){
        when(productRepository.findByUnitIgnoreCaseContaining("un"))
                .thenReturn(List.of(product));

        List<Product> products = productService.getProductByUnit("un");
        assertEquals(1, products.size());
    }

    @Test
    public void testGetProductByType(){
        when(productRepository.findByTypeIgnoreCaseContaining("fixador"))
                .thenReturn(List.of(product));
        List<Product> products = productService.getProductByType("fixador");
        assertEquals(1, products.size());
    }

    @Test
    public void testGetProductByGroupType(){
        when(productRepository.findByGroupType(1))
                .thenReturn(List.of(product));

        List<Product> products = productService.getProductByGroupType(1);
        assertEquals(1, 1);
    }

    @Test
    public void testUpdateProduct(){
        Product updated = new Product();
        updated.setId(1001L);
        updated.setName("Parafuso Atualizado");
        updated.setPrice(0.30);
        updated.setUnit("cj");
        updated.setGroupType(3);

        when(productRepository.findById(1001L)).thenReturn(
                Optional.of(product)
        );
        when(productRepository.save(updated)).thenReturn(updated);

        Product result = productService.updateProduct(1001L, updated);

        assertEquals("Parafuso Atualizado", result.getName());
        verify(productRepository, times(1)).save(updated);
    }

    @Test
    public void testDeleteProduct_Sucess(){
        when(productRepository.existsById(1001L)).thenReturn(true);

        productService.deleteProductById(1001L);

        verify(productRepository, times(1)).deleteById(1001L);
    }

    @Test
    public void testDeleteProduct_NotFound(){
        when(productRepository.existsById(1002L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class,
                () -> productService.deleteProductById(1002L));

        assertTrue(exception.getMessage().contains("Product not found"));
    }
}
