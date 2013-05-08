package com.poolik.junit.jasmine;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.poolik.junit.jasmine.JasmineTestRunner;
import com.poolik.junit.jasmine.classes.JasmineTestRunnerBeforeAndAfterClass;

@RunWith(MockitoJUnitRunner.class)
public class JasmineTestRunnerBeforeAndAfterTest {

	@Mock
	private RunNotifier notifierMock;

	@Test
	public void useJasmineRunnerOnJasmineTestRunnerBeforeAndAfterClass() {
		new JasmineTestRunner(JasmineTestRunnerBeforeAndAfterClass.class).run(notifierMock);
	}
}
