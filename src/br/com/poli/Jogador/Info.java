package br.com.poli.Jogador;

public class Info {
	private String nome;
	private int score;
	private int tempo;
	
		
	public Info(String nome, int score, int tempo) {
		this.nome = nome;
		this.score = score;
		this.tempo = tempo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
}
