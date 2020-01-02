package br.com.poli.view;

import java.io.IOException;

import br.com.poli.PuzzleN.PuzzleDificil;
import br.com.poli.PuzzleN.PuzzleFacil;
import br.com.poli.PuzzleN.PuzzleMedio;
import br.com.poli.PuzzleN.Tabuleiro;
import br.com.poli.repositorio.Repositorio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MenuController {

	@FXML
	private Button btnEasy;
	@FXML
	private Button btnNormal;
	@FXML
	private Button btnHard;
	@FXML
	private Button btnInsane;

	private Layout layoutAtual;

	public void initialize(){
		Repositorio r = new Repositorio();
		String layout = r.getLayout();
		layoutAtual = Layout.gameLayout(layout);
		

		btnEasy.getStylesheets().add("/br/com/poli/view/Button.css");
		btnNormal.getStylesheets().add("/br/com/poli/view/Button.css");
		btnHard.getStylesheets().add("/br/com/poli/view/Button.css");
		btnInsane.getStylesheets().add("/br/com/poli/view/Button.css");
		btnEasy.getStyleClass().add(layout);
		btnNormal.getStyleClass().add(layout);
		btnHard.getStyleClass().add(layout);
		btnInsane.getStyleClass().add(layout);
		//System.out.println(javafx.scene.text.Font.getFamilies());
	}

	public void easy(ActionEvent event){
		Tela tela = new Tela();
		PuzzleFacil puzzle = new PuzzleFacil(new Tabuleiro());
		tela.mudarTela(event,layoutAtual, "/br/com/poli/view/Game.fxml", puzzle);
	}
	
	public void normal(ActionEvent event){
		Tela tela = new Tela();
		PuzzleMedio puzzle = new PuzzleMedio(new Tabuleiro());
		tela.mudarTela(event,layoutAtual, "/br/com/poli/view/Game.fxml", puzzle);
	}
	
	public void hard(ActionEvent event){
		Tela tela = new Tela();
		PuzzleDificil puzzle = new PuzzleDificil(new Tabuleiro());
		tela.mudarTela(event,layoutAtual, "/br/com/poli/view/Game.fxml", puzzle);
	}
}
