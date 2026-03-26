package com.example.demo.controllers3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.entities.Total;
import com.example.demo.helpers.HelperC;
import com.example.demo.services.Cartser;
import com.example.demo.services.ProductSer;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class ProductsController {
	
	@Autowired
	private ProductSer pser;
	
	@Autowired
	private Cartser cser;
	
	@PostMapping("/savep")
	public ResponseEntity<?> saveProduct(@RequestBody Product p) {
		String res = pser.saveProduct(p);
		System.out.println(res);
		HelperC h = new HelperC();
		h.setMessage("Product saved");
		h.setData(p);
		return new ResponseEntity<>(h,HttpStatus.OK);
	}
	
//	Find all available products

	@GetMapping("/availp")
	public ResponseEntity<?> findAvailP(){
		List<Product> li = pser.findAllPA();
		HelperC h = new HelperC();
		  if (li == null || li.isEmpty()) {
		        h.setMessage("No available products found");
		        h.setData(li);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }
		  else {
		    h.setMessage("Available Products");
		    h.setData(li);
		    System.out.println(h);
		    return new ResponseEntity<>(h, HttpStatus.OK);
		  }
	}
	
	@GetMapping("/product/{pid}")
	public ResponseEntity<?> findAvailP1(@PathVariable Integer pid){
		Product product = pser.findAllPA1(pid);
		HelperC h = new HelperC();
		 if (product == null) {
		        h.setMessage("Product not found");
		        h.setData(null);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }

		    h.setMessage("Product found");
		    h.setData(product);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
//	Find by category (only available products)

	@GetMapping("/availBCat")
	public ResponseEntity<?> findByCategoryA(@RequestParam String category){
		List<Product> li = pser.findByCatAvail(category);
		HelperC h = new HelperC();
		  if (li == null || li.isEmpty()) {
		        h.setMessage("No available products found");
		        h.setData(li);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }

		    h.setMessage("Available Products");
		    h.setData(li);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
//	Find by category + color
	
	@GetMapping("/catandcolor")
	public ResponseEntity<?> findByCatandColor(
			@RequestParam String category,
			@RequestParam String color){
		List<Product> li = pser.findByCaCo(category,color);
		HelperC h = new HelperC();
		  if (li == null || li.isEmpty()) {
		        h.setMessage("No available products found");
		        h.setData(li);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }

		    h.setMessage("Available Products");
		    h.setData(li);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
//	Find products in a price range

	@GetMapping("/findbypricerenge")
	public ResponseEntity<?> findPInRange(
			@RequestParam Double min,
			@RequestParam Double max){
		List<Product>  li = pser.findInRange(min,max);
		HelperC h = new HelperC();
		  if (li == null || li.isEmpty()) {
		        h.setMessage("No available products found");
		        h.setData(li);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }

		    h.setMessage("Available Products");
		    h.setData(li);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
//	Search by title (case-insensitive, partial match)

	@GetMapping("/searchBTitle")
	public ResponseEntity<?> findByTitleA(@RequestParam String keyword){
		List<Product> li = pser.searchByTitle(keyword);
		HelperC h = new HelperC();
		  if (li == null || li.isEmpty()) {
		        h.setMessage("No available products found");
		        h.setData(li);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }

		    h.setMessage("Available Products");
		    h.setData(li);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
//	Low-stock products (inventory alert)

	@GetMapping("/lowstock")
	public ResponseEntity<?> findlowStockP(
			@RequestParam Integer quantity){
		List<Product> li = pser.findLowStock(quantity);
		HelperC h = new HelperC();
		  if (li == null || li.isEmpty()) {
		        h.setMessage("No available products found");
		        h.setData(li);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }

		    h.setMessage("Available Products");
		    h.setData(li);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
//	Category + price range + availability (very common)
	
	@GetMapping("/catpriceavail")
	public ResponseEntity<?> findcategoryavail(
			@RequestParam String category,
			@RequestParam Double min,
			@RequestParam Double max){
		List<Product> li = pser.findByCatPriceBet(category,min,max);
		HelperC h = new HelperC();
		  if (li == null || li.isEmpty()) {
		        h.setMessage("No available products found");
		        h.setData(li);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }

		    h.setMessage("Available Products");
		    h.setData(li);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		Product p =  pser.deleteByid(id);
		HelperC h = new HelperC();
		 if (p == null) {
		        h.setMessage("Product not found");
		        h.setData(null);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }
		    h.setMessage("deleted Product");
		    h.setData(p);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateP/{id}")
	public ResponseEntity<?> editeProduct(@PathVariable Integer id,
			@RequestBody Product p) {
		 Product p1 = pser.updateP(id,p);
		HelperC h = new HelperC();
		 if (p1 == null) {
		        h.setMessage("Product not found");
		        h.setData(null);
		        return new ResponseEntity<>(h, HttpStatus.NOT_FOUND);
		    }
		    h.setMessage("updated Product");
		    h.setData(p);
		    return new ResponseEntity<>(h, HttpStatus.OK);
	}
	
	
	@PostMapping("/addtocart")
	public ResponseEntity<?> addToCartP(@RequestParam int uid,
            @RequestParam int pid){
		   pser.addCart(uid,pid);
		 return ResponseEntity.ok("Product added to cart");
	}
	
	@GetMapping("/showCart")
	public List<Cart> getCData(@RequestParam int id){
		List<Cart> li = pser.getAllc(id);
		return li;
	}
	

	 @PostMapping("/create-order")
	    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Total total) {
		     System.out.println(total.getTotal());
	        try {
	            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_RsxBPi44tfgxPg"
	            		+ "", "GGF8rvJDemhwkiiUVBh7yo0p"
	  );

	            JSONObject orderRequest = new JSONObject();
	            int amount = (int) total.getTotal()*100; // Convert to paise
	            orderRequest.put("amount",amount);
	            orderRequest.put("currency", "INR");
	            orderRequest.put("receipt", "txn_123456");
	            orderRequest.put("payment_capture", 1);

	            Order order = razorpayClient.orders.create(orderRequest);

	            Map<String, Object> response = new HashMap<>();
	            response.put("id", order.get("id"));
	            response.put("amount", order.get("amount"));
	            response.put("currency", order.get("currency"));

	            return ResponseEntity.ok(response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(500).body(Map.of("error", "Order creation failed"));
	        }
	 }
	
	
	 @DeleteMapping("/deleteItem/{id}")
	 public Map<String,String> deleteItem(@PathVariable int id){

	     cser.deleteItemId(id);

	     return Map.of("message","Item removed");
	 }
	
	 @PostMapping("/deleteCart/{userId}")
	 public String deleteCart(@PathVariable int userId){

	     cser.deleteCart(userId);

	     return "Cart cleared";
	 }
	

}
