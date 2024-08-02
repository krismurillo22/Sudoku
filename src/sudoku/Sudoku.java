/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudoku;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Sudoku extends JFrame {

    private JPanel panelTablero;
    
    public Sudoku() {
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        panelTablero = new JPanel();
        add(panelTablero);
        panelTablero.setLayout(new GridLayout(9, 9));

        setVisible(true);
    }  
       
    
    public static void main(String[] args) {
        new Sudoku();
        
    }
    
}
