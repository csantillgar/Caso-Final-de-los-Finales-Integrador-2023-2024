package modelo;

import java.time.LocalDateTime;

public class MensajeDirecto extends Tweet {
    private CuentaUsuario destinatario;

    public MensajeDirecto(LocalDateTime fechaPublicacion, String mensaje, CuentaUsuario remitente, CuentaUsuario destinatario) {
        super(mensaje, fechaPublicacion, remitente);
        this.destinatario = destinatario;
    }

    public CuentaUsuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(CuentaUsuario destinatario) {
        this.destinatario = destinatario;
    }
    @Override
    public String toString() {
        return "DirectMessage{" +
                "mensaje='" + this.getMensaje() + '\'' +
                ", fechaPublicacion=" + this.getFechaPublicacion() +
                ", remitente=" + this.getRemitente().getAlias() +
                ", destinatario=" + destinatario.getAlias() +
                '}';
    }
}
