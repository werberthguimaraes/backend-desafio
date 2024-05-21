package br.com.system.cadastro.dto;

import br.com.system.cadastro.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    public interface Create {}
    public interface Update {}

    private Long id;

    @NotBlank(message = "Nome é obrigatório", groups = {Create.class, Update.class})
    @Size(min = 3, max = 50, message = "Nome do Usuário precisa ter de 3 a 50 caracteres")
    private String name;

    @NotBlank(message = "Email é obrigatório", groups = {Create.class, Update.class})
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória", groups = {Create.class, Update.class})
    @Size(min = 6, max = 20, message = "Senha precisa ter de 6 a 20 caracteres")
    private String password;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserDto(){

    }

}
