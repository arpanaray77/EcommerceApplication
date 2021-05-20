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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.productcatalogservice.Controller.DTO.ProductDTO;
import com.ecommerce.productcatalogservice.Model.Product;
import com.ecommerce.productcatalogservice.Service.CategoryService;
import com.ecommerce.productcatalogservice.Service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	public ProductService productService;
	@Autowired
	private CategoryService categoryService;
	static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/img";
	
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
			Files.write(fileName, file.getBytes());
		}else
		{
			imageUUID=imgName;
		}
		product.setImgName(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}
	
	
//	@GetMapping(value = "/products", params = "category")
//    public List<Product> getAllProductByCategory(@RequestParam ("category") String category){
//        List<Product> products = productService.getAllProductByCategory(category);
//        return products;
//    }
//
//    @GetMapping (value = "/products/{id}")
//    public Product getOneProductById(@PathVariable ("id") long id){
//        Product product =  productService.getProductById(id);
//       return product;
//    }
//
//    @GetMapping (value = "/products", params = "name")
//    public List<Product> getAllProductsByName(@RequestParam ("name") String name){
//        List<Product> products =  productService.getAllProductByName(name);
//       return products;
//    }

}
