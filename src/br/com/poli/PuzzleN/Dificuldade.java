package br.com.poli.PuzzleN;

public enum Dificuldade {
	EASY(8),NORMAL(15),HARD(24), NSANE(0);  

	private int valor;
	
	Dificuldade(int valor){				// por isso q o construtor e privado, ele ja inicia com os
		this.valor = valor;				// valores
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public void setValor(int valor){
		this.valor = valor;
	}
}
