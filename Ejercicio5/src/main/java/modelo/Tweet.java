package modelo;

import java.time.LocalDate;

public class Tweet {
    private LocalDate fechaPublicacion;
    private String mensaje;
    private CuentaUsuario remitente;

    public Tweet(LocalDate fechaPublicacion, String mensaje, CuentaUsuario remitente) {
        this.fechaPublicacion = fechaPublicacion;
        this.mensaje = mensaje;
        this.remitente = remitente;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
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
}
