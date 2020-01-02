package br.com.poli.PuzzleN;

public class PuzzleInsano extends Puzzle {
	
	private int tamanho;
	
	public PuzzleInsano(Tabuleiro t, int k) {
		super(t);
		
		this.tamanho = (k*k)-1 ;
		Dificuldade d = Dificuldade.INSANO;
		d.setValor(tamanho);
		setDificuldade(Dificuldade.INSANO);
	}
	
	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public void resolveTabuleiro() throws TempoExcedido {
		throw new TempoExcedido("N alto demais! Muito processamento necessário para estas interações");
		//TODO ultima parte
	}
	
	public void geraTabuleiro(){
		getGridPuzzle().geraTabuleiro(getDificuldade());
	}
}
