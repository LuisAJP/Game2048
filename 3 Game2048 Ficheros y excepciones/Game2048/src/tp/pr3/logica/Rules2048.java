package tp.pr3.logica;

import java.util.Random;

public class Rules2048 implements GameRules{
	
	@Override
	/**Incorpora una célula con valor aleatorio en la posición pos del tablero board*/
	public boolean addNewCellAt(Board board,Position position,Random rand) {
		int aux;
		if (board.getBoard()[position.getFila()][position.getColumna()] == null) {//si esta vacia 
			aux=probabilidad2048(rand);//genero un valor 2 o 4, valor aleatorio
			board.getBoard()[position.getFila()][position.getColumna()] = new Cell(aux);//se crea celula con el valor 2 o 4 segun aux en una posicion del tablero
			return true;
		} else
			return false;
	}
	
	
	public int probabilidad2048(Random rand){
		 int random= rand.nextInt(100)+1;
	        int number=0;
	        if (random > 90) {
	            number = 4;
	        } 
	        else if (random <= 90) {
	            number=2;
	        }
	 return number;
	}
	@Override
	/**Fusiona dos células y devuelve el número de puntos obtenido por la fusión*/
	public int merge(Cell self, Cell other) {
		int aux=0;
		if(this.canMergeNeighbours(self, other)){
			aux=self.getValue()+ other.getValue();
			self.setValue(aux);
			return self.getValue();
		}
		return 0;
	}
	
	@Override
	/**Devuelve el mejor valor del tablero, según las reglas de ese juego, 
	 * comprobándose si es un valor ganador y se ha ganado el juego*/
	public int getWinValue(Board board) {
		int highest=2;
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

	@Override
	/**Devuelve si el juego se ha ganado o no*/
	public boolean win(Board board) {
		for(int i=0;i<board.getSize();i++) {
			for(int j=0;j<board.getSize();j++) {
				if (board.getBoard()[i][j]!=null) {
					if (board.getBoard()[i][j].getValue()==2048){
						return true;
					}
				}
			}
		}
		return false;
	}


	@Override
	public int getScore(Cell cell) {
		return cell.getValue();
	}
}


