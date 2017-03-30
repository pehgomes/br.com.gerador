package br.com.numerador.statics;

public class StaticMenus {
	
	private static System system;

	public static void getSwitchNumeros(){
		StringBuilder string = new StringBuilder();
		
		string.append("             *************************\n");
		string.append("             *                       *\n");
		string.append("             * 1 - sortear           *\n");
		string.append("             * 2 - sair              *\n");
		string.append("             *                       *\n");
		string.append("             *                       *\n");
		string.append("             *************************\n");
		
		system.out.println(string.toString());
	}
	
	public static void getOpcaoInvalida(){
		StringBuilder string = new StringBuilder();
		
		string.append("Opcao invalida ...\n");
		system.out.println(string.toString());
	}
	
	public static void getContinuarTentando(){
		StringBuilder string = new StringBuilder();
		
		string.append("             *************************\n");
		string.append("             *   Deseja continuar ?  *\n");
		string.append("             * y - yes 	            *\n");
		string.append("             * n - no                *\n");
		string.append("             *                       *\n");
		string.append("             *                       *\n");
		string.append("             *************************\n");
		
		system.out.println(string.toString());
	}
	
	public static void getContinuarJogando(){
		StringBuilder string = new StringBuilder();
		
		string.append("             *************************\n");
		string.append("             *  Continuar jogando ?  *\n");
		string.append("             * y - Yes               *\n");
		string.append("             * n - No                *\n");
		string.append("             *                       *\n");
		string.append("             *                       *\n");
		string.append("             *************************\n");
		
		system.out.println(string.toString());
	}


	
	public static void getShowMenuVencimento() {
		StringBuilder string = new StringBuilder();
		
		string.append("Este software esta programado para parar de funcionar agora ...\n");
		string.append("bye bye ...");
		
		system.out.println(string.toString());
	}
	
	public static void getSwitchCartelas(){
		StringBuilder string = new StringBuilder();
		
		string.append("**********************************************************\n");
		string.append("*   escolha o numero da cartela  que deseja escolher     *\n");
		string.append("*            				                *\n");
		string.append("**********************************************************\n");
		
		system.out.println(string.toString());
	}


}
