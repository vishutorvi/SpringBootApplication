package uttara.com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

	@RequestMapping("/catalogRequest")
	public String getCatalogData() {
		return "Bande Catalog Nodoke!!";
	}
	
	
}
