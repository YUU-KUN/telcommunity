package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.ClassChannel;

@EnableJpaRepositories
public interface ClassChannelRepository extends JpaRepository<ClassChannel, String>{
    
}
