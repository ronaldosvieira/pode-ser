package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Categoria;
import model.Filme;
import model.Genero;
import model.Item;
import model.Usuario;

public class ControladorGeral {
	private static ControladorGeral instancia;
	
	List<Usuario> usuarios;
	List<Item> itens;

	private ControladorGeral() {
		usuarios = new ArrayList<>();
		itens = new ArrayList<>();
		
		try {
			deserializarBancoDeDados();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ControladorGeral getInstance() {
		if (instancia == null) instancia = new ControladorGeral();
		
		return instancia;
	}
	
	public void serializarBancoDeDados() {
		try (BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter(new File("./src/data/usuarios")));
				BufferedWriter bwItens = new BufferedWriter(new FileWriter(new File("./src/data/itens")))) {
			String output;
			
			for (Usuario usuario : usuarios) {
				output = "";
				
				output += usuario.getId() + "|";
				output += usuario.getNome() + "|";
				output += usuario.getIdade() + "|";
				output += usuario.getGenero().getAbrev() + "|";
				output += usuario.getOcupacao() + "|";
				output += usuario.getCep() + "\n";
				
				bwUsuarios.write(output);
			}
			
			for (Item item : itens) {
				output = "";
				
				output += ((Filme) item).getId() + "|";
				output += ((Filme) item).getNome() + "|";
				
				if (((Filme) item).getDataEstreia() != null) {
					output += new SimpleDateFormat("dd-MMM-yyyy").format(((Filme) item).getDataEstreia()) + "|";
				} else output += "|";
				
				if (((Filme) item).getDataEstreiaVideo() != null) {
					output += new SimpleDateFormat("dd-MMM-yyyy").format(((Filme) item).getDataEstreiaVideo()) + "|";
				} else output += "|";
				
				output += ((Filme) item).getImdbUrl() + "|";
				
				for (int i = 0; i < 19; ++i) {
					if (((Filme) item).getCategorias().contains(Categoria.get(i))) {
						output += "1";
					} else {
						output += "0";
					}
					
					if (i < 18) output += "|";
				}
				
				output += "\n";
				
				bwItens.write(output);
			}
		} catch (IOException e) {
			System.out.println("Erro ao serializar base de dados: " + e.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		ControladorGeral cg = ControladorGeral.getInstance();
		
		cg.serializarBancoDeDados();
	}

	public void deserializarBancoDeDados() throws FileNotFoundException, IOException {
		try (BufferedReader brUsuarios = new BufferedReader(new FileReader(new File("./src/data/usuarios")));
				BufferedReader brItens = new BufferedReader(new FileReader(new File("./src/data/u.item")))) {
			String linha = null;
			Usuario tempUsuario;
			Filme tempFilme;
			String[] linhaSplit;
			
			usuarios = new ArrayList<>();
			
			while ((linha = brUsuarios.readLine()) != null) {
				linhaSplit = linha.split("[|]");
				
				tempUsuario = new Usuario(Integer.parseInt(linhaSplit[0]), linhaSplit[1],
						Integer.parseInt(linhaSplit[2]), Genero.get(linhaSplit[3]), 
						linhaSplit[4], linhaSplit[5]);
				
				usuarios.add(tempUsuario);
			}
			
			while ((linha = brItens.readLine()) != null) {
				linhaSplit = linha.split("[|]");
				Date dataEstreia, dataEstreiaVideo;
				List<Categoria> categorias = new ArrayList<Categoria>();
				
				if (!linhaSplit[2].equals("")) dataEstreia = new SimpleDateFormat("dd-MMM-yyyy").parse(linhaSplit[2]);
				else dataEstreia = null;
				
				if (!linhaSplit[3].equals("")) dataEstreiaVideo = new SimpleDateFormat("dd-MMM-yyyy").parse(linhaSplit[3]);
				else dataEstreiaVideo = null;
				
				for (int i = 0; i < 19; ++i) {
					if (linhaSplit[5 + i].equals("1")) {
						categorias.add(Categoria.get(i));
					}
				}
				
				tempFilme = new Filme(Integer.parseInt(linhaSplit[0]), 
						linhaSplit[1], dataEstreia, dataEstreiaVideo, 
						linhaSplit[4], categorias);
				
				itens.add(tempFilme);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro: arquivo não encontrado: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Erro: não foi possível ler data: " + e.getMessage());
		}
	}
}