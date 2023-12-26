package telcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.Mahasiswa;

@EnableJpaRepositories
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, String> {
}
