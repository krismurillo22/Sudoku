/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudoku;


/**
 *
 * @author User
*/
public class Casilla extends Celda {

    public Casilla(int valor) {
        super(valor);
    }

    public boolean esValido() {
        return valor >= 1 && valor <= 9;
    }
}
