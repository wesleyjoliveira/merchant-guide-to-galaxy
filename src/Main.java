import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private static Pattern ATRIBUICAO_NUMERO = Pattern.compile("(\\w+) is (\\w)$", Pattern.CASE_INSENSITIVE);
	private static Pattern ATRIBUICAO_MOEDA = Pattern.compile("(.*) (\\w+) is ([\\d]+) Credits",
			Pattern.CASE_INSENSITIVE);

	private static Pattern PERGUNTA_NUMERO = Pattern.compile("How much is (.*) \\?", Pattern.CASE_INSENSITIVE);
	private static Pattern PERGUNTA_MOEDA = Pattern.compile("How many Credits is (.*) (\\w+) \\?",
			Pattern.CASE_INSENSITIVE);

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Map<String, String> sinonimos = new HashMap<>();
		Map<String, Moeda> moedas = new HashMap<>();
		NumeroRomanoConversor conversor = new NumeroRomanoConversor(sinonimos);

		while (sc.hasNext()) {
			String linha = sc.nextLine();

			boolean processado = false;
			if (!linha.isEmpty()) {

				Matcher matcherAtribuicaoNumero = ATRIBUICAO_NUMERO.matcher(linha);
				if (matcherAtribuicaoNumero.find()) {
					processado = true;
					String valor = matcherAtribuicaoNumero.group(1);
					String chave = matcherAtribuicaoNumero.group(2);
					sinonimos.put(chave, valor);
				}

				Matcher matcherAtribuicaoMoeda = ATRIBUICAO_MOEDA.matcher(linha);
				if (matcherAtribuicaoMoeda.find()) {
					processado = true;
					String quantidade = matcherAtribuicaoMoeda.group(1); 
					String nomeMoeda = matcherAtribuicaoMoeda.group(2);
					String valorTotal = matcherAtribuicaoMoeda.group(3);

					Double valorUnitario = Long.valueOf(valorTotal)
							/ (conversor.traduzirNumeralRomano(quantidade) * 1.0);

					Moeda moeda = new Moeda(nomeMoeda, valorUnitario);
					moedas.put(nomeMoeda, moeda);
				}

				Matcher matcherPerguntaNumero = PERGUNTA_NUMERO.matcher(linha);
				if (matcherPerguntaNumero.find()) {
					processado = true;
					String numero = matcherPerguntaNumero.group(1);

					System.out.println(String.format("%s is %d", numero, conversor.traduzirNumeralRomano(numero)));
				}

				Matcher matcherPerguntaMoeda = PERGUNTA_MOEDA.matcher(linha);
				if (matcherPerguntaMoeda.find()) {
					processado = true;
					String numero = matcherPerguntaMoeda.group(1);
					String nomeMoeda = matcherPerguntaMoeda.group(2);
					Moeda moeda = moedas.get(nomeMoeda);

					Double valorTotal = conversor.traduzirNumeralRomano(numero) * moeda.getValorUnitario();

					System.out.println(String.format("%s %s is %d Credits", numero, nomeMoeda, valorTotal.longValue()));
				}

				if (!processado) {
					System.out.println("I have no idea what you are talking about");
				}

			}

		}
		sc.close();

	}
}
