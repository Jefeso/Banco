package br.edu.fatecpg.sistemaBancario.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.fatecpg.sistemaBancario.model.ContaCorrente;
import br.edu.fatecpg.sistemaBancario.model.ContaPoupanca;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private ContaCorrente contCt = new ContaCorrente(001, 5000.00, "Jefferson", 500);
	private ContaPoupanca contBc = new ContaPoupanca(002, 1000.00, "Murilo");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(195, 38, 126, 102);
		contentPane.add(textArea);

		textField = new JTextField();
		textField.setText("...");
		textField.setBounds(63, 120, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Conta Corrente", "Conta Poupanca" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(59, 43, 107, 20);
		contentPane.add(comboBox);

		JLabel Conta = new JLabel("Conta");
		Conta.setBounds(103, 11, 46, 14);
		contentPane.add(Conta);

		JButton btnCont = new JButton("Confirmar");
		btnCont.setBounds(69, 70, 86, 14);
		contentPane.add(btnCont);

		JButton btnDeposito = new JButton("Deposito");
		btnDeposito.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnDeposito.setBounds(75, 161, 74, 23);
		contentPane.add(btnDeposito);

		JButton btnTransferencia = new JButton("Transferencia");
		btnTransferencia.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnTransferencia.setBounds(159, 161, 78, 23);
		contentPane.add(btnTransferencia);

		JButton btnSaque = new JButton("Saque");
		btnSaque.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSaque.setBounds(10, 160, 62, 23);
		contentPane.add(btnSaque);

		// Action listener para o botão Confirmar, que exibe os detalhes da conta selecionada
		btnCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contaSelecionada = (String) comboBox.getSelectedItem();
				if ("Conta Corrente".equals(contaSelecionada)) {
					textArea.setText("Nome: " + contCt.getNome() + "\nSaldo: " + contCt.getSaldo() + "\nLimite: " + contCt.getLimitCheck());
				} else if ("Conta Poupanca".equals(contaSelecionada)) {
					textArea.setText("Nome: " + contBc.getNome() + "\nSaldo: " + contBc.getSaldo());
				}
			}
		});

		// Action listener para o botão Deposito
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(textField.getText());
					String contaSelecionada = (String) comboBox.getSelectedItem();
					if ("Conta Corrente".equals(contaSelecionada)) {
						contCt.deposito(valor);
						textArea.setText("Depósito realizado. Saldo atual: " + contCt.getSaldo());
					} else if ("Conta Poupanca".equals(contaSelecionada)) {
						contBc.deposito(valor);
						textArea.setText("Depósito realizado. Saldo atual: " + contBc.getSaldo());
					}
				} catch (NumberFormatException ex) {
					textArea.setText("Valor inválido.");
				}
			}
		});

		// Action listener para o botão Saque
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(textField.getText());
					String contaSelecionada = (String) comboBox.getSelectedItem();
					if ("Conta Corrente".equals(contaSelecionada)) {
						if (contCt.sacar(valor)) {
							textArea.setText("Saque realizado. Saldo atual: " + contCt.getSaldo());
						} else {
							textArea.setText("Saldo insuficiente.");
						}
					} else if ("Conta Poupanca".equals(contaSelecionada)) {
						textArea.setText("Operação não permitida para Conta Poupança.");
					}
				} catch (NumberFormatException ex) {
					textArea.setText("Valor inválido.");
				}
			}
		});

		// Action listener para o botão Transferencia
		btnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(textField.getText());
					String contaSelecionada = (String) comboBox.getSelectedItem();
					if ("Conta Corrente".equals(contaSelecionada)) {
						if (contCt.transferencia(contBc, valor)) {
							textArea.setText("Transferência realizada para Conta Poupança. Saldo atual: " + contCt.getSaldo());
						} else {
							textArea.setText("Saldo insuficiente.");
						}
					} else if ("Conta Poupanca".equals(contaSelecionada)) {
						if (contBc.transferencia(contCt, valor)) {
							textArea.setText("Transferência realizada para Conta Corrente. Saldo atual: " + contBc.getSaldo());
						} else {
							textArea.setText("Saldo insuficiente.");
						}
					}
				} catch (NumberFormatException ex) {
					textArea.setText("Valor inválido.");
				}
			}
		});
	}
}
