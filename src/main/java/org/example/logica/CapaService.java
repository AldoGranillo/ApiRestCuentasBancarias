package org.example.logica;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.example.dtos.Request;
import org.example.dtos.Respuesta;
import org.example.modelo.Cuentas;
import org.example.modelo.repository.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapaService implements ICapaService {

    private ICuentaRepository iCuentaRepository;

    @Autowired
    public CapaService(ICuentaRepository iCuentaRepository) {
        this.iCuentaRepository = iCuentaRepository;
    }

    @Transactional
    @Override
    public Respuesta altaCuenta(Request cuenta) {
        Respuesta respuesta = new Respuesta();
        Cuentas cuentas = new Cuentas();
        cuentas.setNumero_cuenta(cuenta.getNumeroCuenta());
        cuentas.setEstado(cuenta.getEstado());
        cuentas.setSaldo(cuenta.getSaldo());
        cuentas.setTitular(cuenta.getTitular());

        if(!iCuentaRepository.existsByNumeroCuenta(cuenta.getNumeroCuenta())) {
            //Si no existe se guarda
            iCuentaRepository.save(cuentas);
            respuesta.setEstatus(200);
        }else {
            respuesta.setEstatus(400);
            System.out.println("Esta cuenta ya existe");
        }
        return respuesta;
    }

    @Override
    public Respuesta consultaCuentas(String numeroCuenta) {
        Respuesta respuesta = new Respuesta();
        /**
         * Aca se deberia modelar el dto para no exponerlo en el controler
         */
        Cuentas nuevaCuenta = iCuentaRepository.findByNumeroCuenta(numeroCuenta);
        respuesta.setObjeto(nuevaCuenta);
        respuesta.setEstatus(200);

        return respuesta;
    }

    @Override
    public Respuesta deposito(String numeroCuenta, Request monto) {
        Respuesta respuesta = new Respuesta();
        //Traemos cuenta
        Cuentas nuevaCuenta = iCuentaRepository.findByNumeroCuenta(numeroCuenta);
        //Vamos a depositar, no hay restriccion respecto a nuestro saldo actual

        if(monto.getSaldo()!= 0) { //para evitar que este vacio
            Integer saldoNuevo = nuevaCuenta.getSaldo() + monto.getSaldo();
            nuevaCuenta.setSaldo(saldoNuevo);
            iCuentaRepository.save(nuevaCuenta);
            respuesta.setEstatus(200);
        }
        return respuesta;
    }

    @Override
    public Respuesta retiro(String numeroCuenta, Request monto) { // monto --> cantidad a retirar
        Respuesta respuesta = new Respuesta();

        /*
         * Obtenemos la cuenta correspondiente a el numero de cueta
         */
        Optional<Cuentas> result = iCuentaRepository.findById(iCuentaRepository.findByNumeroCuenta(numeroCuenta).getId());

        // Si la cuenta existe entonces traemos los datos
        if (result.isPresent()) {
            // Traemos los datos
            int saldo = result.get().getSaldo();
            // Se va a hacer un retiro entonces a la cantidad que tenemos se le resta la
            // cantidad que va a sacar el cliente
            if (saldo >= monto.getSaldo()) { // debemos tener mas dinero que lo que vamos a sacar
                //tambien el monto no debe ser mayor al saldo que tenemos
                int saldoNuevo = saldo - monto.getSaldo();
                result.get().setSaldo(saldoNuevo);
                iCuentaRepository.save(result.get());
                respuesta.setEstatus(200);

                return respuesta;
            } else {
                System.out.println("Saldo insuficiente : $" + saldo + ".00");
                respuesta.setEstatus(400);
                return respuesta;
            }

        }
        return respuesta;
    }
}
