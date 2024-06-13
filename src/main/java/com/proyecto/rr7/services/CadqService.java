package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.BaseLayoutRr7;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;
import java.util.List;

public interface CadqService {

    ArchivoRespuesta obtenerCadq(String nombre) throws IOException;

    ResponseMessage updateCadq(List<BaseLayoutRr7> getBase, int anio, int mes);

}
