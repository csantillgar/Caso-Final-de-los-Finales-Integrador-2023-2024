package utilidades;

public class Utilidades {
    public static boolean validarEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean validarAlias(String alias) {
        return alias != null && alias.matches("[a-zA-Z0-9]+");
    }
}
