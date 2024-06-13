package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface InveService {

    ArchivoRespuesta obtenerInve(String nombre, String anioMes) throws IOException;

    int getExistInfoInve(String anioMes);

}
