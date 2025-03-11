package davidamendes.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import davidamendes.springboot.MetodosAluno;
import davidamendes.springboot.Menu;
import davidamendes.springboot.model.Aluno;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

@Component
public class Sistema {
    private static final Scanner ENTRADA = new Scanner(System.in);

    @Autowired
    private MetodosAluno metodosAluno;

    @Autowired
    @Lazy
    private Menu menu;

    private Integer conversorDeIntParaInteger(int idAluno){
        Integer id = Integer.valueOf(idAluno);
        return id;
    }

    public void informacoesAluno(){
        System.out.println("Digite a matricula do aluno: ");

        try{
            int idAluno = conversorDeIntParaInteger(ENTRADA.nextInt());
            Aluno aluno = metodosAluno.findById(idAluno);
            if(aluno != null){
                System.out.println(aluno);
                System.out.println("Deseja editar alguma informação do aluno (0 - Não, 1 - Sim)");
                try {
                    int resposta = ENTRADA.nextInt();
                    ENTRADA.nextLine();

                    if (resposta == 0) {
                        System.out.println("Ação encerrada");
                        menu.exibirMenu();
                    } else if (resposta == 1) {
                        System.out.println("Escolha a informação que deseja editar: \n" +
                                "1 - Nome\n2 - Sobrenome\n3 - Ulitmo Nome\n4 - Email\n" +
                                "5 - CPF\n6 - Telefone\n7 - Idade");

                        int respostaEdicao = ENTRADA.nextInt();
                        ENTRADA.nextLine();
                        try {
                            System.out.println("Digite a nova informação que deseja editar: ");
                            String informacao = ENTRADA.nextLine();
                            metodosAluno.editAluno(idAluno, respostaEdicao, informacao);
                        }  catch (InputMismatchException e) {
                            ENTRADA.nextLine();
                            System.out.println("Entrada inválida! Por favor, digite entre 1 a 7.");
                        }

                    } else {
                        System.out.println("Insira uma opção entre 0 e 1.");
                    }
                } catch (InputMismatchException e) {
                    ENTRADA.nextLine();
                    System.out.println("Entrada inválida! Por favor, digite 0 ou 1.");
                }
            }
            else {
                System.out.println("Aluno não encontrado");
            }
        } catch (InputMismatchException e){
            System.out.println("Entrada Invalida!");
        } finally {
        menu.exibirMenu();
    }


    }

    public void adicionarAluno() {
        System.out.println("Digite o nome:");
        String nome = ENTRADA.nextLine();

        System.out.println("Digite o sobrenome:");
        String sobrenome = ENTRADA.nextLine();

        System.out.println("Digite o último nome:");
        String ultimoNome = ENTRADA.nextLine();

        System.out.println("Digite o email:");
        String email = ENTRADA.nextLine();

        System.out.println("Digite o CPF:");
        String cpf = ENTRADA.nextLine();

        System.out.println("Digite o telefone:");
        String telefone = ENTRADA.nextLine();

        System.out.println("Digite a idade:");
        String idade = ENTRADA.nextLine();

        List<String> campos = new ArrayList<>();
        campos.add(nome);
        campos.add(sobrenome);
        campos.add(ultimoNome);
        campos.add(email);
        campos.add(cpf);
        campos.add(telefone);
        campos.add(idade);

        System.out.println(campos);

        boolean camposCompletos = true;
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                camposCompletos = false;
                break;
            }
        }

        if (camposCompletos) {
            try {
                metodosAluno.saveAluno(nome, sobrenome, ultimoNome, email, cpf, telefone, idade);
                System.out.println("Aluno adicionado com sucesso!");
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida. Digite um número inteiro.");
            }
        } else {
            System.out.println("Erro: Todos os campos devem ser preenchidos. Por favor, tente novamente.");
        }
    }

    public void removerAluno() {
        System.out.println("Digite a matricula do aluno: ");

        try {
            int idAluno = ENTRADA.nextInt();
            ENTRADA.nextLine();
            Aluno aluno = metodosAluno.findById(idAluno);
            if (aluno != null) {
                System.out.println("Certeza que deseja remover aluno (0 - Não, 1 - Sim)");
                try {
                    int resposta = ENTRADA.nextInt();
                    ENTRADA.nextLine();

                    if (resposta == 0) {
                        System.out.println("Ação encerrada");
                        menu.exibirMenu();
                    } else if (resposta == 1) {
                        System.out.println("Ação executada com sucesso");
                        metodosAluno.removeAluno(idAluno);
                    } else {
                        System.out.println("Insira uma opção entre 0 e 1.");
                    }
                } catch (InputMismatchException e) {
                    ENTRADA.nextLine();
                    System.out.println("Entrada inválida! Por favor, digite 0 ou 1.");
                }
            } else {
                System.out.println("Aluno não encontrado");
            }
        } catch (InputMismatchException e) {
            ENTRADA.nextLine();
            System.out.println("Entrada inválida! Por favor, digite um número para a matrícula.");
        } finally {
            menu.exibirMenu();
        }
    }

}
