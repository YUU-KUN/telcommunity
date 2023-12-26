package telcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
