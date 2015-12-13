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

import model.Genero;
import model.Item;
import model.Usuario;

public class ControladorGeral {
	private static ControladorGeral instancia;
	
	List<Usuario> usuarios;
	List<Item> items;

	private ControladorGeral() {
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
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/data/usuarios")))) {
			String output;
			
			for (Usuario usuario : usuarios) {
				output = "";
				
				output += usuario.getId() + "|";
				output += usuario.getNome() + "|";
				output += usuario.getIdade() + "|";
				output += usuario.getGenero().getAbrev() + "|";
				output += usuario.getOcupacao() + "|";
				output += usuario.getCep() + "\n";
				
				bw.write(output);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void main(String[] args) {
		ControladorGeral cg = ControladorGeral.getInstance();
	}

	public void deserializarBancoDeDados() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("./src/data/usuarios")))) {
			String linha = null;
			Usuario temp;
			String[] linhaSplit;
			
			usuarios = new ArrayList<>();
			
			while ((linha = br.readLine()) != null) {
				linhaSplit = linha.split("[|]");
				
				temp = new Usuario(Integer.parseInt(linhaSplit[0]), linhaSplit[1],
						Integer.parseInt(linhaSplit[2]), Genero.get(linhaSplit[3]), 
						linhaSplit[4], linhaSplit[5]);
				
				usuarios.add(temp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro: arquivo não encontrado:" + e.getMessage());
		}
	}
}