package br.com.poli.PuzzleN;

public class MovimentoInvalido extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 	// o que � isso? Serio

	public MovimentoInvalido(String mensagem){
		super(mensagem);
	}
}
