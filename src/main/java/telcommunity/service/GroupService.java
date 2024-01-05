package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telcommunity.model.Group;
import telcommunity.model.GroupChat;
import telcommunity.repository.GroupChatRepository;
import telcommunity.repository.GroupRepository;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupChatRepository groupChatRepository;

    public List<Group> getAllGroup() {
        try {
            List<Group> groups = new ArrayList<Group>();
            groupRepository.findAll().forEach(group -> groups.add(group));
            return groups;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group getGroupById(String group_id) {
        try {
            return groupRepository.findById(group_id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GroupChat> getGroupChats(String group_id) {
        try {
            return getGroupById(group_id).getGroupChats();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveOrUpdate(Group group) {
        try {
            groupRepository.save(group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGroupChat(GroupChat groupChat) {
        try {
            groupChatRepository.save(groupChat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
