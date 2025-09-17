package br.com.cotiinformatica.dtos.gemini;

public class GeminiGenerateContentDto {
	
	private GeminiContentDto[] contents;

	public GeminiContentDto[] getContents() {
		return contents;
	}

	public void setContents(GeminiContentDto[] contents) {
		this.contents = contents;
	}
}
