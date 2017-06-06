package controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class BankController {
	
	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public ResponseEntity<String> getBalance(){
		return ResponseEntity.ok("10000");
	}
}
