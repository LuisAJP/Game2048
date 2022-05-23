package tp.pr3.logica;

import java.util.Random;

public class RulesFib implements GameRules {

	@Override
	public boolean addNewCellAt(Board board,Position position,Random rand) {
		int aux;
		if (board.getBoard()[position.getFila()][position.getColumna()] == null) {//si esta vacia 
			aux=probabilidadFib(rand);//genero un valor 2 o 4, valor aleatorio
			board.getBoard()[position.getFila()][position.getColumna()] = new Cell(aux);//se crea celula con el valor 2 o 4 segun aux en una posicion del tablero
			return true;
		} else
			return false;
		
	}
	/**
	 * Genera un numero aleatorio
	 */
	public int probabilidadFib(Random rand){
		 int random= rand.nextInt(100)+1;
	        int number=0;
	        if (random > 90) {
	            number = 2;
	        } 
	        else if (random <= 90) {
	            number=1;
	        }
	 return number;
	}
	@Override
	public int merge(Cell self, Cell other) {
		// TODO Auto-generated method stub
		if(self!= null && other != null) {
			int aux=nextFib(other.getValue());//calculo el fibo de destino
			if(self.getValue() == 1 && other.getValue() == 1) {
				self.setValue(aux);
				return self.getValue();
			}
			if(aux==self.getValue()) {
				aux=nextFib(self.getValue());
				self.setValue(aux);
				return self.getValue();
			}
			aux=0;
			aux=nextFib(self.getValue());//calculo el fibo de destino
			if(aux==other.getValue()) {
				aux=nextFib(other.getValue());
				self.setValue(aux);
				return self.getValue();
			}
		}
		return 0;
	}

	@Override
	public int getScore(Cell cell) {
		return cell.getValue();
	}
	
	@Override
	public int getWinValue(Board board) {
		int highest=1;
		for(int f=0;f<board.getSize();f++){
			for(int c=0;c<board.getSize();c++){
				if(board.getBoard()[f][c]!=null){
					if(board.getBoard()[f][c].getValue()>highest){
						highest=board.getBoard()[f][c].getValue();
					}
				}	
			}
		}
		return highest;
	}
	
	/**Devuelve si el juego se ha ganado o no*/
	@Override
	public boolean win(Board board) {
		for(int i=0;i<board.getSize();i++) {
			for(int j=0;j<board.getSize();j++) {
				if (board.getBoard()[i][j]!=null) {
					if (board.getBoard()[i][j].getValue()==144){
						return true;
					}
				}
			}
		}
		return false;
	}	
	public static int nextFib(int previous) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);
	}

	public boolean canMergeNeighbours(Cell self, Cell other ) {
		if(self != null && other != null ) {
			int aux=nextFib(other.getValue());//calculo el fibo de destino
			if(self.getValue()==1 && other.getValue()==1) {
				return true;
			}
			if(aux==self.getValue()) {
				aux=nextFib(self.getValue());
				return true;
			}
			aux=0;
			aux=nextFib(self.getValue());//calculo el fibo de destino
			if( aux==other.getValue()) {
				return true;
			}
		}
		
		return false;
	}
}
