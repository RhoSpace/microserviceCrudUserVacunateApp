package com.vacuna.microserviciousuarios.utils;

import com.vacuna.microserviciousuarios.exceptions.AppInternalServerErrorException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ValidadorRut {

    public void validarRut(String rut) throws AppInternalServerErrorException {
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                log.info("El rut es valido");
            }

        } catch (Exception e) {
            log.error("El rut ingresado no es valido");
            throw new AppInternalServerErrorException("Rut ingresado no es valido");
        }
    }
}