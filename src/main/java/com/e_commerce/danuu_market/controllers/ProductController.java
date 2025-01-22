package com.e_commerce.danuu_market.controllers;

import com.e_commerce.danuu_market.Exceptions.ProductAlreadyExists;
import com.e_commerce.danuu_market.Exceptions.ProductDoesNotExist;
import com.e_commerce.danuu_market.models.Products;
import com.e_commerce.danuu_market.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    Map<String, String> errorResponse = new HashMap<>();
    Logger LOG = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<?> getProducts(){
        try{
            LOG.info("Fetching Products...");
            List<Products> products = productService.findAllProducts();
            if(products.isEmpty()){
                errorResponse.put("error","No Product is Available");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
            System.out.println("the size of products is : "+products.size());
            return ResponseEntity.ok(products);
        }catch(Exception e){
            errorResponse.put("error", "Unhandled Exception has occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/getProduct")
    public ResponseEntity<?> getProductsById(@RequestParam int id){
        try{
            Products product = productService.findById(id);
            return ResponseEntity.ok(product);
        }
        catch(ProductDoesNotExist e){
            errorResponse.put("error", "No such product exists in our database");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        catch(Exception e){
            errorResponse.put("error","Unhandled Exception has occured");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam int id){
        try{
            productService.deleteProduct(id);
            errorResponse.put("success","Product Deleted Successfully");
            return ResponseEntity.ok(errorResponse);
        }catch(Exception e){
            errorResponse.put("error", "Unhandled Server Exception has occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Products products){
        if(products.getName().isEmpty() || products.getDescription().isEmpty()){
            errorResponse.put("error","Incorrect product formulation");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try{
            productService.createProduct(products);
            return ResponseEntity.status(HttpStatus.CREATED).body(products);
        }catch(ProductAlreadyExists pe){
            errorResponse.put("error","Product with this name does exist");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }catch(Exception e){
            errorResponse.put("error","Unhandled Internal Server Error Has Occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProducts(@RequestBody Products products){
        try{
            productService.editProduct(products);
            errorResponse.put("success", "product update successfully");
            return ResponseEntity.ok(errorResponse);
        }catch(Exception e){
            errorResponse.put("error", "Unhandled Exception occurred while updating the product");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
