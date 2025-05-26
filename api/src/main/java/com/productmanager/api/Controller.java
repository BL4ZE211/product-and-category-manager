package com.productmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/category")
    public String createCategory(@RequestBody Category newCategory) {
        if (newCategory.getName() != null && !newCategory.getName().trim().isEmpty()) {
            categoryRepository.save(newCategory);
            return "Category created";
        }
        return "Give the category a valid name";
    }

    @PostMapping("/product")
    public String createProduct(@RequestBody Product newProduct) {
        Category category = categoryRepository.findById(newProduct.getCategory_id())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + newProduct.getCategory_id()));
        if(category!=null){
            productRepository.save(newProduct);
            return "Product Created Sucessfully";
        }
        return "Give a valid Product Id";


    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable long id ){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
        if(product!=null){
            return product.toString();
        }
        return "No product with id: "+ id;

    }


    @GetMapping("/category")
    public  List<Category> getCategory(){
        return categoryRepository.findAll();
    }

    @PutMapping("/product")
    public String updateProduct(@RequestBody Product product){
        long id = product.getId();
        Product product1 = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
        if(product1!=null){
            product1.setUpdated_at(LocalDateTime.now());
            product1.setPrice(product.getPrice());
            product1.setName(product.getName());

            return "Product Updated ";
        }
        return "No product with id: "+id;
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable long id){
        Product product1 = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
        if(product1!=null){
            product1.set_active(false);
            return "Product deleted Sucessfully";
        }
        return "No product with id: "+id;
    }



}
