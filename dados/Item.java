package dados;

public class Item {
	// aqui podem ser declarados outros atributos conforme sua necessidade.
	private int chave;

	
	
	//Construtor de objetos da classe Item
	public Item(int ch) {
		this.chave = ch;
	}
	





	//Modifica o valor do atributo chave
	public void setChave (int ch){
		this.chave = ch;
	}
	
	//Faz a leitura do valor do atributo chave
	public int getChave (){
		return this.chave;
	}
	

}
