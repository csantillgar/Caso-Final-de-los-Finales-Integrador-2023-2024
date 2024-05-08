package modelo;

import java.util.ArrayList;
import java.util.List;

public class Twitter {

    private List<CuentaUsuario> usuarios;

    public Twitter() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(CuentaUsuario usuario) {
        this.usuarios.add(usuario);
    }

    public CuentaUsuario buscarUsuarioPorAlias(String alias) {
        for (CuentaUsuario usuario : usuarios) {
            if (usuario.getAlias().equals(alias)) {
                return usuario;
            }
        }
        return null;
    }

    // Otros métodos de Twitter: publicarTweet, obtenerTimeline, etc.
}
