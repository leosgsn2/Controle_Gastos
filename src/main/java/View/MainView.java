package view;

import controller.GastoController;
import controller.UsuarioController;
import model.Gasto;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UsuarioController usuarioController = new UsuarioController();
        GastoController gastoController = new GastoController();

        int opcao;

        do {
            System.out.println("\n=== Sistema Financeiro ===");
            System.out.println("1. Criar usuário");
            System.out.println("2. Carregar usuário");
            System.out.println("3. Adicionar gasto");
            System.out.println("4. Listar gastos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome do novo usuário: ");
                    String nomeNovo = sc.nextLine();
                    usuarioController.criarUsuario(nomeNovo);
                    break;

                case 2:
                    System.out.print("Nome do usuário: ");
                    String nome = sc.nextLine();
                    usuarioController.carregarUsuario(nome);
                    break;

                case 3:
                    Usuario usuarioAtual = usuarioController.getUsuarioAtual();

                    if (usuarioAtual == null) {
                        System.out.println("Carregue um usuário primeiro!");
                        break;
                    }

                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();

                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Categoria: ");
                    String cat = sc.nextLine();

                    gastoController.adicionarGasto(usuarioAtual, desc, valor, cat);
                    break;

                case 4:
                    Usuario user = usuarioController.getUsuarioAtual();

                    if (user == null) {
                        System.out.println("Carregue um usuário primeiro!");
                        break;
                    }

                    List<Gasto> gastos = gastoController.listarGastos(user);

                    if (gastos.isEmpty()) {
                        System.out.println("Nenhum gasto encontrado.");
                    } else {
                        System.out.println("\n--- LISTA DE GASTOS ---");

                        for (Gasto g : gastos) {
                            System.out.println(
                                    "ID: " + g.getId() +
                                            " | Descrição: " + g.getDescricao() +
                                            " | Categoria: " + g.getCategoria() +
                                            " | Valor: R$ " + g.getValor()
                            );
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}