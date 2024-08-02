/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author User
 */
public class Sudoku extends JFrame {

    private Tablero tablero;
    private JTextField[][] campos;

    public Sudoku() {
        tablero = new Tablero();
        campos = new JTextField[9][9];
        inicializarGUI();
    }

    private void inicializarGUI() {
        setTitle("Sudoku");
        setLayout(new GridLayout(3, 3));

        JPanel[][] panelesRegiones = new JPanel[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                panelesRegiones[i][j] = new JPanel(new GridLayout(3, 3));
                Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
                panelesRegiones[i][j].setBorder(border);
                add(panelesRegiones[i][j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                campos[i][j] = new JTextField();
                campos[i][j].setHorizontalAlignment(JTextField.CENTER);
                campos[i][j].setFont(new Font("Times New Roman", Font.BOLD, 20));
                campos[i][j].setEditable(false);
                campos[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JTextField campo = (JTextField) evt.getSource();
                        int fila = -1, col = -1;
                        boolean found = false;
                        for (int i = 0; i < 9 && !found; i++) {
                            for (int j = 0; j < 9 && !found; j++) {
                                if (campos[i][j] == campo) {
                                    fila = i;
                                    col = j;
                                    found = true;
                                }
                            }
                        }
                        if (found) {
                            if (tablero.esModificable(fila, col)) {
                                if (!campos[fila][col].getText().isEmpty()) {
                                    int respuesta = JOptionPane.showOptionDialog(
                                            null,
                                            "¿Deseas borrar el número?",
                                            "Confirmar",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            new Object[]{"Sí", "No"},
                                            "Sí"
                                    );
                                    if (respuesta == JOptionPane.YES_OPTION) {
                                        tablero.setValor(fila, col, 0);
                                        actualizarGUI();
                                    }
                                } else {
                                    abrirDialogoAgregarNumero(fila, col);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No se puede modificar un número inicial", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
                panelesRegiones[i / 3][j / 3].add(campos[i][j]);
            }
        }

        actualizarGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    private void abrirDialogoAgregarNumero(int fila, int col) {
        String numero = JOptionPane.showInputDialog(this, "Ingresa el número que quieres agregar:");
        if (numero != null) {
            try {
                int valor = Integer.parseInt(numero);
                if (valor < 1 || valor > 9) {
                    JOptionPane.showMessageDialog(this, "Error, número fuera de rango", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!tablero.esNumeroValido(fila, col, valor)) {
                    JOptionPane.showMessageDialog(this, "Error, número repetido", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tablero.setValor(fila, col, valor);
                    actualizarGUI();
                    if (tablero.esValido()) {
                        getContentPane().setBackground(Color.GREEN);
                    } else {
                        getContentPane().setBackground(Color.RED);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error, número inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarGUI() {
        boolean completo = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valor = tablero.getValor(i, j);
                campos[i][j].setText(valor == 0 ? "" : String.valueOf(valor));
                if (tablero.getCasilla(i, j).esGenerado()) {
                    campos[i][j].setForeground(Color.RED);
                } else {
                    campos[i][j].setForeground(Color.BLACK);
                }
                if (valor == 0) {
                    completo = false;
                }
            }
        }

        if (completo && tablero.esValido()) {
            JOptionPane.showMessageDialog(this, "¡Felicidades, ganaste, eres un crack!");
            System.exit(0);
        }
    }
}
