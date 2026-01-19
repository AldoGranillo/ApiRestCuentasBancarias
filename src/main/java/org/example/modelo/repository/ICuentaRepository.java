package org.example.modelo.repository;

import org.example.modelo.Cuentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuentas, Integer> {

    @Query("FROM Cuentas WHERE numero_cuenta=:pnumc")
    Cuentas findByNumeroCuenta(@Param("pnumc") String pnumc);
}
