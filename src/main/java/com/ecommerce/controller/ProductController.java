package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String unit,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer groupType
    ){
        try {
            if (name != null){
                return ResponseEntity.ok(productService.getProductByName(name));
            } else if (unit != null) {
                return ResponseEntity.ok(productService.getProductByUnit(unit));
            } else if (type != null) {
                return ResponseEntity.ok(productService.getProductByType(type));
            } else if (groupType != null) {
                return ResponseEntity.ok(productService.getProductByGroupType(groupType));
            } else {
                return ResponseEntity.ok(productService.getAllProduct());
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

    }
    /*
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
     */

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable Product updatedProduct){
        try {
            return ResponseEntity.ok(productService.updateProduct(id, updatedProduct));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e ){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        try {
            productService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}

