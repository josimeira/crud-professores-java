import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class ProfessorGUI extends JFrame {
    private ProfessorDAO dao;
    private JTable table;
    private DefaultTableModel model;
    private JTextField nomeField, disciplinaField;

    public ProfessorGUI() {
        Connection conn = DBConnection.getConnection();
        dao = new ProfessorDAO(conn);

        setTitle("CRUD de Professores");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabela
        model = new DefaultTableModel(new Object[]{"ID", "Nome", "Disciplina"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // Painel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        nomeField = new JTextField();
        disciplinaField = new JTextField();
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Disciplina:"));
        inputPanel.add(disciplinaField);

        // Botões
        JButton btnInserir = new JButton("Inserir");
        JButton btnExcluir = new JButton("Excluir Selecionado");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnInserir);
        btnPanel.add(btnExcluir);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(scroll, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);

        // Eventos
        btnInserir.addActionListener(e -> inserir());
        btnExcluir.addActionListener(e -> excluir());

        carregarProfessores();
        setVisible(true);
    }

    private void carregarProfessores() {
        model.setRowCount(0); // limpa
        List<Professor> lista = dao.listarProfessores();
        for (Professor p : lista) {
            model.addRow(new Object[]{p.getId(), p.getNome(), p.getDisciplina()});
        }
    }

    private void inserir() {
        String nome = nomeField.getText().trim();
        String disciplina = disciplinaField.getText().trim();
        if (!nome.isEmpty() && !disciplina.isEmpty()) {
            dao.inserirProfessor(new Professor(nome, disciplina));
            nomeField.setText("");
            disciplinaField.setText("");
            carregarProfessores();
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
        }
    }

    private void excluir() {
        int linha = table.getSelectedRow();
        if (linha >= 0) {
            int id = (int) model.getValueAt(linha, 0);
            dao.deletarProfessor(id);
            carregarProfessores();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um professor para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProfessorGUI::new);
    }
}
