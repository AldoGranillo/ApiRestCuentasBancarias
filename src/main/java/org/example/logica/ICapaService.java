package org.example.logica;

import org.example.dtos.Request;
import org.example.dtos.Respuesta;
import org.example.modelo.Cuentas;

public interface ICapaService {

    public Respuesta altaCuenta(Request cuentas);

    public Respuesta consultaCuentas(String numeroCuenta);

    public Respuesta deposito(String numeroCuenta, Request monto);

    public Respuesta retiro(String numeroCuenta, Request monto);

}
