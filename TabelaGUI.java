import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TabelaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<String[]> dados;
	private int index = 0;

	private JTextField matriculaField;
	private JTextField nomeField;
	private JTextField emailField;
	private JTextField senhaField;
	private JTextField cargoField;
	private JTextField turmaField;
	private JTextField setorField;

	public TabelaGUI() {
		// Configuração inicial da janela
		setTitle("Tabela");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Carregar dados do arquivo
		dados = carregarDados("tabela.csv");

		// Configurar layout
		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(new JLabel("Matrícula:"));
		matriculaField = new JTextField(50);
		add(matriculaField);

		add(new JLabel("Nome:"));
		nomeField = new JTextField(50);
		add(nomeField);

		add(new JLabel("Email:"));
		emailField = new JTextField(50);
		add(emailField);

		add(new JLabel("Senha:"));
		senhaField = new JTextField(50);
		add(senhaField);

		add(new JLabel("Cargo:"));
		cargoField = new JTextField(50);
		add(cargoField);

		add(new JLabel("Turma:"));
		turmaField = new JTextField(50);
		add(turmaField);

		add(new JLabel("Setor:"));
		setorField = new JTextField(50);
		add(setorField);

		JButton prevButton = new JButton("Anterior");
		prevButton.addActionListener(e -> mostrarAnterior());
		add(prevButton);

		JButton nextButton = new JButton("Próximo");
		nextButton.addActionListener(e -> mostrarProximo());
		add(nextButton);

		JButton updateButton = new JButton("Atualizar");
		updateButton.addActionListener(e -> atualizarDado());
		add(updateButton);

		JButton deleteButton = new JButton("Deletar");
		deleteButton.addActionListener(e -> deletarDado());
		add(deleteButton);

		JButton saveButton = new JButton("Salvar");
		saveButton.addActionListener(e -> salvarDados("tabela.csv"));
		add(saveButton);

		// Mostrar o primeiro dado
		mostrarDado();
	}

	private ArrayList<String[]> carregarDados(String caminho) {
		ArrayList<String[]> lista = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho)))) {
			String linha;
			br.readLine(); // Pular o cabeçalho
			while ((linha = br.readLine()) != null) {
				lista.add(linha.split(";",-1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private void mostrarDado() {
		if (!dados.isEmpty() && index < dados.size() && index >= 0) {
			String[] dado = dados.get(index);
			matriculaField.setText(dado[0]);
			nomeField.setText(dado[1]);
			emailField.setText(dado[2]);
			senhaField.setText(dado[3]);
			cargoField.setText(dado[4]);
			turmaField.setText(dado[5]);
			setorField.setText(dado[6]);
		}
	}

	private void mostrarAnterior() {
		if (index > 0) {
			index--;
			mostrarDado();
		}
	}

	private void mostrarProximo() {
		if (index < dados.size() - 1) {
			index++;
			mostrarDado();
		}
	}

	private void atualizarDado() {
		if (!dados.isEmpty() && index < dados.size() && index >= 0) {
			String[] dado = new String[7];
			dado[0] = matriculaField.getText();
			dado[1] = nomeField.getText();
			dado[2] = emailField.getText();
			dado[3] = senhaField.getText();
			dado[4] = cargoField.getText();
			dado[5] = turmaField.getText();
			dado[6] = setorField.getText();
			dados.set(index, dado);
		}
	}

	private void deletarDado() {
		if (!dados.isEmpty() && index < dados.size() && index >= 0) {
			dados.remove(index);
			if (index > 0) {
				index--;
			}
			mostrarDado();
		}
	}

	private void salvarDados(String caminho) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(caminho)))) {
			bw.write("matrícula;nome;email;senha;cargo;turma;setor\n");
			for (String[] dado : dados) {
				bw.write(String.join(";", dado));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new TabelaGUI().setVisible(true);
		});
	}
}
