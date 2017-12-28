import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class TestGroup {

	@Test
	public void test() throws InterruptedException {

		ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

		IdentityService identityService = processEngine.getIdentityService();
		
		identityService.deleteUser("lz");
		User lz = identityService.newUser("lz");
		lz.setFirstName("Zhe");
		lz.setLastName("Li");
		lz.setEmail("mail@mail.com");
		identityService.saveUser(lz);
		
		identityService.deleteGroup("deptManager");
		Group deptManager = identityService.newGroup("deptManager");
		deptManager.setName("Dept Manager");
		deptManager.setType("assignment");
		identityService.saveGroup(deptManager);
		
		List<Group> result = identityService.createGroupQuery().groupId("deptManager").list();
		for(Group g:result){
			System.out.println(g.getName());
		}
		
		identityService.createMembership("lz", "deptManager");
		
		List<User> userInGroup = identityService.createUserQuery().memberOfGroup("deptManager").list();
		for(User u:userInGroup){
			System.out.println(u.getId());
		}
		
		Group group = identityService.createGroupQuery().groupMember("lz").singleResult();
		System.out.println(group.getId());

	}

}
