package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface IrreService {

    ArchivoRespuesta obtenerIrre(String nombre, String anioMes) throws IOException;

    int getExistInfoIrre(String anioMes);

}
