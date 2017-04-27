package br.com.numerador.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import br.com.numerador.statics.StaticMenus;

public class Main {

	private static final Scanner scanner = new Scanner(System.in);
	
	private static final String DATA_FUNCIONAMENTO = "10/05/2017";

	private static Logger logger = Logger.getLogger("robot");
	
	private static Date dataFinalFuncionamento;
	
	private static Date dataAtual;

	


	public static void main(String... args) throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Map<Integer, List<Integer>> mapDeCartelas =  new HashMap<>();
		
		dataAtual = new Date();
		dataFinalFuncionamento = date.parse(DATA_FUNCIONAMENTO);

		if (dataAtual.before(dataFinalFuncionamento)) {
			StaticMenus.getSwitchNumeros();
			Object obj = scanner.nextLine();
			receberParametro(obj, mapDeCartelas);
		} else {
			StaticMenus.getShowMenuVencimento();
		}

	}

	private static void receberParametro(Object parametro, Map<Integer, List<Integer>> mapDeCartelas) throws ParseException {
		switch ((String) parametro) {
		case "1":
			preencherCartelas(mapDeCartelas);
			StaticMenus.getSwitchCartelas();
			String opc = scanner.nextLine();
			carregarCartela(opc, mapDeCartelas);
			StaticMenus.getContinuarJogando();
			Object obj = scanner.nextLine();
			verificarTentativa(obj);
			break;

		case "2":
			logger.warning("finalizando ...");
			diminuirVelocidade();
			logger.warning("bye !");
			break;
		default:
			StaticMenus.getOpcaoInvalida();
			StaticMenus.getContinuarTentando();
			Object obj1 = scanner.nextLine();
			verificarTentativa(obj1);
			break;
		}
	}

	private static void verificarTentativa(Object obj) throws ParseException {
		switch ((String) obj) {
		case "y":
		case "Y":
		case "yes":
			String args[] = {"",""};
			logger.info("Preparando novas cartelas ...");
			diminuirVelocidade();
			logger.info("Iniciando novo jogo ...");
			diminuirVelocidade();
			Main.main(args);
			break;
		case "n":
		case "N":
		case "no":
			logger.warning("Finalizando o jogo ...");
			diminuirVelocidade();
			logger.warning("Bye!");
			diminuirVelocidade();
			System.exit(0);
			break;
		default:
			System.exit(0);
			break;
		}
	}
	
	private static void carregarCartela(String obj, Map<Integer, List<Integer>> mapDeCartelas) {
		switch (obj) {
		case "1":
		case "2":
		case "3":
		case "4":		
			exibirCartela(obj, mapDeCartelas);
			break;
		default:
			logger.warning("Cartela invalida ...");
			diminuirVelocidade();
			StaticMenus.getContinuarTentando();
			Object obj1 = scanner.nextLine();
			try {
				verificarTentativa(obj1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			diminuirVelocidade();
			logger.warning("Finalizando gerador de numeros ...");
			System.exit(0);
			break;
		}
	}

	private static void preencherCartelas(Map<Integer, List<Integer>> mapDeCartelas) {
		List<Integer> cartelaUm = new ArrayList<>();
		List<Integer> cartelaDois = new ArrayList<>();
		List<Integer> cartelaTres = new ArrayList<>();
		List<Integer> cartelaQuatro = new ArrayList<>();
		List<List<Integer>> todasAsCartelas = new ArrayList<>();
		
		Random randon = new Random();
		Set<Integer> numeros = new TreeSet<Integer>();
		exibirCarregamento();
		
		int y = 1;
		while (numeros.size() < 53) {
			numeros.add(randon.nextInt(99));
		}
		
		for (int valor : numeros) {
			if (y < 14) {
				cartelaUm.add(valor);
			} else if (y >= 14 && y < 27) {
				cartelaDois.add(valor);
			} else if (y >= 27 && y < 40) {
				cartelaTres.add(valor);
			} else if (y >= 40 && y < 53) {
				cartelaQuatro.add(valor);
			}
			y++;
		}
		
		
		
		todasAsCartelas.add(cartelaTres);
		todasAsCartelas.add(cartelaDois);
		todasAsCartelas.add(cartelaQuatro);
		todasAsCartelas.add(cartelaUm);
		
		exibirCartelasDinamicamente(todasAsCartelas, mapDeCartelas);
		preencherMapDeCartelas(todasAsCartelas, mapDeCartelas);
	}
	
	private static void exibirCartela(String obj,Map<Integer, List<Integer>> mapDeCartelas) {
		StringBuilder string = new StringBuilder();
		Integer key = Integer.parseInt(obj);

		List<Integer> listaNumeros = mapDeCartelas.get(key);
		System.out.println("\n cartela de nº "+obj+" selecionada");
		
		string.append("\n************************************************************************\n");
		for (Integer numero : listaNumeros) {
			string.append(" * " +numero);
		}
		string.append("\n************************************************************************\n");
		
		System.out.println(string.toString());
		gerarRestanteAleatorio(listaNumeros);
	}
	
	private static void preencherMapDeCartelas(List<List<Integer>> litaCartelas, Map<Integer, List<Integer>> mapDeCartelas) {
		int x = 1;
		for (List<Integer> cartela : litaCartelas) {
			mapDeCartelas.put(x, cartela);
			x++;
		}	
	}
	
	private static void exibirCartelasDinamicamente(List<List<Integer>> litaCartelas, Map<Integer, List<Integer>> mapDeCartelas) {
		int x = 1;
		for (List<Integer> cartela : litaCartelas) {
			StringBuilder papel = new StringBuilder();
			String registro = "Cartela de Nº "+x+ "\n";
			x++;
			for (int valorCartela : cartela) {
				papel.append(" * "+ valorCartela);
			}
			exibirCartela(papel, registro);
		}
	}
	
	private static void gerarRestanteAleatorio(List<Integer> list) {
		StringBuilder papel = new StringBuilder();
		Random randon = new Random();
		Set<Integer> numeros = new TreeSet<Integer>();
		int x = 1;
		
		numeros.addAll(list);
		while (numeros.size() < 51) {
			numeros.add(randon.nextInt(99));
		}
		
		for (int valorCartela : numeros) {
			if (x == 12) {
				papel.append("\n                  ");
			} else if (x == 22 | x == 32) {
				papel.append("\n                  ");
			} else if (x == 42) {
				papel.append("\n                  ");
			}
			papel.append(" * "+ valorCartela);	
			x++;
		}

		exibirCartela(papel, "\n50 numeros gerados ...");
	}
	
	private static void exibirCartela(StringBuilder folha, String registro) {
		StringBuilder bordas = new StringBuilder();
		exibirCarregamentoIndividual("");
		
		bordas.append(registro);
		bordas.append("\n                  *******************************************************************\n");
		bordas.append("                  "+folha);
		bordas.append("\n                  *******************************************************************\n");
		
		System.out.println(bordas.toString());
	}
	
	private static void exibirCarregamento() {
		try {
			logger.info(" carregando cartelas ...");
			Thread.sleep(2000);
			logger.info(" gerando numeros da sorte ...");
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void exibirCarregamentoIndividual(String registro) {
		logger.info(registro + " gerada com sucesso !"); 
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void diminuirVelocidade(){
		try {
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
