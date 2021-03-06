package tp.pr3.logica;

import java.util.Random;

public class RulesInverse implements GameRules{

	@Override
	public boolean addNewCellAt(Board board,Position position ,Random rand) {
		int aux;
		if (board.getBoard()[position.getFila()][position.getColumna()] == null) {//si esta vacia 
			aux=probabilidadInv(rand);//genero un valor 2 o 4, valor aleatorio
			board.getBoard()[position.getFila()][position.getColumna()] = new Cell(aux);//se crea celula con el valor 2 o 4 segun aux en una posicion del tablero
			return true;
		} else
			return false;
	}

	@Override
	public int merge(Cell self, Cell other) {
		int aux=0;
		if(this.canMergeNeighbours(self, other)){
			aux=self.getValue()/2;
			self.setValue(aux);
			return score(self.getValue());
		}
		return 0;
	}

	@Override
	public int getWinValue(Board board) {
		int highest=2048;
		for(int f=0;f<board.getSize();f++){
			for(int c=0;c<board.getSize();c++){
				if(board.getBoard()[f][c]!=null){
					if(board.getBoard()[f][c].getValue()<highest){
						highest=board.getBoard()[f][c].getValue();
					}
				}	
			}
		}
		return highest;
	}

	@Override
	public boolean win(Board board) {
		for(int i=0;i<board.getSize();i++) {
			for(int j=0;j<board.getSize();j++) {
				if (board.getBoard()[i][j]!=null) {
					if (board.getBoard()[i][j].getValue()==1){
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Genera un numero aleatorio
	 */
	public int probabilidadInv(Random rand){
		 int random= rand.nextInt(100)+1;
	        int number=0;
	        if (random > 90) {
	            number = 1024;
	        } 
	        else if (random <= 90) {
	            number=2048;
	        }
	 return number;
	}
	/**este metodo calcula el valor que se suma a la puntuacion en caso de que el juego sea Fibonacci
	 * utilizo el valor doce ya que si observamos bien 2048 es igual a 2^11 y aportar?a como puntuaci?n 2^1,
	 * devuelve 2^(12 - el exponente del valor de la casilla) y esto se cumple con todos los valores
	 * */
	public int score(int valor) {
		int exponente = 0;
		while (valor > 1) {
			valor = valor /2;
			exponente ++ ;
		}
		return (int) Math.pow(2, (11 - exponente));
	}

//	@Override
//	public int puntuacion(Cell[][] tablero, Position position, int score) {
//		int aux=0;
//		aux=tablero[position.getFila()][position.getColumna()].getValue();
//		aux=sacaPotencia(aux)+1;//porque antes se ha fusionado y el valor ha cambiado, por lo que agrego 1
//		aux=4096/ (int) Math.pow(2, aux);
//		aux=score+aux;
//		return aux;
//	}
	public int sacaPotencia(int a) {
		int i=0,cociente=0;
		cociente=a/2;
		while(cociente!=0) {
			i++;
			cociente=cociente/2;
		}
		return i;
	}

	@Override
	public int getScore(Cell cell) {
		return score(cell.getValue());
	}
}
