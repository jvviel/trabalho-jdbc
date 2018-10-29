package br.edu.fema.exception;

public class ExcecaoGeral extends RuntimeException{

	private static final long serialVersionUID = 5241160442831987142L;
	
	public ExcecaoGeral(String mensagem) {
		super(mensagem);
	}
}
