package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface InmuService {

    ArchivoRespuesta obtenerInmu(String nombre, String anioMes) throws IOException;

    int getExistInfoInmu(String anioMes);

}
