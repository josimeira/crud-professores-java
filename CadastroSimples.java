import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroSimples extends JFrame {
    private JTextField txtNome;
    private JRadioButton rbMasculino, rbFeminino;
    private JComboBox<String> comboIdioma;
    private JTextArea txtResultado;

    public CadastroSimples() {
        setTitle("Cadastro Simples");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        // Nome
        txtNome = new JTextField();
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);

        // Sexo
        JPanel sexoPanel = new JPanel();
        rbMasculino = new JRadioButton("Masculino");
        rbFeminino = new JRadioButton("Feminino");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFeminino);
        sexoPanel.add(rbMasculino);
        sexoPanel.add(rbFeminino);
        panel.add(new JLabel("Sexo:"));
        panel.add(sexoPanel);

        // Idioma
        comboIdioma = new JComboBox<>(new String[]{"Português", "Inglês", "Espanhol"});
        panel.add(new JLabel("Idioma preferido:"));
        panel.add(comboIdioma);

        // Botão de cadastro
        JButton btnCadastrar = new JButton("Cadastrar");
        panel.add(btnCadastrar);

        // Área de resultado
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);

        // Adiciona tudo ao frame
        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Ação do botão
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                String sexo = rbMasculino.isSelected() ? "Masculino" :
                              rbFeminino.isSelected() ? "Feminino" : "Não informado";
                String idioma = (String) comboIdioma.getSelectedItem();

                String resultado = String.format("Nome: %s\nSexo: %s\nIdioma: %s\n\n",
                        nome, sexo, idioma);
                txtResultado.append(resultado);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroSimples().setVisible(true));
    }
}
