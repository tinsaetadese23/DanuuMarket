package com.e_commerce.danuu_market.service;

import com.e_commerce.danuu_market.Exceptions.AlreadyExistException;
import com.e_commerce.danuu_market.Exceptions.NotFoundException;
import com.e_commerce.danuu_market.models.Products;
import com.e_commerce.danuu_market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Products createProduct(Products products){
        if(this.findByName(products.getName()) != null){
            throw  new AlreadyExistException("Product Already Exists");
        }
        return productRepository.save(products);
    }

    public Products findByName(String name){
        if(name.equals("")){
            throw new NullPointerException();
        }
        return productRepository.findProductsByName(name);
    }

    public List<Products> findAllProducts(){
        return productRepository.findAll();
    }

    public Products findById(int id) throws NotFoundException {
        if(Integer.valueOf(id) == null){
            throw new NullPointerException();
        }
        if(productRepository.findById(id) == null){
            throw new NotFoundException("Such Product Does Not Exist");
        }
        return productRepository.findById(id);
    }

    public void deleteProduct(int id) throws NotFoundException {
        if(Integer.valueOf(id) == null){
            throw new NullPointerException();
        }
        if(productRepository.findById(id) == null){
            throw new NotFoundException("Such Product Does Not Exist");
        }
        Products products = productRepository.findById(id);
        productRepository.delete(products);
    }

    public Products editProduct(Products products) throws Exception {
        if(products == null){
            throw new NullPointerException("null pointer exception");
        }
        if(productRepository.findById(products.getId()) == null){
            throw new NotFoundException("product does not exist");
        }
        Products old_products = productRepository.findById(products.getId());
        if(old_products == products){
            throw new Exception("No field is changed");
        }
        old_products.setName(products.getName());
        old_products.setPrice(products.getPrice());
        old_products.setDescription(products.getDescription());
        old_products.setStockQuantity(products.getStockQuantity());
        old_products.setCategory(products.getCategory());

        return productRepository.save(old_products);
    }
}
