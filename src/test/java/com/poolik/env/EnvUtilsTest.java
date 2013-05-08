package com.poolik.env;

import org.junit.Before;
import org.junit.runner.RunWith;

import com.poolik.junit.jasmine.JasmineSuite;
import com.poolik.junit.jasmine.JasmineTestRunner;
import com.poolik.rhino.RhinoContext;

@RunWith(JasmineTestRunner.class)
@JasmineSuite(sources = "jquery-1.6.1.js", sourcesRootDir = "src/test/javascript")
public class EnvUtilsTest {

	@Before
	public void loadJasmineJQueryMatchers(RhinoContext context) {
		context.loadFromClasspath("js/lib/jasmine-1.0.2/jasmine-jquery-rhino.js");
	}

}
