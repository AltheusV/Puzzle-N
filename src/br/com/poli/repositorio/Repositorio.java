package br.com.poli.repositorio;

public class Repositorio {
	
	public void setLayout(String layout){
		Arquivo arq = new Arquivo();
		arq.salvarLayout(layout);
	}
	
	public String getLayout(){
		Arquivo arq = new Arquivo();
		return arq.lerLayout();
	}
	
	public void armazenarRanking(String ranking){
		Arquivo arq = new Arquivo();
		arq.salvarRanking(ranking);
	}
	
	public String recuperarRanking(){
		Arquivo arq = new Arquivo();
		return arq.lerRanking();
	}
}
