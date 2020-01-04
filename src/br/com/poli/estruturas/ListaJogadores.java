package br.com.poli.estruturas;

import br.com.poli.Jogador.Info;

public class ListaJogadores {
	
	private Info playerInfo;
	private ListaJogadores nextPlayer;
	
	public ListaJogadores(Info playerInfo, ListaJogadores nextPlayer) {
		this.playerInfo = playerInfo;
		this.nextPlayer = nextPlayer;
	}
	
	public Info getPlayerInfo() {
		return playerInfo;
	}
	public void setPlayerInfo(Info playerInfo) {
		this.playerInfo = playerInfo;
	}
	public ListaJogadores getNextPlayer() {
		return nextPlayer;
	}
	public void setNextPlayer(ListaJogadores nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	
	
	
}
