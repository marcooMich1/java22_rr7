package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface CcmpService {

    ArchivoRespuesta obtenerCcmp(String nombre, String anioMes) throws IOException;

    int getExistInfoCcmp(String anioMes);

}
