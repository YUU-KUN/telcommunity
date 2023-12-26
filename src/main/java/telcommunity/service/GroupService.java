package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telcommunity.model.Group;
import telcommunity.repository.GroupRepository;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

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

    public void saveOrUpdate(Group group) {
        try {
            groupRepository.save(group);
        } catch (Exception e) {
            e.printStackTrace();   
        }
    }
}
