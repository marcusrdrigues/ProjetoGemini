package br.com.cotiinformatica.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agenda_salva")
public class AgendaSalva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "dados_enviados", columnDefinition = "TEXT")
	private String dadosEnviados; //O JSON enviado para o Gemini
	
	@Column(name = "resultado_gemini", columnDefinition = "TEXT")
	private String resultadoGemini; //A resposta que o Gemini retornar

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDadosEnviados() {
		return dadosEnviados;
	}

	public void setDadosEnviados(String dadosEnviados) {
		this.dadosEnviados = dadosEnviados;
	}

	public String getResultadoGemini() {
		return resultadoGemini;
	}

	public void setResultadoGemini(String resultadoGemini) {
		this.resultadoGemini = resultadoGemini;
	}
}
