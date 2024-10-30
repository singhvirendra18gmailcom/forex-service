package org.msoct.fis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrawingAppController {


	@GetMapping("/")
	public String  welcome() {
		return "welcome to the Drawing App";
	}
}
