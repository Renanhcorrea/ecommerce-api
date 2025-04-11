package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(
                productService.createProduct(product),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @GetMapping("/{unit}")
    public ResponseEntity<List<Product>> getProductByUnit(@PathVariable String unit){
        return ResponseEntity.ok(productService.getProductByUnit(unit));
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Product>> getProductByType(@PathVariable String type){
        return ResponseEntity.ok(productService.getProductByType(type));
    }

    @GetMapping("/{group}")
    public ResponseEntity<List<Product>> getProductByGroupType(@PathVariable Integer grouptype){
        return ResponseEntity.ok(productService.getProductByGroupType(grouptype));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable Product updatedProduct){
        return ResponseEntity.ok(productService.updateProduct(id, updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

