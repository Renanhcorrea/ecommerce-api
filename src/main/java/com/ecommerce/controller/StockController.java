package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.Stock;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock){
        try {
            Stock createdStock = stockService.createStock(stock);
            return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stockService.getStockById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Stock> getStockByProduct(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            Stock stock = stockService.getStockByProduct(product);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock(){
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id,@RequestBody Stock stock){
        try {
            return ResponseEntity.ok(stockService.updateStock(id, stock));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException  e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/product/{productId}/quantity")
    public ResponseEntity<Stock> updateStockQuantity(@PathVariable Long productId, @RequestParam Long change){
        try {
            Product product = productService.getProductById(productId);
            Stock updateStock = stockService.updateStockQuantity(product, change);
            return ResponseEntity.ok(updateStock);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockById(@PathVariable Long id) {
        try {
            stockService.deleteStockById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
