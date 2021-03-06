package com.zeroturnaround.env;

import org.junit.Before;
import org.junit.runner.RunWith;

import org.zeroturnaround.junit.jasmine.JasmineSuite;
import org.zeroturnaround.junit.jasmine.JasmineTestRunner;
import org.zeroturnaround.rhino.RhinoContext;

@RunWith(JasmineTestRunner.class)
@JasmineSuite(sources = "jquery-1.6.1.js", sourcesRootDir = "src/test/javascript")
public class EnvUtilsTest {

	@Before
	public void loadJasmineJQueryMatchers(RhinoContext context) {
		context.loadFromClasspath("js/lib/jasmine-1.3.1/jasmine-jquery-rhino.js");
	}

}
