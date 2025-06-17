import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println("Nao foi possivel conectar ao banco, encerrando.");
            sc.close();
            return;
        }

        ProfessorDAO dao = new ProfessorDAO(conn);

        dao.createTable();

        int opcao;
        do {
            System.out.println("\n=== MENU PROFESSORES ===");
            System.out.println("1 - Inserir professor");
            System.out.println("2 - Listar professores");
            System.out.println("3 - Atualizar professor");
            System.out.println("4 - Deletar professor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Disciplina: ");
                    String disciplina = sc.nextLine();
                    dao.inserirProfessor(new Professor(nome, disciplina));
                }
                case 2 -> {
                    List<Professor> lista = dao.listarProfessores();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum professor cadastrado.");
                    } else {
                        for (Professor p : lista) {
                            System.out.printf("%d - %s (%s)\n", p.getId(), p.getNome(), p.getDisciplina());
                        }
                    }
                }
                case 3 -> {
                    System.out.print("ID do professor: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Nova disciplina: ");
                    String disciplina = sc.nextLine();
                    dao.atualizarProfessor(new Professor(id, nome, disciplina));
                }
                case 4 -> {
                    System.out.print("ID do professor a deletar: ");
                    int id = sc.nextInt();
                    dao.deletarProfessor(id);
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        sc.close();

        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexao: " + e.getMessage());
        }
    }
}
