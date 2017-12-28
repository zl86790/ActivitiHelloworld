import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class TestAct2 {
	
	

	@Test
	public void test() throws InterruptedException {

		ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

		System.out.println("------processEngine:" + processEngine);

		Deployment deployment = processEngine.getRepositoryService().createDeployment()
				.addClasspathResource("diagrams/HelloworldProcess3.bpmn").deploy();

		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		
		IdentityService identityService = processEngine.getIdentityService();
		
		identityService.deleteUser("lz");
		
		User lz = identityService.newUser("lz");
		lz.setFirstName("Zhe");
		lz.setLastName("Li");
		lz.setEmail("mail@mail.com");
		identityService.saveUser(lz);
		
		User result = identityService.createUserQuery().userId("lz").singleResult();
		System.out.println(result.getEmail());
		
//		RuntimeService runtimeService = processEngine.getRuntimeService();
//		String pid = runtimeService.startProcessInstanceByKey("helloprocess3").getId();
//		System.out.print(pid);
		
//		while(true){
//			Thread.sleep(20000);
//		}

	}

}
