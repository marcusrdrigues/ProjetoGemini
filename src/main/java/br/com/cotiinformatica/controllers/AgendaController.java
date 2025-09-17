package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AgendaDto;
import br.com.cotiinformatica.services.GeminiService;

@RestController
@RequestMapping(value = "/api/agenda")
public class AgendaController {
	
	@Autowired GeminiService geminiService;
	
	@PostMapping
	public String post(@RequestBody AgendaDto dto) {
		return geminiService.organizarAgenda(dto);
	}
}
