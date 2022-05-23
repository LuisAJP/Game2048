package tp.pr3.logica;

public enum GameType {
	
	ORIG("2048, original version", "original", new Rules2048()),
	FIB("2048, Fibonacci version", "fib", new RulesFib()),
	INV("2048, inverse version", "inverse", new RulesInverse());
	
	private String userFriendlyName;
	private String parameterName;
	private GameRules correspondingRules;
	
	private GameType(String friendly, String param, GameRules rules){
		userFriendlyName = friendly;
		parameterName = param;
		correspondingRules = rules;
	}
	
	/**
	 * precondition : param string contains only lower−case characters
	 * used in PlayCommand and Game, in parse method and load method, respectively
	 **/ 
	
	public static GameType parse(String param) {
		for (GameType gameType : GameType.values()) {
			if (gameType.parameterName.equals(param))
				return gameType;
		}
		return null;
	}
	
	/** used in PlayCommand to build help message, and in parse method exception msg*/
	public static String externaliseAll () {
		String s = "";
		for (GameType type : GameType.values())
			s = s + " " + type.parameterName + ",";
		return s.substring(1, s.length()-1);
	}
	
	/**used in Game when constructing object and when executing play command*/
	public GameRules getRules() {
		return correspondingRules;
	}
	
	/** used in Game in store method*/
	public String externalise () {
		return parameterName;
	}
	
	/**used PlayCommand and LoadCommand, in parse methods 
	 * in ack message and success message, respectively
	 * */
	public String toString() {
		return userFriendlyName;
	}
	
	/**
	 * Parsea el juego introducido y devuelve gameType
	 */
	public static GameType convertirCadenaGameType(String cadena) {
		GameType gameType=null;
		String s=cadena.toUpperCase();//la cadena lo convierte a mayuscula
		switch(s){
		case "FIB":
			gameType=GameType.FIB;
			break;
		case "INVERSE":
			gameType=GameType.INV;
			break;
		case "ORIGINAL":
			gameType=GameType.ORIG;
			break;
		}
		return gameType;
	}
}
