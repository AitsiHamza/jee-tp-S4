package com.example.ecommerce.web;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProductController {
    ProductRepository productRepository;

    @GetMapping(path="/admin/productsJson")
    @ResponseBody
    List<Product> getProductsJson(){
        return productRepository.findAll();
    }

    @GetMapping(path = "/")
    public String home(){
        return "home";
    }

    @GetMapping(path = "/admin/products")
    public String products(Model model,
                              @RequestParam(name = "page",defaultValue = "0") int page,
                              @RequestParam(name = "size",defaultValue = "5") int size,
                              @RequestParam(name = "keyword",defaultValue = "")String keyword){
        Page<Product> products=productRepository.getByNameContains(keyword, PageRequest.of(page,size));
        model.addAttribute("products",products);
        model.addAttribute("pages",new int[products.getTotalPages()]);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "products";
    }

    @DeleteMapping(path = "/admin/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") String id){
        productRepository.deleteById(id);
        return "redirect:/user/products";
    }

    @PostMapping(path = "/admin/addProduct/{name}")
    public String addProduct(@PathVariable(name = "name") String name){
        Product product= new Product();
        product.setName(name);
        product.setIdProduct(UUID.randomUUID().toString());
        productRepository.save(product);
        return "redirect:/user/products";
    }
    @PutMapping(path = "/admin/update/{id}")
    public String updateProduct(@PathVariable(name = "id")String id){
        Product product= productRepository.getById(id);
        productRepository.save(product);
        return "redirect:/user/products";
    }
    @PatchMapping(path = "/admin/partialUpdate/{id}")
    public String partialUpdateProduct(@PathVariable(name = "id")String id){
        Product product= productRepository.getById(id);
        productRepository.save(product);
        return "redirect:/user/products";
    }

}
