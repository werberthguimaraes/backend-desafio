package br.com.system.cadastro.repository;

import br.com.system.cadastro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
