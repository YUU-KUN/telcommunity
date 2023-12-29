package telcommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.ClassChannelChat;

@EnableJpaRepositories
public interface ClassChannelChatRepository extends JpaRepository<ClassChannelChat, String>{
    
}
