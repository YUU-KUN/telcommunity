package telcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.KetuaOrmawa;

@EnableJpaRepositories
public interface KetuaOrmawaRepository extends JpaRepository<KetuaOrmawa, String> {
}
