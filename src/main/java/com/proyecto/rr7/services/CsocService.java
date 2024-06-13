package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface CsocService {

    ArchivoRespuesta obtenerCsoc(String nombre) throws IOException;

    void updateCsoc(int anio, int mes);

}
