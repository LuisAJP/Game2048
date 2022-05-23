package tp.pr1;

public class Cell {
	private int valor;
	
	
	public Cell(int value){
		this.valor =value;
	}
	
	/**
	 * Fusiona, si es posible, una célula y su vecina,
	 * eliminando esta última
	 */
	public boolean doMerge(Cell neighbour){
		int aux=0;
		if(getValue()==neighbour.getValue()){
			aux=getValue()+ neighbour.getValue();
			valor=aux;
			return true;
		}
	return false;
	}
	/**
	 * Si el valor de la baldosa es cero
	 * devuelve true
	 */
	public boolean isEmpty(){
		boolean ok = false;
		if (valor==0){
			ok=true;
		}
		return ok;	
	}
	public int getValue(){
		return this.valor  ;
	}
	public void setValue(int a){
		 this.valor=a ;
	}
}
