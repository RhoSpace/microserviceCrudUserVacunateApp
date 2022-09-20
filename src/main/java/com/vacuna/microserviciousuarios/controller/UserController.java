package com.vacuna.microserviciousuarios.controller;

import com.vacuna.microserviciousuarios.dto.request.UserRequestDto;
import com.vacuna.microserviciousuarios.entity.User;
import com.vacuna.microserviciousuarios.exceptions.AppInternalServerErrorException;
import com.vacuna.microserviciousuarios.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    //Guardar usuario
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody UserRequestDto requestDto) throws AppInternalServerErrorException {
        User save;
        try {
            save = userService.saveuser(requestDto);

        } catch (Exception e) {
            throw new AppInternalServerErrorException("No se ha guardado el usuario");
        }
        return ResponseEntity.ok(save);
    }

    //Listar usuario
    @GetMapping("/list")
    public ResponseEntity<List<User>> findAllUsers() throws AppInternalServerErrorException {
        try {
            return ResponseEntity.ok(userService.findAllUsers());

        } catch (Exception e) {
            throw new AppInternalServerErrorException("No se han listado los usuarios");
        }
    }

    //Buscar por Rut
    @GetMapping("/find/rut/")
    public ResponseEntity<User> findUserRut(@RequestParam String rut) throws AppInternalServerErrorException {
        try {
            return ResponseEntity.ok(userService.findUserRut(rut));
        } catch (Exception e) {
            throw new AppInternalServerErrorException("No se ha encontrado el usuario con el rut:" + rut);
        }
    }

    //Buscar por id
    @GetMapping("/find/id/")
    public ResponseEntity<User> findUserId(@RequestParam Long id) throws AppInternalServerErrorException {
        try {
            return ResponseEntity.ok(userService.findUserId(id));
        } catch (Exception e) {
            throw new AppInternalServerErrorException("No se ha encontrado el usuario con el id:" + id);
        }
    }

    //Modificar usuario
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam Long id, @RequestBody UserRequestDto requestDto) throws AppInternalServerErrorException {
        try {
            log.info("Intentando eliminar el usuario:" + id);
            User update = userService.updateUser(id, requestDto);
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            throw new AppInternalServerErrorException("No se ha guardado el usuario");
        }
    }

    //Eliminar usuario
    @DeleteMapping("/")
    public ResponseEntity<User> deleteUser(@RequestParam Long id) throws AppInternalServerErrorException {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new AppInternalServerErrorException("No se ha eliminado el usuario");
        }
    }

    //Numero de vacunadores
    @GetMapping("/count")
    public int CoutVaccinator() {
        return userService.countVacinator();
    }
}