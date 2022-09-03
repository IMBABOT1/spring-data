package com.geekbrains.springdata.controllers;

import com.geekbrains.springdata.entities.Product;
import com.geekbrains.springdata.exceptions.ResourceNotFoundException;
import com.geekbrains.springdata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductByID(id);
    }

    @GetMapping("/products/min_price")
    public List<Product> findAllProductsByPriceGreaterThan(@RequestParam(defaultValue = "0") Integer min) {
        return productService.findAllProductsByPriceGreaterThan(min);
    }

    @GetMapping("/products/max_price")
    public List<Product> findAllProductsByPriceLessThan(@RequestParam(defaultValue = "0") Integer max) {
        return productService.findAllProductsByPriceLessThan(max);
    }

    @GetMapping("/products/price_between")
    public List<Product> findProductsByPriceBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "200") Integer max) {
        return productService.findProductsByPriceBetween(min, max);
    }

    @PostMapping("/products/getProduct")
    public void getProduct(@RequestBody Product p){
        productService.saveProduct(p);
    }
}
