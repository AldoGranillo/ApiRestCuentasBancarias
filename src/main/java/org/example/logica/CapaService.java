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
    public int altaCuenta(Cuentas cuenta) {
        Respuesta estadoDTO = new Respuesta();
        iCuentaRepository.save(cuenta);
        estadoDTO.setEstatus(0);
        System.out.println(iCuentaRepository.save(cuenta));
        return 1;
    }

    @Override
    public Cuentas consultaCuentas(String numeroCuenta) {
        /**
         * Aca se deberia modelar el dto para no exponerlo en el controler
         */
        Cuentas nuevaCuenta = iCuentaRepository.findByNumeroCuenta(numeroCuenta);

        return nuevaCuenta;
    }

    @Override
    public int deposito(String numeroCuenta, Request monto) {
        //Traemos cuenta
        Cuentas nuevaCuenta = iCuentaRepository.findByNumeroCuenta(numeroCuenta);
        //Vamos a depositar, no hay restriccion respecto a nuestro saldo actual
        if(monto.getSaldo()!= 0) { //para evitar que este vacio
            Integer saldoNuevo = nuevaCuenta.getSaldo() + monto.getSaldo();
            nuevaCuenta.setSaldo(saldoNuevo);
        }
        return 0;
    }

    @Override
    public int retiro(String numeroCuenta, Request monto) { // monto --> cantidad a retirar
        Respuesta estadoDTO = new Respuesta();

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
                return 1;
            } else {
                System.out.println("Saldo insuficiente : $" + saldo + ".00");
                return 0;
            }

        }
        return 0;
    }
}
