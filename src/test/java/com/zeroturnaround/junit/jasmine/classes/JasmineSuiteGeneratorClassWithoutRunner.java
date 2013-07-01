package com.zeroturnaround.junit.jasmine.classes;

import com.zeroturnaround.junit.jasmine.JasmineSuite;

@JasmineSuite(specs = { "spec1.js", "spec2.js" }, sources = { "source1.js", "source2.js" }, sourcesRootDir = "src/test/javascript/sources/")
public class JasmineSuiteGeneratorClassWithoutRunner {

}
