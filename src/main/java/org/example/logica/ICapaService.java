package org.example.logica;

import org.example.dtos.Request;
import org.example.modelo.Cuentas;

public interface ICapaService {

    public int altaCuenta(Cuentas cuentas);

    public Cuentas consultaCuentas(String numeroCuenta);

    public int deposito(String numeroCuenta, Request monto);

    public int retiro(String numeroCuenta, Request monto);

}
