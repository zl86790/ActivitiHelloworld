package name.lizhe.service;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class Hello2 implements JavaDelegate {

	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("hello world222");
	}
	
}
