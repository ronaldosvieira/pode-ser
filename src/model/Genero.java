package model;

public enum Genero {
	Agenero("Ag�nero"), Androgino("Andr�gino"), Bigenero("Big�nero"),
	HomemCisgenero("Homem cisg�nero"), MulherCisgenero("Mulher cisg�nero"),
	DuploEspirito("Duplo esp�rito"), Genderqueer("Genderqueer"),
	GeneroEmduvida("G�nero em d�vida"), GeneroFluido("G�nero fluido"),
	GeneroNaoConformista("G�nero n�o conformista"), 
	Generovariante("G�nero variante"), HomemTrans("Homem trans"),
	MulherTrans("Mulher trans"), Intersex("Intersex"),
	NaoBinario("N�o bin�rio"), Neutrois("Neutrois"), Pangenero("Pang�nero"),
	Transgenero("Transg�nero"), Outro("Outro");
	
	public String nome;
	
	Genero(String nome) {
		this.nome = nome;
	}
}
