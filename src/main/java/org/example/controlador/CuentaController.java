package org.example.controlador;

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
import org.example.dtos.EstadoDTO;
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
    public ResponseEntity<EstadoDTO> crearCuentas(@RequestBody Cuentas cuenta) {
        EstadoDTO estadoDTO = new EstadoDTO();
        System.out.println(cuenta + "Entrando al if");
        if (cuenta != null) {
            System.out.println("Estamos dentro del if");
            estadoDTO.setEstatus(capaService.altaCuenta(cuenta));
            return ResponseEntity.ok().body(estadoDTO);
        }
        return ResponseEntity.badRequest().body(estadoDTO);
    }

    @GetMapping("/cuentas/{numeroCuentas}")
    public ResponseEntity<EstadoDTO> consultaCuenta(@PathVariable String numeroCuentas) {
        EstadoDTO estadoDTO = new EstadoDTO();
        if (!numeroCuentas.isEmpty()) {
            estadoDTO.setObjeto(capaService.consultaCuentas(numeroCuentas));
            return ResponseEntity.ok().body(estadoDTO);
        }
        return ResponseEntity.badRequest().body(estadoDTO);
    }

    @PutMapping("/cuentas/{numeroCuenta}/deposito")
    public ResponseEntity<EstadoDTO> depositarSaldo(
            @PathVariable String numeroCuenta, @RequestBody int deposito) {
        EstadoDTO estadoDTO = new EstadoDTO();
        if (numeroCuenta != null && deposito != 0) {
            estadoDTO.setEstatus(capaService.deposito(numeroCuenta, deposito));
            return ResponseEntity.ok().body(estadoDTO);
        }

        return ResponseEntity.badRequest().body(estadoDTO);
    }

    @PutMapping("/cuentas/{numeroCuenta}/retiro")
    public ResponseEntity<EstadoDTO> retirarSaldo(
            @PathVariable String numeroCuenta, @RequestBody int retiro) {
        EstadoDTO estadoDTO = new EstadoDTO();
        if (numeroCuenta != null && retiro != 0) {
            estadoDTO.setEstatus(capaService.retiro(numeroCuenta, retiro));
            return ResponseEntity.ok().body(estadoDTO);
        }
        return ResponseEntity.badRequest().body(estadoDTO);
    }
}
