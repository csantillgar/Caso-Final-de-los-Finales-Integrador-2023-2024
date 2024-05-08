package modelo;

import java.time.LocalDate;

public class Retweet extends Tweet {
    private Tweet tweetOriginal;

    public Retweet(LocalDate fechaPublicacion, String mensaje, CuentaUsuario remitente, Tweet tweetOriginal) {
        super(fechaPublicacion, mensaje, remitente);
        this.tweetOriginal = tweetOriginal;
    }

    public Tweet getTweetOriginal() {
        return tweetOriginal;
    }

    public void setTweetOriginal(Tweet tweetOriginal) {
        this.tweetOriginal = tweetOriginal;
    }
}
