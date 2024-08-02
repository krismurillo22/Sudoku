/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class Casillas {
    private int fila;
    private int col;
    JLabel label;
    private boolean SePueCambiar = true;
    
    public Casillas(int fila, int col) {
        this.fila = fila;
        this.col = col;
        this.label = new JLabel();
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setOpaque(true);
    }
    
    public int getFila() {
        return fila;
    }

    public int getCol() {
        return col;
    }

    public JLabel getLabel() {
        return label;
    }
    
    public void setNumeroActual(int numero) {
        if (numero == 0) {
            label.setText("");
            return;
        }
        label.setText("  "+numero);
    }
    public int getNumeroActual() {
        if (label.getText().equals("")){
            return -1;
        }
        return Integer.parseInt(label.getText());
    }
    
    public boolean SePueCambiar() {
        return SePueCambiar;
    }
    
    public void setCambiado(boolean SePueCambiar) {
        this.SePueCambiar = SePueCambiar;
    }
}
