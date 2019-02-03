package jogoDaVelha;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Bem vindo ao jogo da velha criado por Mateus!");
		Tabuleiro x = new Tabuleiro();
		System.out.println("Você irá jogar contra um amigo!!");
		System.out.println("");
		x.jogar();
		leitor.close();
	}

}
