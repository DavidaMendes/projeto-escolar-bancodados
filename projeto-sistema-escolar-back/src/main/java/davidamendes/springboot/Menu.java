package davidamendes.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import java.util.InputMismatchException;

import davidamendes.springboot.Sistema;

@Component
public class Menu {
    private static final Scanner ENTRADA = new Scanner(System.in);

    @Autowired
    @Lazy
    private Sistema sistema;

    public void exibirMenu() {
        while (true) {
            System.out.print("---SISTEMA ESCOLAR--- \n 1 - Encontrar Aluno\n 2 - Adicionar Aluno \n 3 - Remover aluno \n 4 - Sair \n R: ");
            try {
                int resposta = ENTRADA.nextInt();
                processarOpcao(resposta);
            } catch (InputMismatchException e) {
                ENTRADA.nextLine();
                System.out.println("Entrada inválida! Por favor, insira um número entre 1 e 4.");
            }
        }
    }

    private void processarOpcao(int resposta) {

        switch (resposta) {
            case 1:
                sistema.informacoesAluno();
                break;
            case 2:
                sistema.adicionarAluno();
                break;
            case 3:
                sistema.removerAluno();
                break;
            case 4:
                System.out.println("Saindo do sistema...");
                System.exit(0);
                break;
            default:
                System.out.print("Insira uma opção entre 0 a 3:\n");

        }
    }
}
