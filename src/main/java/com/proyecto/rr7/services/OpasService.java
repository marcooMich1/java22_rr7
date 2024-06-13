package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface OpasService {

    ArchivoRespuesta obtenerOpas(String nombre) throws IOException;

    void updateOpas(int anio, int mes);

}
