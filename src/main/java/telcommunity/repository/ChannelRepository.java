package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.Channel;

@EnableJpaRepositories
public interface ChannelRepository extends JpaRepository<Channel, String>{
    
}
