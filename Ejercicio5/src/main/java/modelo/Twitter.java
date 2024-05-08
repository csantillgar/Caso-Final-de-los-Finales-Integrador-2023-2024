package modelo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


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

    public void publicarTweet(CuentaUsuario usuario, String mensaje) {
        if (usuario != null && mensaje != null && !mensaje.isEmpty()) {
            LocalDateTime fechaHora = LocalDateTime.now();
            Tweet tweet = new Tweet(mensaje, fechaHora, usuario);
            usuario.agregarTweet(tweet);
        }
    }


    public List<Tweet> obtenerTimeline(CuentaUsuario usuario) {
        List<Tweet> timeline = new ArrayList<>();
        if (usuario != null) {
            for (CuentaUsuario seguidor : usuario.getSeguidores()) {
                timeline.addAll(seguidor.getTweets());
            }
        }
        return timeline;
    }


}
