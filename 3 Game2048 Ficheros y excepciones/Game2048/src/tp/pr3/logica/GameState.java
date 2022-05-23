package tp.pr3.logica;

public class GameState {
	private int mejorValor;
	private int score;
	private int [][] boardState;
	
	public GameState( int [][] m, int valor, int score) {
		boardState=m;
		this.mejorValor=valor;
		this.score=score;
		
	}
	public int getScore() {
		return this.score;
	}
	public int getHighest() {
		return this.mejorValor;
	}
	public int [][] getState(){
		return this.boardState;
	}
}
