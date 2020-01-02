package br.com.poli.PuzzleN;
import java.util.Random;

public class Tabuleiro {
	private Bloco[][] grid;
	private int limite;

	public void setLimite(int tamanho){
		this.limite = (int) Math.sqrt(tamanho+1);	
	}

	public int getLimite(){
		return limite;
	}

	public void setGrid(){			
		this.grid = new Bloco[limite][limite];	
	}

	public Bloco[][] getGrid(){
		return grid;
	}

	public Tabuleiro(){

	}


	public Boolean executaMovimento(int linha, int coluna, String sentido) throws MovimentoInvalido{

		int valor = grid[linha][coluna].getValor();

		switch(sentido){

		case "cima":
			if(linha-1>=0 && grid[linha-1][coluna].getValor()==0){
				grid[linha-1][coluna].setValor(valor);
				grid[linha][coluna].setValor(0);
				return true;
			}
			else{
				throw new MovimentoInvalido("Movimento inválido");
			}
			//break;

		case "baixo":
			if(linha+1<grid.length && grid[linha+1][coluna].getValor()==0){
				grid[linha+1][coluna].setValor(valor);
				grid[linha][coluna].setValor(0);
				return true;
			}
			else{
				throw new MovimentoInvalido("Movimento inválido");
			}
			//break;

		case "esquerda":
			if(coluna-1>=0 && grid[linha][coluna-1].getValor()==0){
				grid[linha][coluna-1].setValor(valor);
				grid[linha][coluna].setValor(0);
				return true;
			}
			else{
				throw new MovimentoInvalido("Movimento inválido");
			}
			//break;

		case "direita":
			if(coluna+1<grid.length && grid[linha][coluna+1].getValor()==0 ){
				grid[linha][coluna+1].setValor(valor);
				grid[linha][coluna].setValor(0);
				return true;
			}
			else{
				throw new MovimentoInvalido("Movimento inválido");
			}
			//break;

		default:
			return false;
		}

	}

	public boolean isTabuleiroOrdenado(){	

		int count = 1;

		for(int i = 0; i<limite; i++){
			for(int j = 0; j<limite; j++){
				if(grid[i][j].getValor()!=count){
					if(count==limite*limite){
						return true;		// caso final, o contador é igual ao numero da matriz, ou seja, é o 0 nesta posicao
					}
					else{
						return false;		// se nao for, o numero está na posicao errada.
					}
				}
				else{
					count++;				// se forem iguais, o contador avanca para checar o proximo numero
				}
			}
		}
		return true;
	}

	public void geraTabuleiro(Dificuldade d){
		setLimite(d.getValor());

		String[] valores;

		do{
			valores = gerarValores(d.getValor()+1);
		}while(checarPossibilidade(valores));

		int count = 0;
		setGrid();

		for(int i = 0;i<limite;i++){
			for(int j = 0; j<limite; j++){
				grid[i][j] = new Bloco(retornaInteiro(valores[count]));			
				count++;
			}
		}	
	}

	// metodos auxiliares
	private String[] gerarValores(int tamanho){	// tinha que ser String, pois por padrao
		// array de inteiros era 0, ao inves de null,
		if(tamanho>0){							// que eu utilizo para verificar se ja esta preenchido ou nao. (0 TAMBEM E UM VALOR)

			String[] aux = new String[tamanho];
			int count = 0;

			while(count!=tamanho){
				String n = geraAleatorio(tamanho);
				if(!verificaRepetido(aux, n)){
					aux[count] = n;
					count++;
				}
			}
			return aux;

		}

		else{
			return null; // TODO JOGAR UMA EXCEPTION AQ 
		}
	}

	private String geraAleatorio(int tamanho){		
		Random r = new Random();				
		return Integer.toString(r.nextInt(tamanho));
	}

	private boolean verificaRepetido(String[] valores, String verificar){

		for(int i = 0; i<valores.length; i++){
			if(valores[i] == null){
				return false;
			}
			else if (valores[i].equals(verificar)){
				return true;
			}
		}

		return false;
	}
	//TODO Separar este metodo se o n for par ou impar.
	
	public boolean checarPossibilidade(String[] valores){		// verificar se o tabuleiro é resolvivel
		
		int inversoes = 0;			// numero de inversoes, quantos valores menores que o verificado existem nas casas posteriores?							
		
										// variaveis caso n seja par
		int posZero = 0;			// posicao do zero contada da ultima linha
		int countColunas = 0;			// variaveis auxiliar para passar para proxima linha
		int countLinhas = limite;
		
		for(int i = 0; i<valores.length; i++){
			
			if(countColunas == limite){				// quebra de linha
				countColunas = 0;					// tive q fazer isso para simular o grid. (ta tudo em linha no array de string)
				countLinhas--;
			}
			
			if(retornaInteiro(valores[i])!=0){					
				for(int j = i; j<valores.length; j++){		
					if(retornaInteiro(valores[j]) != 0){
						if(retornaInteiro(valores[i])>retornaInteiro(valores[j])){							
							inversoes++;							
						}
					}
				}
			}
			else{
				posZero = countLinhas;
				System.out.println("");
				System.out.println("Tentativa: ");
				System.out.println("Posicao do zero: " + posZero);
				
			}
			
			countColunas++;
		}
		System.out.println("inversoes : " + inversoes);
		//System.out.println("limite : " + limite);
		//System.out.println("Posicao do zero: " + posZero);
		if(limite%2==0){								// se n for PAR
			if(posZero%2==0){
				if(inversoes%2==0){						// se o 0 estiver em uma posicao PAR, as inversoes devem ser IMPAR
					return true; // CONTINUA NO LOOP, jogo impossivel								
				}
				else{
					return false;// SAI DO LOOP
				}
			}
			else{										// se o 0 estiver em uma posicao IMPAR, as inversoes devem ser PAR				
				if(inversoes%2==0){
					return false; // SAI DO LOOP
				}
				else{
					return true; // CONTINUA NO LOOP, jogo impossivel
				}
			}
		}
		else{
			if(inversoes%2==0){		//se n for IMPAR em nxn, as inversoes devem ser pares
				return false; 		// sai do loop, o jogo está OK
			}
			else{	
				return true;		// continua no loop, o jogo é IMPOSSIVEL.
			}
		}

	}

	private int retornaInteiro(String s){
		return Integer.parseInt(s.replaceAll("[^0-9]", ""));
	}
	
}
