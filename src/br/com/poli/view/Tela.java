package br.com.poli.view;

import java.io.IOException;

import br.com.poli.PuzzleN.Puzzle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class Tela {

	public AnchorPane gerarTela(Layout layout, Parent root){
		AnchorPane tela = (AnchorPane) root;

		Image image = new Image(layout.getUrl());
		BackgroundSize size = new BackgroundSize(tela.getWidth(),tela.getHeight(),true ,true,true,true);
		BackgroundImage backImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, size);
		Background back = new Background(backImage);
		tela.setBackground(back);

		return tela;
	}


	public void mudarTela(ActionEvent event,Layout layout, String caminho, Puzzle puzzle){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(caminho));
			Parent root = (Parent) fxmlLoader.load();
			GameController controller = fxmlLoader.<GameController>getController();
			controller.setPuzzle(puzzle);		
			Scene scene = new Scene(gerarTela(layout, root));									
			Node node = (Node)event.getSource();							
			Stage stage = (Stage)node.getScene().getWindow();				
			stage.setScene(scene);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	//voltar?
	public void mudarTela(ActionEvent event,Layout layout, String caminho){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(caminho));
			Parent root = (Parent) fxmlLoader.load();		
			Scene scene = new Scene(gerarTela(layout, root));									
			Node node = (Node)event.getSource();							
			Stage stage = (Stage)node.getScene().getWindow();				
			stage.setScene(scene);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	//	public void mostrarTela(ActionEvent event, String caminho, String sala, String bloco){
	//		try {
	//			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(caminho));	// funciona parecido a anterior,
	//			Parent root = (Parent) fxmlLoader.load();									// entretanto, ela da um set em duas informacoes 
	//			AlteracaoController controller = fxmlLoader.<AlteracaoController>getController();	// no proximo controller, que serao utilizadas
	//			controller.setSala(sala);															// * classe AlteracaoController
	//			controller.setBloco(bloco);
	//			Scene scene = new Scene(root);
	//			Node node = (Node)event.getSource();
	//			Stage stage = (Stage)node.getScene().getWindow();
	//			stage.setScene(scene);
	//		} catch (IOException e) {
	//			System.out.println(e.getMessage());
	//		}
	//	}
}
