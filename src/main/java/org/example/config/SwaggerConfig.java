package org.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpHeaders;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Cuentas bancarias",
                description = "Microservicio para gestion de cuentas bancarias",
                version = "0.0.1"
        ),
        servers = @Server(
                //solo dev porque no hay prod
                description = "Servidor de desarrollo",
                url = "http://127.0.0.1:8080"
        )
//        security =@SecurityRequirement(
//                name = "Token de seguridad"
//        )
)
//@SecurityScheme(
//        name = "Token de seguridad",
//        description = "Token de acceso para la api",
//        type = SecuritySchemeType.DEFAULT
//        /**
//         * no se porque pero dio error en esto
//        type = SecuritySchemeType.HTTP, //--> para tokens
//        paramName = HttpHeaders,
//        in = SecuritySchemIn.HEADER
//         */
//)
public class SwaggerConfig {
}
