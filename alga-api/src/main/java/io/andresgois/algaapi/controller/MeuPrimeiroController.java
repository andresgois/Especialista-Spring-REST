package io.andresgois.algaapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeuPrimeiroController {

	@GetMapping("/home")
	public String home() {
		return "Hello World 2!";

	}
}
