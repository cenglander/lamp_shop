package lamp_shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LampWebController {

	@Autowired
	LampShopService lampShopService;
	
	
//	@GetMapping("products")
//	public String getAllProducts(Model m) {
//		m.addAttribute("products", productService.getAllProducts());
//		return "products-page";
//	}
	
}
