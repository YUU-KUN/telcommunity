package telcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.Ormawa;

@EnableJpaRepositories
public interface OrmawaRepository extends JpaRepository<Ormawa, String> {
}
