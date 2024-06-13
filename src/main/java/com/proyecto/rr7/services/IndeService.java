package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface IndeService {

    ArchivoRespuesta obtenerInde(String nombre, String anioMes) throws IOException;

    int getExistInfoInde(String anioMes);

}
