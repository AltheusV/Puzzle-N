package br.com.poli.repositorio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Arquivo {

	private static String Read(String caminho){
		String conteudo = "";
		try{
			FileReader arq = new FileReader(caminho);		 
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			try{
				linha = lerArq.readLine();
				while(linha!=null){
					conteudo += linha;
					linha = lerArq.readLine();
				}
				arq.close();
			} catch (IOException ex) {
				conteudo = "ERRO31: Não foi possível ler o arquivo.";
			}
		} catch (FileNotFoundException ex){
			conteudo = "ERRO32: arquivo não encontrado.";
		}
		return conteudo;
	}

	private static boolean Write(String caminho, String texto){
		try{
			FileWriter arq = new FileWriter(caminho, false);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.print(texto);
			gravarArq.close();
			return true;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void salvarLayout(String layout){
		String arq = "Layout.txt";
		Write(arq,layout);
	}

	public String lerLayout(){
		String arq = "Layout.txt";
		String layout = Read(arq);
		
		if(layout.contains("ERRO")){
			salvarLayout("RETRO");
			return "RETRO";
		}
		return layout;
	}
}
