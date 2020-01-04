package br.com.poli.Jogador;

import java.util.Calendar;

import br.com.poli.PuzzleN.Puzzle;

public class CalculaScore
{


	public static int pontos(Puzzle partida) {
		
		int pontuacao = 1000 * partida.getGridPuzzle().getLimite();
		
		int tempoTotal = (int) (Calendar.getInstance().getTimeInMillis() - partida.getTempo().getTimeInMillis());
		
		tempoTotal = tempoTotal/1000;
		
		pontuacao = (pontuacao - tempoTotal - partida.getQuantidadeMovimentos());
		System.out.println(pontuacao);
		return pontuacao;
	}

}
