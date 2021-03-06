import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class TestAct {

	@Test
	public void test() throws InterruptedException {

		ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
 
		System.out.println("------processEngine:" + processEngine);

		Deployment deployment = processEngine.getRepositoryService().createDeployment()
				.addClasspathResource("diagrams/HelloworldProcess.bpmn").deploy();

		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		Map map = new HashMap();
		map.put("type", 2);
		String pid = runtimeService.startProcessInstanceByKey("helloprocess",map).getId();
		System.out.print(pid);

	}

}
