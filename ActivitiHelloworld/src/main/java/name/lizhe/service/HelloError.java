package name.lizhe.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class HelloError implements JavaDelegate {

	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("hello error");
	}
	
}
