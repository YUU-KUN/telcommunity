package telcommunity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import telcommunity.model.GroupChat;

@EnableJpaRepositories
public interface GroupChatRepository extends JpaRepository<GroupChat, String> {
}
