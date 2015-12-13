package model;

import java.util.HashMap;
import java.util.Map;

public enum Genero {
	Agenero("Agênero", "AG"), Androgino("Andrógino", "AD"), Bigenero("Bigênero", "B"),
	HomemCisgenero("Homem cisgênero", "M"), MulherCisgenero("Mulher cisgênero", "F"),
	DuploEspirito("Duplo espírito", "DE"), Genderqueer("Genderqueer", "GQ"),
	GeneroEmduvida("Gênero em dúvida", "GD"), GeneroFluido("Gênero fluido", "GF"),
	GeneroNaoConformista("Gênero não conformista", "GC"), 
	Generovariante("Gênero variante", "GV"), HomemTrans("Homem trans", "MT"),
	MulherTrans("Mulher trans", "MT"), Intersex("Intersex", "I"),
	NaoBinario("Não binário", "NB"), Neutrois("Neutrois", "N"), Pangenero("Pangênero", "P"),
	Transgenero("Transgênero", "TG"), Transformer("Transformer", "TF"), Outro("Outro", "O");
	
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
