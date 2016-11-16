package com.mbeans.test;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class MyTestMain {

	private static void init() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
		  MBeanServer mbs = null;
		  mbs = ManagementFactory.getPlatformMBeanServer();
		  MyTestAgent agent = new MyTestAgent();
		  ObjectName agentName;
		  agentName = new ObjectName("MyTests:name=MyTestAgent");
		  mbs.registerMBean(agent, agentName);
		 }
		 
		 public static void main(String[] args) throws Exception {
		  init();
		  for (;;) {
		   Thread.sleep(1000);
		  }
		 }
}
