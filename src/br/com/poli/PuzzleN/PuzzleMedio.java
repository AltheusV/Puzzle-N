package br.com.poli.PuzzleN;

public class PuzzleMedio extends Puzzle {

	public PuzzleMedio(Tabuleiro t) {
		super(t);
		setDificuldade(Dificuldade.MEDIO);
	}
	
	public void resolveTabuleiro() throws TempoExcedido {
		throw new TempoExcedido("N alto demais! Muito processamento necessário para estas interações");
		//TODO ultima parte
	}
	
	public void geraTabuleiro(){
		getGridPuzzle().geraTabuleiro(getDificuldade());
	}
}
