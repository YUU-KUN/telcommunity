package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.UserChat;

@EnableJpaRepositories
public interface UserChatRepository extends JpaRepository<UserChat, String> {
    
}
