package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create
    @Transactional
    public Product createProduct(Product product){
        validateProduct(product);

        if(product.getId() == null){
            int groupType = product.getGroupType();
            Long maxID = productRepository.findMaxIdByGroupType(groupType);
            long base = groupType * 1000L;
            long newId = base +1;
            if (maxID != null && maxID > base){
                newId = maxID +1;
            }
            product.setId(newId);
        }
        return productRepository.save(product);
    }

    // Validete
    public void validateProduct(Product product){
        if(product.getName()==null || product.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Name must not be empty.");
        }
        if(product.getPrice()==null || product.getPrice()<=0){
            throw new IllegalArgumentException("Price must not be empty.");
        }
        if(product.getUnit()==null || product.getUnit().trim().isEmpty()){
            throw new IllegalArgumentException("Unit must not be empty.");
        }
        if(product.getGroupType()==null||product.getGroupType()<=0){
            throw new IllegalArgumentException("Group Type must not be empty or 0.");
        }
    }

    // Find by ID
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Product not found with ID: " + id));
    }

    // Find All
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    // Find by name
    public List<Product> getProductByName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name must not be empty.");
        }
        return productRepository.findByNameIgnoreCaseContaining(name.trim());
    }

    // Find by Unit
    public List<Product> getProductByUnit(String unit){
        if(unit == null || unit.trim().isEmpty()){
            throw new IllegalArgumentException("Unit must not be empty.");
        }
        return productRepository.findByUnitIgnoreCaseContaining(unit.trim());
    }

    // Find by Type
    public List<Product> getProductByType(String type){
        if(type==null || type.trim().isEmpty()){
            throw new IllegalArgumentException("Type must not be empty.");
        }
        return productRepository.findByTypeIgnoreCaseContaining(type.trim());
    }

    // Find by Group
    public List<Product> getProductByGroupType(Integer grouptype){
        if(grouptype==null || grouptype<=0){
            throw new IllegalArgumentException("Group Type must not be empty or 0.");
        }
        return productRepository.findByGroupType(grouptype);
    }

    // Update
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct){
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isEmpty()){
            throw new NoSuchElementException("Product not found with ID: "+ id);
        }
        Product productExisting = getProductById(id);
        updatedProduct.setId(productExisting.getId());
        return productRepository.save(updatedProduct);
    }

    // Delete
    @Transactional
    public void deleteProductById(Long id){
        if(!productRepository.existsById(id)){
            throw new NoSuchElementException("Product not found with ID: "+ id);
        }
        productRepository.deleteById(id);
    }

}
