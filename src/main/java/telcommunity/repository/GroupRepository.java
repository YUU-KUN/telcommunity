package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.Group;

@EnableJpaRepositories
public interface GroupRepository extends JpaRepository<Group, String> {
}
