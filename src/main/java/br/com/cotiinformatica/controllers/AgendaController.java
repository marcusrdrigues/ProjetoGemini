package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AgendaDto;
import br.com.cotiinformatica.entities.AgendaSalva;
import br.com.cotiinformatica.repositories.AgendaSalvaRepository;
import br.com.cotiinformatica.services.GeminiService;

@RestController
@RequestMapping(value = "/api/agenda")
public class AgendaController {
	
	private static final String PALAVRA_CHAVE = "palavraChave";

	@Autowired 
	private GeminiService geminiService;
	
	@Autowired
	private AgendaSalvaRepository agendaSalvaRepository;
	
	@CrossOrigin(origins = "*")
	@PostMapping
	public ResponseEntity<String> post(@RequestBody AgendaDto dto) {
		
		try {
			String resultado = geminiService.organizarAgenda(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
		
		} catch (Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
		}
	}
	// 2. Criando o novo método para consultar
	@CrossOrigin(origins = "*")
	@GetMapping("/buscar") // Será acessado via GET /api/agenda/buscar
	public ResponseEntity<List<AgendaSalva>> getByKeyword(@RequestParam(PALAVRA_CHAVE) String palavraChave) {
		
		try {
			// Usa o método que criamos no repositório para buscar os resultados!
			List<AgendaSalva> agendas = agendaSalvaRepository.findByResultadoGeminiContaining(palavraChave);
			
			return ResponseEntity.status(HttpStatus.OK).body(agendas);
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
