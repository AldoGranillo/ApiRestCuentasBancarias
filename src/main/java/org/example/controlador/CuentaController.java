package org.example.controlador;

import org.example.dtos.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.logica.CapaService;
import org.example.dtos.Respuesta;
import org.example.modelo.Cuentas;

@RestController
@RequestMapping("/api")
public class CuentaController {

    private CapaService capaService;

    @Autowired
    public CuentaController(CapaService capaService) {
        this.capaService = capaService;
    }

    @PostMapping("/cuentas")
    public ResponseEntity<Respuesta> crearCuentas(@RequestBody Request cuenta) {
        Respuesta estadoDTO = new Respuesta();
        if (cuenta != null) {
            estadoDTO=(capaService.altaCuenta(cuenta));
            if (estadoDTO.getEstatus() == 200) {
                return ResponseEntity.ok().body(estadoDTO);
            }
        }
        return ResponseEntity.badRequest().body(estadoDTO);
    }

    @GetMapping("/cuentas/{numeroCuentas}")
    public ResponseEntity<Respuesta> consultaCuenta(@PathVariable String numeroCuentas) {
        Respuesta estadoDTO = new Respuesta();
        if (!numeroCuentas.isEmpty()) {
            estadoDTO = capaService.consultaCuentas(numeroCuentas);
            if(estadoDTO.getEstatus() == 200) {
                return ResponseEntity.ok().body(estadoDTO);
            }
        }
        return ResponseEntity.badRequest().body(estadoDTO);
    }

    @PutMapping("/cuentas/{numeroCuenta}/deposito")
    public ResponseEntity<Respuesta> depositarSaldo(@PathVariable String numeroCuenta, @RequestBody Request deposito) {
        Respuesta estadoDTO = new Respuesta();
        if (numeroCuenta != null ) {
            estadoDTO=capaService.deposito(numeroCuenta, deposito);
            if (estadoDTO.getEstatus() == 200) {
                return ResponseEntity.ok().body(estadoDTO);
            }
        }
        return ResponseEntity.badRequest().body(estadoDTO);
    }

    @PutMapping("/cuentas/{numeroCuenta}/retiro")
    public ResponseEntity<Respuesta> retirarSaldo(@PathVariable String numeroCuenta, @RequestBody Request retiro) {
        Respuesta estadoDTO = new Respuesta();
        if (numeroCuenta != null) {
            estadoDTO = capaService.retiro(numeroCuenta, retiro);
            if (estadoDTO.getEstatus() == 200) {
                return ResponseEntity.ok().body(estadoDTO);
            }
        }
        return ResponseEntity.badRequest().body(estadoDTO);
    }
}
