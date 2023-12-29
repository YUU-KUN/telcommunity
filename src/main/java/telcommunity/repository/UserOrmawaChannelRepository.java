package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.UserOrmawaChannel;

@EnableJpaRepositories
public interface UserOrmawaChannelRepository extends JpaRepository<UserOrmawaChannel, String> {
    
}
