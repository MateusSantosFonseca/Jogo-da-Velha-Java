package jogoDaVelha;

import java.util.Scanner;

//X é a jogada do player 1
//O é a jogada do player 2

public class Tabuleiro {

	char campoDeJogo[][] = new char[3][3];
	boolean haJogadas = true;
	int vencedor = 0;
	int jogador = 0;

	public void jogar() {

		campoDeJogo = geradorCampoDeJogo();
		System.out.println("O player 1 será: X e o player 2: O");
		System.out.println("Lembre-se, as linhas e colunas começam em 0!!");
		printCampoDeJogo(campoDeJogo);

		jogador = ((int) (1 + Math.random() * (2 - 1 + 1)));
		System.out.println("O primeiro a jogar é o player: " + jogador);

		while (haJogadas(campoDeJogo) == true) {

			campoDeJogo = jogadaNoCampoDeJogo(campoDeJogo, jogador);

			vencedor = verificaVencedor(campoDeJogo);
			if (vencedor != 0) {
				System.out.println("O vencedor é: " + vencedor);
				System.out.println("O campo final ficou assim: ");
				System.out.println("");
				printCampoDeJogo(campoDeJogo);
				System.exit(-1);
			}

			System.out.println("O campo ficou assim após a jogada do jogador: " + jogador);
			printCampoDeJogo(campoDeJogo);

			System.out.println("");
			jogador = resolveJogador(jogador);
			System.out.println("Agora é a vez do jogador: " + jogador);

		}

		System.out.println("O jogo terminou empatado!");
		System.out.println("O campo final ficou assim: ");
		System.out.println("");
		printCampoDeJogo(campoDeJogo);
		System.exit(-1);

	}

	// gerando o campo de jogo base
	public char[][] geradorCampoDeJogo() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				campoDeJogo[i][j] = '-';
		return campoDeJogo;
	}

	// fazendo uma jogada
	public char[][] jogadaNoCampoDeJogo(char[][] campoDeJogo2, int jogador) {
		Scanner leitor2 = new Scanner(System.in);
		boolean answer;
		boolean indicesForaDoLimite;

		System.out.println("Qual a linha que você deseja fazer a jogada?");
		int i = leitor2.nextInt();
		System.out.println("Qual a coluna que você deseja fazer a jogada?");
		int j = leitor2.nextInt();
		indicesForaDoLimite = foraDoLimite(i, j);

		while (indicesForaDoLimite == true) {
			System.out.println("A linha deve ser 0, 1 ou 2, por favor insira corretamente a linha: ");
			i = leitor2.nextInt();
			System.out.println("A coluna deve ser 0, 1 ou 2, por favor insira corretamente a coluna: ");
			j = leitor2.nextInt();
			indicesForaDoLimite = foraDoLimite(i, j);
		}

		answer = jaHouveJogadaNestaPosicao(campoDeJogo2, i, j);

		while (answer == false) {
			System.out.println("Já houve jogada nessa linha, insira outra linha: ");
			i = leitor2.nextInt();
			System.out.println("Já houve jogada nessa coluna, insira outra coluna: ");
			j = leitor2.nextInt();
			answer = jaHouveJogadaNestaPosicao(campoDeJogo2, i, j);
		}

		if (jogador == 1)
			campoDeJogo2[i][j] = 'X';
		else
			campoDeJogo2[i][j] = 'O';
		return campoDeJogo2;
	}

	public boolean haJogadas(char[][] campoDeJogo2) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (campoDeJogo2[i][j] == '-')
					return true;
		return false;
	}

	public int verificaVencedor(char[][] campoDeJogo2) {
		int vencedor = 0;
		if (((campoDeJogo2[0][0] == campoDeJogo2[0][1]) && (campoDeJogo2[0][1] == campoDeJogo2[0][2]))
				&& (campoDeJogo2[0][0] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[0][0]);
		else if (((campoDeJogo2[1][0] == campoDeJogo2[1][1]) && (campoDeJogo2[1][1] == campoDeJogo2[1][2]))
				&& (campoDeJogo2[1][0] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[1][0]);
		else if (((campoDeJogo2[2][0] == campoDeJogo2[2][1]) && (campoDeJogo2[2][1] == campoDeJogo2[2][2]))
				&& (campoDeJogo2[2][0] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[2][0]);
		else if (((campoDeJogo2[0][0] == campoDeJogo2[1][1]) && (campoDeJogo2[1][1] == campoDeJogo2[2][2]))
				&& (campoDeJogo2[0][0] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[0][0]);
		else if (((campoDeJogo2[0][2] == campoDeJogo2[1][1]) && (campoDeJogo2[1][1] == campoDeJogo2[2][0]))
				&& (campoDeJogo2[0][2] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[0][2]);
		else if (((campoDeJogo2[0][0] == campoDeJogo2[1][0]) && (campoDeJogo2[1][0] == campoDeJogo2[2][0]))
				&& (campoDeJogo2[0][0] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[0][0]);
		else if (((campoDeJogo2[0][1] == campoDeJogo2[1][1]) && (campoDeJogo2[1][1] == campoDeJogo2[2][1]))
				&& (campoDeJogo2[0][1] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[0][1]);
		else if (((campoDeJogo2[0][2] == campoDeJogo2[1][2]) && (campoDeJogo2[1][2] == campoDeJogo2[2][2]))
				&& (campoDeJogo2[0][2] != '-'))
			vencedor = traduzVencedor(campoDeJogo2[2][0]);
		return vencedor;
	}

	public int traduzVencedor(char vencedor) {
		if (vencedor == 'X')
			return 1;
		else
			return 2;
	}

	public void printCampoDeJogo(char[][] campoDeJogo2) {
		System.out.println("");
		for (int i = 0; i < 20; i++)
			System.out.println("");

		System.out.println("Campo Atual:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(campoDeJogo2[i][j] + "       ");
			System.out.println("");
		}
	}

	public int resolveJogador(int jogador) {
		if (jogador == 1)
			return 2;
		else
			return 1;
	}

	public boolean jaHouveJogadaNestaPosicao(char[][] campoDeJogo2, int i, int j) {
		if (campoDeJogo2[i][j] != '-')
			return false;
		else
			return true;
	}

	public boolean foraDoLimite(int i, int j) {
		if (i < 0 || i > 2)
			return true;
		if (j < 0 || j > 2)
			return true;
		return false;
	}

}
