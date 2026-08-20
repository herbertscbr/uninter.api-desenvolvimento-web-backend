package com.uninter.ads.back_end;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@OpenAPIDefinition(
    info = @Info(
        title = "::Livraria::",
        version = "1.0.0",
        description = "API Spring desenvolvida com fins didáticos para a disciplina de Desenvolvimento Web - Backend / Uninter",
        contact = @Contact(
            name = "Herbert",
            url = "http://localhost:8080/api/swagger-ui/index.html"
        )
    )
)
@SecurityScheme(
    name = "JWT",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "caso a API fosse protegida, iria inserir aqui o JWT"
)
public class OpenApiConfig {
}
