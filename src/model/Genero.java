package model;

public enum Genero {
	Agenero("Agênero"), Androgino("Andrógino"), Bigenero("Bigênero"),
	HomemCisgenero("Homem cisgênero"), MulherCisgenero("Mulher cisgênero"),
	DuploEspirito("Duplo espírito"), Genderqueer("Genderqueer"),
	GeneroEmduvida("Gênero em dúvida"), GeneroFluido("Gênero fluido"),
	GeneroNaoConformista("Gênero não conformista"), 
	Generovariante("Gênero variante"), HomemTrans("Homem trans"),
	MulherTrans("Mulher trans"), Intersex("Intersex"),
	NaoBinario("Não binário"), Neutrois("Neutrois"), Pangenero("Pangênero"),
	Transgenero("Transgênero"), Outro("Outro");
	
	public String nome;
	
	Genero(String nome) {
		this.nome = nome;
	}
}
