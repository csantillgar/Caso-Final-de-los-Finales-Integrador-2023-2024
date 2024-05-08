package modelo;

import java.util.ArrayList;
import java.util.List;

public class CuentaUsuario {
    private String alias;
    private String email;
    private List<CuentaUsuario> seguidores;
    private List<Tweet> tweets;

    // Constructor
    public CuentaUsuario(String alias, String email) {
        this.alias = alias;
        this.email = email;
        this.seguidores = new ArrayList<>();
        this.tweets = new ArrayList<>();
    }

    // Getters y setters

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos para gestionar seguidores

    public void seguir(CuentaUsuario usuario) {
        if (!seguidores.contains(usuario)) {
            seguidores.add(usuario);
        }
    }

    public void dejarDeSeguir(CuentaUsuario usuario) {
        seguidores.remove(usuario);
    }

    // Métodos para publicar tweets

    public void publicarTweet(Tweet tweet) {
        tweets.add(tweet);
        notificarSeguidores(tweet);
    }

    private void notificarSeguidores(Tweet tweet) {
        for (CuentaUsuario seguidor : seguidores) {
            seguidor.recibirTweet(tweet);
        }
    }

    private void recibirTweet(Tweet tweet) {




        //falta implementar logica



    }
}
