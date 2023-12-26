package telcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.Dosen;

@EnableJpaRepositories
public interface DosenRepository extends JpaRepository<Dosen, String> {
}
