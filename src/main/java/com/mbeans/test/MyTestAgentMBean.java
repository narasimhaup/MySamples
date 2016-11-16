package com.mbeans.test;

public interface MyTestAgentMBean {
	void createNewThread(String pThreadName);
	void newCollectableObject(int size);
	 void newLeakedObject(int size);
	 void clearLeaked();
	 void cpuIntensiveOperation(int iterations);

}
