package br.com.poli.PuzzleN;

public enum Dificuldade {
	FACIL(8),MEDIO(15),DIFICIL(24), INSANO(0);

	private int valor;
	
	Dificuldade(int valor){				// por isso q o construtor � privado, ele j� inicia com os
		this.valor = valor;				// valores
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public void setValor(int valor){
		this.valor = valor;
	}
}
