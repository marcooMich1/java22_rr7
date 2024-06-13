package com.proyecto.rr7.util;

public class ResponseMessage {

    private String mensaje;
    private String detalleMensaje;
    private Object dataExtra;

    public ResponseMessage(String mensaje, String detalleMensaje, Object dataExtra) {
        this.mensaje = mensaje;
        this.detalleMensaje = detalleMensaje;
        this.dataExtra = dataExtra;
    }

    public ResponseMessage() {
        super();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalleMensaje() {
        return detalleMensaje;
    }

    public void setDetalleMensaje(String detalleMensaje) {
        this.detalleMensaje = detalleMensaje;
    }

    public Object getDataExtra() {
        return dataExtra;
    }

    public void setDataExtra(Object dataExtra) {
        this.dataExtra = dataExtra;
    }

}
