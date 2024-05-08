package modelo;

import java.util.ArrayList;
import java.util.List;

public class CuentaUsuario {
    private String alias;
    private String email;
    private List<CuentaUsuario> seguidores;
    private List<CuentaUsuario> siguiendo;
    private List<Tweet> tweets;

    // Constructor
    public CuentaUsuario(String alias, String email) {
        this.alias = alias;
        this.email = email;
        this.seguidores = new ArrayList<>();
        this.siguiendo = new ArrayList<>();
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

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<CuentaUsuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<CuentaUsuario> seguidores) {
        this.seguidores = seguidores;
    }

    public List<CuentaUsuario> getSiguiendo() {
        return siguiendo;
    }

    public void setSiguiendo(List<CuentaUsuario> siguiendo) {
        this.siguiendo = siguiendo;
    }

    public void seguir(CuentaUsuario usuario) {
        if (!this.siguiendo.contains(usuario)) {
            this.siguiendo.add(usuario);
            usuario.agregarSeguidor(this);
        }
    }


    public void dejarDeSeguir(CuentaUsuario usuario) {
        seguidores.remove(usuario);
    }

    // Métodos para publicar tweets

    public void agregarTweet(Tweet tweet) {
        this.tweets.add(tweet);
        this.notificarSeguidores(tweet);
    }

    private void notificarSeguidores(Tweet tweet) {
        for (CuentaUsuario seguidor : this.seguidores) {
            seguidor.recibirTweet(tweet);
        }
    }

    private void recibirTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }

    private void agregarSeguidor(CuentaUsuario seguidor) {
        this.seguidores.add(seguidor);
    }

    @Override
    public String toString() {
        return "CuentaUsuario{" +
                "alias='" + alias + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

