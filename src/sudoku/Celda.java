/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;

/**
 *
 * @author User
 */
public abstract class Celda implements SudCelda {

    protected int valor;

    public Celda(int valor) {
        this.valor = valor;
    }
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public void limpiar() {
        this.valor = 0;
    }

    @Override
    public abstract boolean esValido();
}