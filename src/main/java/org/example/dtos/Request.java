package org.example.dtos;


public class Request {

    /**
     * Este sera el dto para enviar datos al end point
     */
    private String numeroCuenta;

    private String titular;

    private int saldo;

    private String estado;


    public Request() {
    }

    public Request(String numero_cuenta, String titular, int saldo, String estado) {
        this.numeroCuenta = numero_cuenta;
        this.titular = titular;
        this.saldo = saldo;
        this.estado = estado;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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
