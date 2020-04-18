package com.dai.eventos.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String DIRETOR = "ROLE_DIRETOR";
    public static final String RESPONSAVEL_CENTRAL_COMANDOS = "ROLE_RESPONSAVEL_CENTRAL_COMANDOS";
    public static final String RESPONSAVEL_SEGURANCA = "ROLE_RESPONSAVEL_SEGURANCA";
    public static final String GUARDA_PRISIONAL = "ROLE_GUARDA_PRISIONAL";
    public static final String ADMINISTRADOR_SISTEMA = "ROLE_ADMINISTRADOR_SISTEMA";

    private AuthoritiesConstants() {
    }
}
