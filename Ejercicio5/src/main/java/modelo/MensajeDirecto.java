package modelo;

import java.time.LocalDate;

public class MensajeDirecto extends Tweet {
    private CuentaUsuario destinatario;

    public MensajeDirecto(LocalDate fechaPublicacion, String mensaje, CuentaUsuario remitente, CuentaUsuario destinatario) {
        super(fechaPublicacion, mensaje, remitente);
        this.destinatario = destinatario;
    }

    public CuentaUsuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(CuentaUsuario destinatario) {
        this.destinatario = destinatario;
    }
}
