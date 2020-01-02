package br.com.poli.PuzzleN;

public class PuzzleFacil extends Puzzle {

	public PuzzleFacil(Tabuleiro t) {
		super(t);
		setDificuldade(Dificuldade.FACIL);
	}
	
	public void resolveTabuleiro() throws TempoExcedido {
		throw new TempoExcedido("N alto demais! Muito processamento necessário para estas interações");
		//TODO ultima parte
	}
	
	public void geraTabuleiro(){
		getGridPuzzle().geraTabuleiro(getDificuldade());
	}

}
