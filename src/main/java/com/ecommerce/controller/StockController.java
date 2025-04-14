package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.Stock;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(stockService.createStock(stock),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
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



}
