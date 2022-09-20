package com.vacuna.microserviciousuarios.services;

import com.vacuna.microserviciousuarios.dto.request.UserRequestDto;
import com.vacuna.microserviciousuarios.dto.response.UserResponseDto;
import com.vacuna.microserviciousuarios.entity.User;
import com.vacuna.microserviciousuarios.exceptions.AppInternalServerErrorException;
import com.vacuna.microserviciousuarios.exceptions.AppNotFoundException;
import com.vacuna.microserviciousuarios.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
public class UserService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void ValidadorRut(String rut){}

    @Autowired
    private UserRepository userRepository;

    //Implementacion del metodo para guardar usuario
    public User saveuser(UserRequestDto requestDto) throws AppInternalServerErrorException {

        //declaro usuario
        User noSave = new User();
        noSave.setId(null);
        //compruebo si el rut es valido
        //ValidadorRut(requestDto.getRut());
        noSave.setRut(requestDto.getRut());
        noSave.setName(requestDto.getName());
        noSave.setPhone(requestDto.getPhone());
        noSave.setEmail(requestDto.getEmail());
        noSave.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        noSave.setRole(requestDto.isRole());

        //guardar en db
        User save = userRepository.save(noSave);
        log.info("Usuario guardado en base de datos");

        //respuesta
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setRut(save.getRut());
        responseDto.setName(save.getName());
        responseDto.setPhone(save.getPhone());
        responseDto.setEmail(save.getEmail());
        responseDto.setPassword(save.getPassword());
        responseDto.setRole(save.isRole());

        return noSave;
    }

    //Implementacion del metodo para buscar todos los usuarios
    public List<User> findAllUsers() {
        List<User> userList = (userRepository.findAll())
                .stream().collect(
                        Collectors.collectingAndThen(
                                Collectors.partitioningBy(User::isRole),
                                x -> Stream.of(x.get(false))
                                        .flatMap(List::stream)
                                        .collect(Collectors.toList())));
        return userList;
    }

    //Implementacion del metodo para buscar usuario por rut
    public User findUserRut(String rut) throws AppNotFoundException {
        User user = findByRut(rut);
        return user;
    }

    //Implementacion del metodo para buscar usuario por id
    public User findUserId(Long id) throws AppNotFoundException {
        User user = findById(id);
        return user;
    }

    //Implementacion del metodo para borrar usuario
    public void deleteUser(Long id) throws AppNotFoundException {
        User user = findUserId(id);
        userRepository.deleteById(id);
        log.warn("Se ha eliminado el usuario con id:" + id);
    }

    //Implementacion del metodo para actualizar usuario
    public User updateUser(Long id, UserRequestDto requestDto) throws AppNotFoundException, AppInternalServerErrorException {

        //Busco el id de usuario
        User NoUpdate = findById(id);

        //compruebo si el rut es valido

        //Se actualizan los atributos
        NoUpdate.setRut(requestDto.getRut());
        NoUpdate.setName(requestDto.getName());
        NoUpdate.setPhone(requestDto.getPhone());
        NoUpdate.setEmail(requestDto.getEmail());
        NoUpdate.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        //Actualizo el usuario
        User userUpdate = userRepository.save(NoUpdate);
        log.info("Usuario actualizado con exito");
        return userUpdate;
    }

    //Implementacion de busquedas por repositorio
    public User findById(Long id) throws AppNotFoundException {
        log.info("Buscando el rut: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new AppNotFoundException("No se ha encontrado al usuario con id:" + id));
    }

    public User findByRut(String rut) throws AppNotFoundException {
        log.info("Buscando el rut: {}", rut);
        return userRepository.findByRut(rut)
                .orElseThrow(() -> new AppNotFoundException("No se ha encontrado al usuario con rut:" + rut));
    }

    public int countVacinator() {
        return userRepository.getCountOfVaccinator();
    }
}