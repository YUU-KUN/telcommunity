package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.OrmawaChannel;

@EnableJpaRepositories
public interface OrmawaChannelRepository extends JpaRepository<OrmawaChannel, String>{
    
}
