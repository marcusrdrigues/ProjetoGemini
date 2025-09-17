package br.com.cotiinformatica.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

import br.com.cotiinformatica.dtos.AgendaDto;
import br.com.cotiinformatica.dtos.gemini.GeminiContentDto;
import br.com.cotiinformatica.dtos.gemini.GeminiGenerateContentDto;
import br.com.cotiinformatica.dtos.gemini.GeminiPartRequestDto;
import br.com.cotiinformatica.entities.AgendaSalva;
import br.com.cotiinformatica.repositories.AgendaSalvaRepository;

@Service
public class GeminiService {
	
	@Autowired // Injetando o repositório
	private AgendaSalvaRepository agendaSalvaRepository;
	
	@Value("${gemini.api.url}")
	private String apiUrl;
	
	@Value("${gemini.api.key}")
	private String apiKey;
	
	private final WebClient webClient;
	
	public GeminiService(WebClient.Builder webBuilder) {
		webClient = webBuilder.build();
	}
	
	public String organizarAgenda(AgendaDto dto) {
		
		var tarefas = "";
		for (var tarefa : dto.getTarefas()) {
			tarefas += "Tarefa: " + tarefa.getNome() + ", horas necessárias: " + tarefa.getTempoEmHoras() + ", complexidade: " + tarefa.getComplexidade() + ", urgência: " + tarefa.getUrgencia() + ". ";
		}
		
		var pergunta = "Planeje e organize a minha agenda de tarefas para o período de datas de: " + dto.getDataInicio() + " até " + dto.getDataFim() + " " + "e sabendo que a minha disponibilidade de trabalho é: " + dto.getDescricao() + "." + "Preciso que defina a melhor data de início, fim e horário para execução de cada tarefa, seguem: " + tarefas;
		
		var part = new GeminiPartRequestDto();
		part.setText(pergunta);
		
		var parts = new ArrayList<GeminiPartRequestDto>();
		parts.add(part);
		
		var content = new GeminiContentDto();
		content.setParts(parts.toArray(new GeminiPartRequestDto[0]));
		
		var contents = new ArrayList<GeminiContentDto>();
		contents.add(content);
		
		var generate = new GeminiGenerateContentDto();
		generate.setContents(contents.toArray(new GeminiContentDto[0]));
		
		var resposta = webClient.post()
				.uri(apiUrl)
				.bodyValue(generate)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		// Criar um objeto da entidade para salvar no banco de dados
		AgendaSalva agendaSalva = new AgendaSalva();
		
		// Converter o DTO original para uma String JSON para guardar o que foi enviado
		agendaSalva.setDadosEnviados(new Gson().toJson(dto)); // Convertendo o DTO para JSON
		agendaSalva.setResultadoGemini(resposta); // Guardando a resposta do Gemini
		
		// Salva no banco de dados
		agendaSalvaRepository.save(agendaSalva);
		
		return resposta;
	}
}
