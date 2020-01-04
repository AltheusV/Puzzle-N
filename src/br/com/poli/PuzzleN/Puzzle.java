package br.com.poli.PuzzleN;
import java.util.Calendar;

import br.com.poli.Jogador.Jogador;

public class Puzzle {
	private Jogador jogador;
	private Tabuleiro gridPuzzle;
	private int quantidadeMovimentos;
	private boolean venceu;
	private Calendar tempo;
	private Dificuldade dificuldade;
	
	
	public Puzzle(Tabuleiro t){
		this.gridPuzzle= t ;
	}
	
	
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public Tabuleiro getGridPuzzle() {
		return gridPuzzle;
	}
	public void setGridPuzzle(Tabuleiro gridPuzzle) {
		this.gridPuzzle = gridPuzzle;
	}
	public int getQuantidadeMovimentos() {
		return quantidadeMovimentos;
	}
	public void setQuantidadeMovimentos(int quantidadeMovimentos) {
		this.quantidadeMovimentos = quantidadeMovimentos;
	}
	public boolean isVenceu() {
		return venceu;
	}
	public void setVenceu(boolean venceu) {
		this.venceu = venceu;
	}
	public Calendar getTempo() {
		return tempo;
	}
	public void setTempo(Calendar tempo) {
		this.tempo = tempo;
	}
	public Dificuldade getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
	
	
	
	public boolean isFimDeJogo(){	
		return gridPuzzle.isTabuleiroOrdenado();
	}
	
	
	public void iniciaPartida(){
		setQuantidadeMovimentos(0);
		tempo = Calendar.getInstance();
		setVenceu(false);
		gridPuzzle.geraTabuleiro(dificuldade);
		
	}
}
