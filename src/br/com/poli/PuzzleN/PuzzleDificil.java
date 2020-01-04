package br.com.poli.PuzzleN;

public class PuzzleDificil extends Puzzle {

	public PuzzleDificil(Tabuleiro t) {
		super(t);
		setDificuldade(Dificuldade.HARD);
	}
	
	public void resolveTabuleiro() throws TempoExcedido {
		throw new TempoExcedido("N alto demais! Muito processamento necessário para estas interações");
		//TODO ultima parte
	}
	
	public void geraTabuleiro(){
		getGridPuzzle().geraTabuleiro(getDificuldade());
	}

}
