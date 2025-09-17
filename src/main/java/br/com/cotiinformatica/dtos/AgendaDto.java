package br.com.cotiinformatica.dtos;

import java.util.List;

public class AgendaDto {
	
	private String dataInicio;
	private String dataFim;
	private String descricao;
	private List<TarefaDto> tarefas;
	
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<TarefaDto> getTarefas() {
		return tarefas;
	}
	public void setTarefas(List<TarefaDto> tarefas) {
		this.tarefas = tarefas;
	}

}
