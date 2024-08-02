/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

/**
 *
 * @author User
 */
public class Region implements SudCelda {

    private Casilla[][] celdas;

    public Region() {
        celdas = new Casilla[3][3];
        inicializarCeldas();
    }

    private void inicializarCeldas() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                celdas[i][j] = new Casilla(0);
            }
        }
    }

    public void setCelda(int fila, int col, Casilla celda) {
        celdas[fila][col] = celda;
    }

    public Casilla getCelda(int fila, int col) {
        return celdas[fila][col];
    }

    public boolean esValido() {
        boolean[] vistos = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int valor = celdas[i][j].getValor();
                if (valor != 0 && vistos[valor]) {
                    return false;
                }
                vistos[valor] = true;
            }
        }
        return true;
    }

    public void limpiar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                celdas[i][j].limpiar();
            }
        }
    }
}
