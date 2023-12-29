package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.UserClassChannel;

@EnableJpaRepositories
public interface UserClassChannelRepository extends JpaRepository<UserClassChannel, String> {
    
}
