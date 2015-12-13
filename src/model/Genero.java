package model;

import java.util.HashMap;
import java.util.Map;

public enum Genero {
	Agenero("Ag�nero", "AG"), Androgino("Andr�gino", "AD"), Bigenero("Big�nero", "B"),
	HomemCisgenero("Homem cisg�nero", "M"), MulherCisgenero("Mulher cisg�nero", "F"),
	DuploEspirito("Duplo esp�rito", "DE"), Genderqueer("Genderqueer", "GQ"),
	GeneroEmduvida("G�nero em d�vida", "GD"), GeneroFluido("G�nero fluido", "GF"),
	GeneroNaoConformista("G�nero n�o conformista", "GC"), 
	Generovariante("G�nero variante", "GV"), HomemTrans("Homem trans", "MT"),
	MulherTrans("Mulher trans", "MT"), Intersex("Intersex", "I"),
	NaoBinario("N�o bin�rio", "NB"), Neutrois("Neutrois", "N"), Pangenero("Pang�nero", "P"),
	Transgenero("Transg�nero", "TG"), Transformer("Transformer", "TF"), Outro("Outro", "O");
	
	public String nome;
	public String abreviatura;
	
	private static final Map<String, Genero> lookup = new HashMap<>();

	Genero(String nome, String abreviatura) {
		this.nome = nome;
		this.abreviatura = abreviatura;
	}
	
	public String getGenero() {
		return this.nome;
	}
	
	public String getAbrev() {
		return this.abreviatura;
	}
	
	static {
	    for(Genero g : Genero.values())
	        lookup.put(g.getAbrev(), g);
	}
	
	public static Genero get(String abreviatura) { 
	     return lookup.get(abreviatura); 
	}
}
