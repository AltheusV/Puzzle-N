package br.com.poli.application;

import br.com.poli.repositorio.Repositorio;
import br.com.poli.view.Layout;
import br.com.poli.view.Tela;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;



public class PuzzleN extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/br/com/poli/view/Menu.fxml"));
			Tela tela = new Tela();			
			Repositorio r = new Repositorio();
			AnchorPane fundo = tela.gerarTela(Layout.gameLayout(r.getLayout()), root);
			Scene scene = new Scene(fundo);
			primaryStage.setTitle("Puzzle-N");
			primaryStage.getIcons().add(new Image("/br/com/poli/resources/a-void.png"));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
