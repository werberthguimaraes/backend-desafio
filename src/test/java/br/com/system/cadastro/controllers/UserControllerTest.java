package br.com.system.cadastro.controllers;

import br.com.system.cadastro.dto.UserDto;
import br.com.system.cadastro.entity.User;
import br.com.system.cadastro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {

        User user = User.builder().id(1L).name("Tony").build();

      //  when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));
        ResponseEntity<List<UserDto>> response = usuarioController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Teste", response.getBody().get(0).getName());
    }

    @Test
    void testGetUserById() {
        User user = User.builder().id(1L).name("Tony").build();

     //   when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        ResponseEntity<UserDto> response = usuarioController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Teste", response.getBody().getName());
    }

}
