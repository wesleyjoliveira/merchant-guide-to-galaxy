import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NumeroRomanoConversor {

	Map<String, String> sinonimos = new HashMap<>();

	public NumeroRomanoConversor(Map<String, String> sinonimos) {
		this.sinonimos = sinonimos;
	}

	/**
	 * Author: Francisco Edmundo
	 *
	 **/
	public int traduzirNumeralRomano(String texto) {
		texto = usaSinonimos(texto, this.sinonimos);
		int n = 0;
		int numeralDaDireita = 0;
		for (int i = texto.length() - 1; i >= 0; i--) {
			int valor = (int) traduzirNumeralRomano(texto.charAt(i));
			n += valor * Math.signum(valor + 0.5 - numeralDaDireita);
			numeralDaDireita = valor;
		}
		return n;
	}

	private double traduzirNumeralRomano(char caractere) {
		return Math.floor(Math.pow(10, "IXCM".indexOf(caractere)))
				+ 5 * Math.floor(Math.pow(10, "VLD".indexOf(caractere)));
	}

	private String usaSinonimos(String valorOriginal, Map<String, String> sinonimos) {
		for (Entry<String, String> item : sinonimos.entrySet()) {
			valorOriginal = valorOriginal.replaceAll(item.getValue(), item.getKey());
		}
		return valorOriginal.replaceAll(" ", "");
	}

}
