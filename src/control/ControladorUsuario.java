package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Filme;
import model.Genero;
import model.Item;
import model.Nota;
import model.Usuario;

public class ControladorUsuario {
	private static ControladorUsuario instancia;
	
	private List<Usuario> usuarios;

	private ControladorUsuario() {
		this.usuarios = new ArrayList<>();
		
		try {
			deserializarBancoDeDados();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ControladorUsuario getInstance() {
		if (instancia == null) instancia = new ControladorUsuario();
		
		return instancia;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void inserirUsuario(String nome, int idade, Genero genero, String ocupacao, String cep) {
		getUsuarios().add(new Usuario(getUsuarios().get(getUsuarios().size() - 1).getId() + 1, 
				nome, idade, genero, ocupacao, cep));
		serializarBancoDeDados();
	}
	
	public void alterarUsuario(int id, String ocupacao, String cep, Integer idade) {
		Usuario temp = getUsuarios().get(id - 1);
		
		if (ocupacao != null) temp.setOcupacao(ocupacao);
		if (cep != null) temp.setCep(cep);
		if (idade != null) temp.setIdade(idade);
		
		this.usuarios.set(id - 1, temp);
	}
	
	public boolean removerUsuario(int id) {
		List<Item> itens = ControladorItem.getInstance().getItens();
		
		for (Item item : itens) {
			List<Nota> notas = ((Filme) item).getNotas();
			List<Integer> assistidoPor = ((Filme) item).getAssistidoPor();
			
			for (Nota nota : notas) {
				if (nota.getUsuarioId() == id) return false;
			}
			
			for (Integer usuarioId : assistidoPor) {
				if (usuarioId == id) return false;
			}
		}
		
		Usuario usuario = this.usuarios.get(id - 1);
		usuario.desativarUsuario();
		
		this.usuarios.set(id - 1, usuario);
		
		return true;
	}

	public List<Filme> obterFilmesAssistidos(int usuarioId) {
		List<Filme> filmesAssistidos = new ArrayList<>();
		
		for (Item item : ControladorItem.getInstance().getItens()) {
			List<Integer> usuarios = ((Filme) item).getAssistidoPor();
			
			if (usuarios.size() > 0) {
				for (Integer id : usuarios) {
					if (id == usuarioId) filmesAssistidos.add((Filme) item);
				}
			}
		}
		
		return filmesAssistidos;
	}
	
	public List<Filme> obterFilmesAvaliados(int usuarioId) {
		List<Filme> filmesAvaliados = new ArrayList<>();
		
		for (Item item : ControladorItem.getInstance().getItens()) {
			List<Nota> notas = ((Filme) item).getNotas();
			for (int i = 0; i < notas.size(); ++i) {
				if (notas.get(i).getUsuarioId() == usuarioId) {
					filmesAvaliados.add((Filme) item);
					break;
				}
			}
		}
		
		return filmesAvaliados;
	}
	
	public void serializarBancoDeDados() {
		try {
			File fileUsuarios = new File("./src/data/usuarios");
			
			fileUsuarios.delete();
			
			BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter(fileUsuarios));
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
			
			bwUsuarios.close();
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

	public boolean assistiu(int usuarioId, Filme filme) {
		List<Filme> filmesAssistidos = obterFilmesAssistidos(usuarioId);
		
		for (Filme filme2 : filmesAssistidos) {
			if (filme2.getId() == filme.getId()) return true;
		}
		
		return false;
	}
	
	public boolean avaliou(int usuarioId, Filme filme) {
		List<Filme> filmesAvaliados = obterFilmesAvaliados(usuarioId);
		
		for (Filme filme2 : filmesAvaliados) {
			if (filme2.getId() == filme.getId()) return true;
		}
		
		return false;
	}
}