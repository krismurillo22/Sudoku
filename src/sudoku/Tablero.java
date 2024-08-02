/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

import java.util.Random;

/**
 *
 * @author User
 */
public class Tablero implements SudCelda{
   private Casilla[][] tablero;
    private Region[][] regiones;

    public Tablero() {
        tablero = new Casilla[9][9];
        regiones = new Region[3][3];
        inicializarTablero();
        rellenarTableroConNumerosAleatorios();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablero[i][j] = new Casilla(0);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                regiones[i][j] = new Region();
                for (int fila = 0; fila < 3; fila++) {
                    for (int col = 0; col < 3; col++) {
                        regiones[i][j].setCelda(fila, col, tablero[i * 3 + fila][j * 3 + col]);
                    }
                }
            }
        }
    }

    private void rellenarTableroConNumerosAleatorios() {
        Random rand = new Random();
        int numIniciales = 20 + rand.nextInt(11); // Entre 20 y 30 celdas

        for (int n = 0; n < numIniciales; n++) {
            int fila, col, valor;
            do {
                fila = rand.nextInt(9);
                col = rand.nextInt(9);
                valor = 1 + rand.nextInt(9);
            } while (tablero[fila][col].getValor() != 0 || !esNumeroValido(fila, col, valor));

            tablero[fila][col].setValor(valor);
        }
        System.out.println("Cantidad de celdas llenas: " + numIniciales);
    }

    public boolean esNumeroValido(int fila, int col, int valor) {
        for (int i = 0; i < 9; i++) {
            if (tablero[fila][i].getValor() == valor || tablero[i][col].getValor() == valor) {
                return false;
            }
        }
        int regionFila = fila / 3;
        int regionCol = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (regiones[regionFila][regionCol].getCelda(i, j).getValor() == valor) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean esValido() {
        for (int i = 0; i < 9; i++) {
            boolean[] filaVistos = new boolean[10];
            boolean[] colVistos = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int valorFila = tablero[i][j].getValor();
                int valorCol = tablero[j][i].getValor();
                if ((valorFila != 0 && filaVistos[valorFila]) || (valorCol != 0 && colVistos[valorCol])) {
                    return false;
                }
                filaVistos[valorFila] = true;
                colVistos[valorCol] = true;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!regiones[i][j].esValido()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void limpiar() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablero[i][j].limpiar();
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                regiones[i][j].limpiar();
            }
        }
    }

    public void setValor(int fila, int col, int valor) {
        tablero[fila][col].setValor(valor);
    }

    public int getValor(int fila, int col) {
        return tablero[fila][col].getValor();
    }
}
