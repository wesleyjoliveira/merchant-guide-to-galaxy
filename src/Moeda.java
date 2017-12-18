
public class Moeda {
	
	private String nome;
	private Double valorUnitario;

	public Moeda(String nome, Double valorUnitario) {
		this.nome = nome;
		this.valorUnitario = valorUnitario;
	}
	
	public Double getValorUnitario() {
		return valorUnitario;
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.valorUnitario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moeda other = (Moeda) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	

}
