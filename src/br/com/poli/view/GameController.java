package br.com.poli.view;

import br.com.poli.Jogador.CalculaScore;
import br.com.poli.Jogador.Info;
import br.com.poli.Jogador.Jogador;
import br.com.poli.PuzzleN.MovimentoInvalido;
import br.com.poli.PuzzleN.Puzzle;
import br.com.poli.estruturas.ListaJogadores;
import br.com.poli.repositorio.Repositorio;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameController {
	
	@FXML
	private TilePane grid;
	@FXML
	private TextField nome;
	@FXML
	private TextField player;
	@FXML
	private TextField moves;
	@FXML
	private TextField time;
	@FXML
	private VBox container;
	@FXML
	private VBox endContainer;
	@FXML
	private ImageView gif;
	@FXML
	private Button voltar;
	@FXML
	private Button solver;

	private int size;
	private Button[] btn;
	private Puzzle puzzle;
	
	private int zero;		// posicao do botao que contem o 0
								
	private int x;			// variaveis para a posicao da peca selecionada
	private int y;

	private String layout;
	private Timeline clock;
	private int count;		// temporizador
	
	public void initialize(){
		Repositorio r = new Repositorio();
		layout = r.getLayout();
		
		if(layout.equals("CLASSIC")){
			container.setLayoutY(22.0);
		}
		else if (layout.equals("FUTURISTIC")){
			container.setLayoutY(42.0);
		}
		else if(layout.equals("RETRO")){
			container.setLayoutY(22.0);
			gif.setImage(new Image("/br/com/poli/resources/retroGif.gif"));
		}
		
		container.getStylesheets().add("/br/com/poli/view/Button.css");
		container.getStyleClass().add(layout);
		container.setStyle("-fx-background-color: transparent");
		
		endContainer.getStylesheets().add("/br/com/poli/view/Button.css");
		endContainer.getStyleClass().add(layout);
		endContainer.setStyle("-fx-background-color: transparent");
		
		player.getStylesheets().add("/br/com/poli/view/Button.css");
		player.getStyleClass().add(layout);
		
		moves.getStylesheets().add("/br/com/poli/view/Button.css");
		moves.getStyleClass().add(layout);
		
		time.getStylesheets().add("/br/com/poli/view/Button.css");
		time.getStyleClass().add(layout);
		
		voltar.getStylesheets().add("/br/com/poli/view/Button.css");
		voltar.getStyleClass().add(layout);
		 
		solver.getStylesheets().add("/br/com/poli/view/Button.css");
		solver.getStyleClass().add(layout);
		
	}

	public void getNome(KeyEvent tecla){
		//Jogador
		if(tecla.getCode()== KeyCode.ENTER && !nome.getText().isEmpty()){
			Jogador player = new Jogador(nome.getText());
			puzzle.setJogador(player);
			this.player.setText(nome.getText());
			container.setVisible(true);
			voltar.setVisible(true);
			solver.setVisible(true);
			geraGame();
			
			count = 0;
			
		    clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
		        time.setText(count + "(s)");
		        count++;
		    }),
		         new KeyFrame(Duration.seconds(1))
		    );
		    
		    Timeline moves = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
		        this.moves.setText(Integer.toString(puzzle.getQuantidadeMovimentos()));
		    }),
		         new KeyFrame(Duration.seconds(0.1))
		    );
		    
		    clock.setCycleCount(Animation.INDEFINITE);
		    clock.play();
		    moves.setCycleCount(Animation.INDEFINITE);
		    moves.play();
		    
		    
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
			puzzle.setQuantidadeMovimentos(puzzle.getQuantidadeMovimentos()+1);

			if(puzzle.isFimDeJogo()){
				finalizarJogo();
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
	
	private void finalizarJogo(){
		rank(null , new Info(player.getText(), CalculaScore.pontos(puzzle), count));
	}
	
	private ListaJogadores rank(ListaJogadores lista, Info novo){
		
		if(lista == null)
			return new ListaJogadores(novo, null);
		else{
			if(novo.getScore()<lista.getPlayerInfo().getScore())
				return new ListaJogadores(novo, lista);
			else{
				return new ListaJogadores(lista.getPlayerInfo(), rank(lista.getNextPlayer(), novo));
			}
		}
	}


	public void voltar(ActionEvent event){
		Tela t = new Tela();
		t.mudarTela(event, Layout.gameLayout(layout), "/br/com/poli/view/Menu.fxml");

	}
	
	public void solve(ActionEvent event){
		grid.setVisible(false);
		clock.stop();
	}
}
