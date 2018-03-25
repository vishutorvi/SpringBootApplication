package uttara.com.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import uttara.com.spring.exceptions.ResourceNotFoundException;
import uttara.com.spring.jpa.Products;
import uttara.com.spring.model.ProductRepository;

@RestController
@RequestMapping("/products")
@ComponentScan("uttara.com.model")
public class ProductController {

	private static final String description = "A buyer persona is an imaginary customer. It is the person for whom you’ve developed your product and to whom you’d love to sell it (of course!). He or she represents your target audience, but is much more real than a vague description of some demographics";
	private static final String productLine = "Motorcycles";
	private static final String productScale = "1:10";
	Gson g = new Gson();
	@Autowired
	ProductRepository productRepository;
	
	@CrossOrigin(origins = {"http://localhost:3000","http://52.91.233.77:3000/"})
	@GetMapping("/all")
	public List<Products> getAllProducts(){
		List<Products> products = productRepository.findAll();
		return products;
	}
	
	@CrossOrigin(origins = {"http://localhost:3000\",\"http://52.91.233.77:3000/"})
	@GetMapping("/{id}")
	public Optional<Products> getOneProducts(@PathVariable("id") String id){
		Optional<Products> products = productRepository.findById(id);
		return products;
	}
	
	@CrossOrigin(origins = {"http://localhost:3000","http://52.91.233.77:3000/"})
	@PutMapping("/update/{id}")
	public Products updateProduct(@PathVariable("id") String id, @Valid @RequestBody Products productDetails) {
	    Products product = productRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Products", "id", id));

	    product.setId(productDetails.getId());
	    product.setProductDescription(productDetails.getProductDescription());
	    product.setBuyPrice(productDetails.getBuyPrice());
	    product.setMsrp(productDetails.getMsrp());
	    product.setProductLine(productDetails.getProductLine());
	    product.setProductName(productDetails.getProductName());
	    product.setProductScale(productDetails.getProductScale());
	    product.setProductVendor(productDetails.getProductVendor());
	    Products updatedProduct = productRepository.save(product);
	    return updatedProduct;
	}
	
	public static String setPrecision(double nbr, int decimal) {
	    int integer_Part = (int) nbr;
	    double float_Part = nbr - integer_Part;
	    int floating_point = (int) (Math.pow(10, decimal) * float_Part);
	    String final_nbr = String.valueOf(integer_Part) + "." + String.valueOf(floating_point);
	    return final_nbr;
	}
	
	@CrossOrigin(origins = {"http://localhost:3000","http://52.91.233.77:3000/"})
	@PostMapping("/create")
	public Products createProduct(@Valid @RequestBody String product) {
		Products product1 = new Products();
		Double random = Math.random() * 10 + 1;
		String id = setPrecision(random,5);
		if(product != null) {
			product1 = g.fromJson(product, Products.class);
			product1.setId(id);
			product1.setProductDescription(description);
			product1.setProductScale(productScale);
			product1.setProductLine(productLine);
		}
	    return productRepository.save(product1);
	}
	
	@CrossOrigin(origins = {"http://localhost:3000","http://52.91.233.77:3000/"})
	@PostMapping("/deleteIds")
	public ResponseEntity<?> deleteProduct(@RequestBody String ids) {
		if(ids == null) {
			return ResponseEntity.badRequest().build();
		}
		String splitIds = ids.split(":")[1];
		int length = splitIds.length()-2;
		String originalIds = splitIds.substring(1, length);
		originalIds = originalIds.replaceAll("[\'-+^]*\"", "");
		String[] Ids = originalIds.split(",");
		
		for(String id:Ids) {
			if(id.contains("//[") || id.contains("\\]")) {
			}else {
				List<Products> productsList = productRepository.findAll();
				
				Products product = productRepository.findById(id)
			            .orElseThrow(() -> new ResourceNotFoundException("Products", "id", id));
	
			    productRepository.delete(product);
			}
		}
	    return ResponseEntity.ok().build();
	}
}
