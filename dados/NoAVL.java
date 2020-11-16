package dados;

public class NoAVL extends NoArv{
	private byte fatorBalanceamento;
	
	public NoAVL(Banco info) {
		super(info);
		this.fatorBalanceamento = 0;
	}

	public byte getFatorBalanceamento() {
		return this.fatorBalanceamento;
	}

	public void setFatorBalanceamento(byte fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}
}