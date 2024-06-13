package com.proyecto.rr7.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CCArchivosRR7 implements Serializable {

    private int id;
    private String infoCargada;
    private String nombreSimplificado;
    private String nombreExtendido;

}
