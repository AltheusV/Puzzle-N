package br.com.poli.view;

import br.com.poli.Jogador.Jogador;
import br.com.poli.PuzzleN.MovimentoInvalido;
import br.com.poli.PuzzleN.Puzzle;
import br.com.poli.repositorio.Repositorio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

public class GameController {
	@FXML
	private TilePane grid;
	@FXML
	private TextField nome;

	private int size;
	private Button[] btn;
	private Puzzle puzzle;
	
	private int zero;		// posicao do botao que contem o 0
								
	private int x;			// variaveis para a posicao da peca selecionada
	private int y;

	private String layout;
	
	public void initialize(){
		Repositorio r = new Repositorio();
		layout = r.getLayout();
	}

	public void getNome(KeyEvent tecla){
		//Jogador
		if(tecla.getCode()== KeyCode.ENTER && !nome.getText().isEmpty()){
			Jogador player = new Jogador(nome.getText());
			puzzle.setJogador(player);
			geraGame();
		}

	}
	//TODO reorganizar geraGame
	public void geraGame(){
		grid.setVisible(true);
		nome.setVisible(false);

		puzzle.iniciaPartida();

		size = puzzle.getGridPuzzle().getLimite();

		grid.setPrefColumns(size);
		grid.setPrefRows(size);

		int count = 2;
		int pct = 400;

		do{
			pct = pct-50;
			count++;

			if(count == 9){
				pct = 70;
				break;
			}
		}
		while(count<size);

		count = 0;
		btn = new Button[(size*size)];
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				Button b = new Button(Integer.toString(puzzle.getGridPuzzle().getGrid()[i][j].getValor()));
				b.getStylesheets().add("/br/com/poli/view/Button.css");
				b.getStyleClass().add(layout);
				b.setStyle("-fx-font-size: " + pct + "%");
				b.setPrefSize(400/size, 400/size);
				btn[count] = b;
				btn[count].setOnAction(eventM);
				btn[count].setOnKeyReleased(eventT);
				if(puzzle.getGridPuzzle().getGrid()[i][j].getValor()==0){
					btn[count].setVisible(false);
					zero = count;
				}
				grid.getChildren().addAll(b);
				count++;

			}
		}

	}

	EventHandler<ActionEvent> eventM = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) 
		{ 
			Button atual = (Button) e.getSource();
			realizarMovimento(atual);
		} 
	}; 

	EventHandler<KeyEvent> eventT = new EventHandler<KeyEvent>(){
		public void handle(KeyEvent e) {	
			switch(e.getCode()){

			case UP :
				if(zero+size< btn.length){
					Button atual = btn[zero+size];
					realizarMovimento(atual);
				}
				
				break;

			case DOWN: 
				if(zero-size >= 0){
					Button atual = btn[zero-size];
					realizarMovimento(atual);
				}
				break;

			case LEFT :
				if(zero+1 < btn.length){
					Button atual = btn[zero+1];
					realizarMovimento(atual);
				}
				break;

			case RIGHT: 
				if(zero-1 >= 0){
					Button atual = btn[zero-1];
					realizarMovimento(atual);
				}
				break;

			default:
				break;
			}
		}		
	};

	public Puzzle getPuzzle() {
		return puzzle;
	}
	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	private void realizarMovimento(Button atual){
		if(movimentarPeca(atual)){
			btn[zero].setVisible(true);
			btn[zero].setText(atual.getText());
			zero = grid.getChildren().indexOf(atual);  
			atual.setText("0");
			atual.setVisible(false);

			if(puzzle.isFimDeJogo()){
				//TODO gerar tela de fim de jogo.	
			}
		}
	}

	private void getCoordinates(Button b){
		for(int i = 0; i<size; i++){
			for(int j = 0; j<size; j++){
				if(puzzle.getGridPuzzle().getGrid()[i][j].getValor()==Integer.parseInt(b.getText())){
					x = i;
					y = j;
					return;
				}
			}
		}
	}


	private String getSentido(){

		if(x-1>=0 && puzzle.getGridPuzzle().getGrid()[x-1][y].getValor()==0)
			return "cima";
		else if(x+1<size && puzzle.getGridPuzzle().getGrid()[x+1][y].getValor()==0)
			return "baixo";
		else if(y-1>=0 && puzzle.getGridPuzzle().getGrid()[x][y-1].getValor()==0)
			return "esquerda";
		else if(y+1<size && puzzle.getGridPuzzle().getGrid()[x][y+1].getValor()==0)
			return "direita";
		else 
			return "null";

	}

	private boolean movimentarPeca(Button b){
		getCoordinates(b);
		try {
			if(puzzle.getGridPuzzle().executaMovimento(x, y, getSentido())){
				return true;
			}
		} catch (MovimentoInvalido e) {
			e.printStackTrace();
		}
		return false;
	}


	public void voltar(ActionEvent event){
		Tela t = new Tela();
		t.mudarTela(event, Layout.gameLayout(layout), "/br/com/poli/view/Menu.fxml");

	}
}
