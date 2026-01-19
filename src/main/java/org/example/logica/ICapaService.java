package org.example.logica;

import org.example.modelo.Cuentas;

public interface ICapaService {

    public int altaCuenta(Cuentas cuentas);

    public Cuentas consultaCuentas(String numeroCuenta);

    public int deposito(String numeroCuenta, int monto);

    public int retiro(String numeroCuenta, int monto);

}
