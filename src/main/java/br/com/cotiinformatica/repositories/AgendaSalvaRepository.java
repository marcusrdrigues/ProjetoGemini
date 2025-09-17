package br.com.cotiinformatica.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cotiinformatica.entities.AgendaSalva;

public interface AgendaSalvaRepository extends JpaRepository<AgendaSalva, UUID> {
	

    /**
     * Busca agendas salvas onde o resultado do Gemini contém uma palavra-chave.
     * A busca é sensível a maiúsculas/minúsculas.
     * Exemplo de SQL gerado: WHERE resultado_gemini LIKE '%palavraChave%'
     */
	List<AgendaSalva> findByResultadoGeminiContaining(String palavraChave);
	
	/**
     * Busca agendas salvas onde os dados enviados pelo usuário contêm um termo.
     * A busca NÃO é sensível a maiúsculas ou minúsculas.
     * Exemplo de SQL gerado: WHERE UPPER(dados_enviados) LIKE UPPER('%termo%')
     */
	List<AgendaSalva> findByDadosEnviadosContainingIgnoreCase(String termo);
	
}
