package modelo;

import java.time.LocalDateTime;

public class Retweet extends Tweet {
    private Tweet tweetOriginal;

    public Retweet(LocalDateTime fechaPublicacion, String mensaje, CuentaUsuario remitente, Tweet tweetOriginal) {
        super(mensaje, fechaPublicacion, remitente);
        this.tweetOriginal = tweetOriginal;
    }

    public Tweet getTweetOriginal() {
        return tweetOriginal;
    }

    public void setTweetOriginal(Tweet tweetOriginal) {
        this.tweetOriginal = tweetOriginal;
    }
    @Override
    public String toString() {
        return "Retweet{" +
                "mensaje='" + this.getMensaje() + '\'' +
                ", fechaPublicacion=" + this.getFechaPublicacion() +
                ", remitente=" + this.getRemitente().getAlias() +
                ", tweetOriginal=" + tweetOriginal.getMensaje() +
                '}';
    }
}
