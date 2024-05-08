package modelo;

import java.time.LocalDateTime;

public class Tweet {
    private LocalDateTime fechaPublicacion;
    private String mensaje;
    private CuentaUsuario remitente;

    public Tweet(String mensaje, LocalDateTime fechaPublicacion, CuentaUsuario remitente) {
        this.mensaje = mensaje;
        this.fechaPublicacion = fechaPublicacion;
        this.remitente = remitente;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }



    public CuentaUsuario getRemitente() {
        return remitente;
    }

    public void setRemitente(CuentaUsuario remitente) {
        this.remitente = remitente;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "mensaje='" + mensaje + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", remitente=" + remitente.getAlias() +
                '}';
    }
}
