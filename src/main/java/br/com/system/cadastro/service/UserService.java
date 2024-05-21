package br.com.system.cadastro.service;

import br.com.system.cadastro.dto.UserDto;
import br.com.system.cadastro.entity.User;
import br.com.system.cadastro.repository.UserRepository;
import br.com.system.cadastro.util.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public UserDto getUserById(Long id) {
        Optional<User> userOp =  userRepository.findById(id);

        if (userOp.isPresent()) {
            return convertToDTO(userOp.get());
        }
        throw new NotFoundException("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }

    @Transactional
    public UserDto save(@Valid UserDto userDto) {

        User entity = factoryUser(userDto);

        entity = userRepository.save(entity);

        return new UserDto(entity);
    }

    @Transactional
    public UserDto update(Long id, @Validated(UserDto.Update.class) UserDto userDto) {
        Optional<User> opUser = userRepository.findById(id);
        if (opUser.isPresent()) {
            User entity = opUser.get();

            validateForUpdateUser(userDto,entity);

            return new UserDto(userRepository.save(entity));
        } else {
            throw new NotFoundException("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    private void validateForUpdateUser(UserDto userDto,User entity){
        if (userDto.getName() != null) {
            entity.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            entity.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            entity.setPassword(userDto.getPassword());
        }
    }

    @Transactional
    public boolean deleteUser(Long id) {
        Optional<User> usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            userRepository.delete(usuarioOptional.get());
            return true;
        } else {
            return false;
        }
    }

    private User factoryUser(UserDto userDto) {
        return User.builder().
                name(userDto.getName()).
                email(userDto.getEmail()).
                id(userDto.getId()).
                password(userDto.getPassword()).
                createdAt(Instant.now()).
                updatedAt(Instant.now()).build();

    }

    private UserDto convertToDTO(User user) {
        return new UserDto(user);
    }


}
