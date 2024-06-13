package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "RR7ARCHIVOHISTORICO")
@NamedQuery(name = "RR7ARCHIVOHISTORICO.findAll", query = "SELECT r FROM Rr7ArchivoHistorico r")
@Getter
@Setter
public class Rr7ArchivoHistorico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FECHAREGISTRO")
    private String fechaRegistro;

    @Column(name = "BASE64RR7")
    private String base64Rr7;

    @Column(name = "ANIO")
    private int anio;

    @Column(name = "IDARCHIVO")
    private int idArchivo;

    @Column(name = "MES")
    private int mes;

}
