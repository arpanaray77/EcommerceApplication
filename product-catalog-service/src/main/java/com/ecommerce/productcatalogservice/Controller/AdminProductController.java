package com.ecommerce.productcatalogservice.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.productcatalogservice.Controller.DTO.ProductDTO;
import com.ecommerce.productcatalogservice.Model.Product;
import com.ecommerce.productcatalogservice.Service.CategoryService;
import com.ecommerce.productcatalogservice.Service.ProductService;

@Controller
public class AdminProductController {
   
	@Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    
    @GetMapping(value = "/admin/products")
     public String getAllProducts(Model model){
      model.addAttribute("products", productService.getAllProduct());
        return "products";
    }
    
    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
    
    @PostMapping("/admin/products/add")
    public String postProduct(@ModelAttribute("productDTO") ProductDTO productDTO,@RequestParam("productImage") MultipartFile file,
    @RequestParam("imgName")String imgName)throws IOException {
        
        Product product =new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setAvailability(productDTO.getAvailability());
        String imageUUID;
        if(!file.isEmpty())
        {
            imageUUID=file.getOriginalFilename();
            Path fileName =Paths.get(uploadDir,imageUUID);
            //System.out.println(fileName);
            Files.write(fileName, file.getBytes());
        }else
        {
            imageUUID=imgName;
        }
        product.setImgName(imageUUID);
        productService.addProduct(product);
        
        return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id)
    {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id,Model model){
        
        Product product = productService.getProductById(id); //get() is used to get the value or it will return exception

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setCategoryId(product.getCategory().getCategoryId());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setAvailability(product.getAvailability());
        productDTO.setImgName(product.getImgName());
        
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDTO",productDTO);
        
            return "productsAdd";
    }
}
