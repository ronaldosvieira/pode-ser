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
import model.Item;
import model.Nota;

public class ControladorItem {
	private static ControladorItem instancia;
	private List<Item> itens;

	private ControladorItem() {
		this.itens = new ArrayList<>();
		
		deserializarBancoDeDados();
	}
	
	public static ControladorItem getInstance() {
		if (instancia == null) instancia = new ControladorItem();
		
		return instancia;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void inserirFilme(String nome, Date dataEstreia, Date dataEstreiaVideo,
			String imdbUrl, List<Categoria> categorias) {
		getItens().add(new Filme(((Filme) getItens().get(getItens().size())).getId(), 
				nome, dataEstreia, dataEstreiaVideo, imdbUrl, categorias));
	}
	
	public void serializarBancoDeDados() {
		try (BufferedWriter bwItens = new BufferedWriter(new FileWriter(new File("./src/data/itens")));
				BufferedWriter bwNotas = new BufferedWriter(new FileWriter(new File("./src/data/notas")))) {
			String output;
			
			for (Item item : getItens()) {
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
			
			for (Item item : itens) {
				output = "";
				Filme filme = (Filme) item;
				
				for (Nota nota : filme.getNotas()) {
					output += nota.getUsuarioId() + "\t";
					output += filme.getId() + "\t";
					output += nota.getNota() + "\t";
					output += nota.getData().getTime();
					
					bwNotas.write(output);
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao serializar base de dados: " + e.getMessage());
		}
	}

	public void deserializarBancoDeDados() {
		try (BufferedReader brItens = new BufferedReader(new FileReader(new File("./src/data/u.item")));
				BufferedReader brNotas = new BufferedReader(new FileReader(new File("./src/data/u.data")))) {
			String linha = null;
			Filme tempFilme;
			String[] linhaSplit;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			while ((linha = brItens.readLine()) != null) {
				linhaSplit = linha.split("[|]");
				Date dataEstreia, dataEstreiaVideo;
				List<Categoria> categorias = new ArrayList<Categoria>();
				
				if (!linhaSplit[2].equals("")) dataEstreia = dateFormat.parse(linhaSplit[2]);
				else 
				dataEstreia = null;
				
				if (!linhaSplit[3].equals("")) dataEstreiaVideo = dateFormat.parse(linhaSplit[3]);
				else 
				dataEstreiaVideo = null;
				
				for (int i = 0; i < 19; ++i) {
					if (linhaSplit[5 + i].equals("1")) {
						categorias.add(Categoria.get(i));
					}
				}
				
				tempFilme = new Filme(Integer.parseInt(linhaSplit[0]), 
						linhaSplit[1], dataEstreia, dataEstreiaVideo, 
						linhaSplit[4], categorias);
				
				getItens().add(tempFilme);
			}
			
			while ((linha = brNotas.readLine()) != null) {
				linhaSplit = linha.split("\t");
				
				Filme filme = ((Filme) this.itens.get(Integer.parseInt(linhaSplit[1]) - 1)); 
				filme.inserirNota(new Nota(Integer.parseInt(linhaSplit[0]),
						Integer.parseInt(linhaSplit[2]), new Date(Long.parseLong(linhaSplit[3]))));
				
				this.itens.set(Integer.parseInt(linhaSplit[1]) - 1, filme);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro: arquivo não encontrado: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Erro: não foi possï¿½vel ler data: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}