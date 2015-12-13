package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Genero;
import model.Usuario;

public class ControladorUsuario {
	private static ControladorUsuario instancia;
	
	private List<Usuario> usuarios;

	private ControladorUsuario() {
	}
	
	public static ControladorUsuario getInstance() {
		if (instancia == null) instancia = new ControladorUsuario();
		
		return instancia;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void inserirUsuario(String nome, int idade, Genero genero, String ocupacao, String cep) {
		getUsuarios().add(new Usuario(getUsuarios().get(getUsuarios().size()).getId() + 1, 
				nome, idade, genero, ocupacao, cep));
	}
	public void alterarUsuario(int id, String ocupacao, String cep, Integer idade) {
		if (ocupacao != null) getUsuarios().get(id).setOcupacao(ocupacao);
		if (cep != null) getUsuarios().get(id).setCep(cep);
		if (idade != null) getUsuarios().get(id).setIdade(idade);
	}
	
	public void serializarBancoDeDados() {
		try (BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter(new File("./src/data/usuarios")))) {
			String output;
			
			for (Usuario usuario : getUsuarios()) {
				output = "";
				
				output += usuario.getId() + "|";
				output += usuario.getNome() + "|";
				output += usuario.getIdade() + "|";
				output += usuario.getGenero().getAbrev() + "|";
				output += usuario.getOcupacao() + "|";
				output += usuario.getCep() + "\n";
				
				bwUsuarios.write(output);
			}
		} catch (IOException e) {
			System.out.println("Erro ao serializar base de dados: " + e.getMessage());
		}
	}

	public void deserializarBancoDeDados() throws FileNotFoundException, IOException {
		try (BufferedReader brUsuarios = new BufferedReader(new FileReader(new File("./src/data/usuarios")))) {
			String linha = null;
			Usuario tempUsuario;
			String[] linhaSplit;
			
			while ((linha = brUsuarios.readLine()) != null) {
				linhaSplit = linha.split("[|]");
				
				tempUsuario = new Usuario(Integer.parseInt(linhaSplit[0]), linhaSplit[1],
						Integer.parseInt(linhaSplit[2]), Genero.get(linhaSplit[3]), 
						linhaSplit[4], linhaSplit[5]);
				
				getUsuarios().add(tempUsuario);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro: arquivo não encontrado: " + e.getMessage());
		}
	}
}