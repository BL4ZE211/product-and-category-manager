package com.productmanager.api;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    ArrayList<Category> categoriesList = new ArrayList<>();
    ArrayList<Product> productsList = new ArrayList<>();

    @PostMapping("/category")
    public String createCategory(@RequestBody Category newCategory) {
        if (newCategory.getName() != null && !newCategory.getName().trim().isEmpty()) {
            categoriesList.add(newCategory);
            return "Category created";
        }
        return "Give the category a valid name";
    }

    @PostMapping("/product")
    public String createProduct(@RequestBody Product newProduct) {
        long c_id = newProduct.getCategory_id();
        for(Category ele:categoriesList){
            if(ele.getId()== c_id && ele.is_active()){
                productsList.add(newProduct);
                return "Product created";
            }
        }
        return "Catogory not found";
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productsList;
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable long id ){
        for(Product ele:productsList){
            if(ele.getId()==id){
                return  ele.toString();
            }
        }
            return "Product Not Found";
    }


    @GetMapping("/category")
    public  List<Category> getCategory(){
        return categoriesList;
    }

    @PutMapping("/product")
    public String updateProduct(@RequestBody Product product){
        long pId = product.getId();
        for(Product ele:productsList){
            if(ele.getId() == pId) {
                ele.setId(pId);
                ele.setName(product.getName());
                ele.setPrice(product.getPrice());
                ele.setUpdated_at(LocalDateTime.now());

                return "Product Details Updated";
            }
        }
        return  "Provide Valid Product Id";
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable long id){
        for (Product ele : productsList){
            if(ele.getId()==id){
                ele.set_active(false);

                return "Product deleted successfully";
            }
        }
        return "Product Not Found";
    }



}
