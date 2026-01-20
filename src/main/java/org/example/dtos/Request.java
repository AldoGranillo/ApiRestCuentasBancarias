package org.example.dtos;


public class Request {

    /**
     * Este sera el dto para enviar datos al end point
     */
    private String numero_cuenta;

    private String titular;

    private int saldo;

    private String estado;


    public Request() {
    }

    public Request(String numero_cuenta, String titular, int saldo, String estado) {
        this.numero_cuenta = numero_cuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.estado = estado;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
