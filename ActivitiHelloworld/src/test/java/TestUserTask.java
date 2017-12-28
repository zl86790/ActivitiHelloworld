import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class TestUserTask {

	@Test
	public void test() throws InterruptedException {

		ProcessEngine processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
		
		Deployment deployment = processEngine.getRepositoryService().createDeployment()
				.addClasspathResource("diagrams/HelloworldProcess4.bpmn").deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());

		TaskService taskService = processEngine.getTaskService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		taskService.setVariables("key", new HashMap());
		
		Map map = new HashMap();
		map.put("skip", 1);
		
		String pid = runtimeService.startProcessInstanceByKey("helloprocess4",map).getId();
		System.out.println(pid);
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("lz").list();
		
		for(Task t:tasks){
			System.out.println(t.getId()+" "+t.getAssignee());
			System.out.println(taskService.getVariables(t.getId()));
			taskService.claim(t.getId(), "lz");
			taskService.complete(t.getId());     
		}
		
		                            
	}

}
