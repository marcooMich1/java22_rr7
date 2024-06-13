package com.proyecto.rr7.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ArchivoRespuesta implements Serializable {

    private String baseArchivo;
    private String nombreArchivo;

}
