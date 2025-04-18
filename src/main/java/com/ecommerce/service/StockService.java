package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.Stock;
import com.ecommerce.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // Create
    @Transactional
    public Stock createStock(Stock stock){
        if (stock.getProduct() == null || stock.getProduct().getId() == null)
            throw new IllegalArgumentException("Product must NOT be empty.");
        if(stock.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");
        if (stock.getLocation() == null || stock.getLocation().trim().isEmpty())
            throw new IllegalArgumentException("Location must NOT be empty.");
        return stockRepository.save(stock);
    }

    // Find by ID
    public Stock getStockById(Long id){
        return stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Stock not found with ID: " + id));
    }

    // Find by Product
    public Stock getStockByProduct(Product product){
        return stockRepository.findByProduct(product)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + product.getId()));
    }

    // Find All
    public List<Stock> getAllStock(){
        return stockRepository.findAll();
    }

    // Update
    @Transactional
    public Stock updateStock(Long id, Stock stock){
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Stock not found with ID: " +id));
        if (stock.getProduct() != null && stock.getProduct().getId() != null){
            existingStock.setProduct(stock.getProduct());
        }
        if (stock.getQuantity() != null && stock.getQuantity() >= 0){
            existingStock.setQuantity(stock.getQuantity());
        }
        if (stock.getLocation() != null && stock.getLocation().trim().isEmpty()){
            existingStock.setLocation(stock.getLocation());
        }
        return stockRepository.save(existingStock);
    }

    // Update Quantity
    @Transactional
    public Stock updateStockQuantity(Product product, Long quantityChange){
        Stock existingStock = stockRepository.findByProduct(product)
                .orElseThrow(() -> new NoSuchElementException
                        ("Stock not found with ID: " + product.getId()));

        Long newQuantity = existingStock.getQuantity() + quantityChange;
        if (newQuantity < 0){
            throw new IllegalArgumentException
                    ("Not enough stock available for Product ID: " + product.getId());
        }
        existingStock.setQuantity(newQuantity);
        return stockRepository.save(existingStock);

    }

    // Delete
    @Transactional
    public void deleteStockById(Long id){
        if (!stockRepository.existsById(id)){
            throw new RuntimeException("Stock not found with ID: " +id);
        }
        stockRepository.deleteById(id);
    }
}
