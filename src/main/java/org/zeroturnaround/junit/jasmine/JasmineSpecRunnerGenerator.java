package org.zeroturnaround.junit.jasmine;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

class JasmineSpecRunnerGenerator {

	private enum TemplatePlaceholders {
		RELATIVE_PATH("<!--RelativePath-->"),
		SOURCE_FILES_TO_INCLUDE("<!--SourceFileIncludes-->"),
		SPEC_FILES_TO_INCLUDE("<!--SpecFileIncludes-->");

		private final String placeholder;

		private TemplatePlaceholders(String placeholder) {
			this.placeholder = placeholder;
		}

		public String getPlaceholder() {
			return placeholder;
		}

	}

	private final JasmineSuite suite;
	private final String[] jasmineSpecs;
	private final String outputPath;
	private final String outputFileName;

	public JasmineSpecRunnerGenerator(String[] jasmineSpecs, JasmineSuite suite, String outputPath, String outputFileName) {
		this.jasmineSpecs = jasmineSpecs;
		this.suite = suite;
		this.outputPath = outputPath;
		this.outputFileName = outputFileName;
	}

	public void generate(JasmineSuite suiteAnnotation) {
		String template = loadTemplate(suite.specRunnerTemplate());
		template = replaceRelativePathsForLibs(template);
		template = template.replaceAll(TemplatePlaceholders.SOURCE_FILES_TO_INCLUDE.getPlaceholder(),
				getJavascriptFileIncludes(suiteAnnotation.specRunnerSourcesRelativePath(), suite.sources()));
		template = template.replaceAll(TemplatePlaceholders.SPEC_FILES_TO_INCLUDE.getPlaceholder(),
				getJavascriptFileIncludes(suiteAnnotation.specRunnerSpecsRelativePath(), jasmineSpecs));

		try {
			FileUtils.writeStringToFile(new File(outputPath + "/" + outputFileName), template);
		} catch (IOException e) {
			throw new RuntimeException("unable to write spec runner contents to destination", e);
		}
	}

	private String replaceRelativePathsForLibs(String template) {
		return template.replaceAll(TemplatePlaceholders.RELATIVE_PATH.getPlaceholder(), suite.jsRootDir());
	}

	private String getJavascriptFileIncludes(String path, String[] jsFiles) {
		StringBuilder sourceFileIncludes = new StringBuilder();
		for (String sourceFile : jsFiles) {
      if (suite.headJs())
      {
        sourceFileIncludes.append("\t\thead.js('").append(path).append("/").append(sourceFile).append("');\r\n");
      }
      else
      {
        sourceFileIncludes.append("\t\t<script type='text/javascript' src='").append(path).append("/").append(sourceFile).append("'></script>\r\n");
      }
		}
		return sourceFileIncludes.toString();
	}

	private String loadTemplate(String name) {
		try {
			return IOUtils.toString(
				Thread
					.currentThread()
					.getContextClassLoader()
					.getResourceAsStream(name)
			);
		} catch (NullPointerException e) {
			throw new IllegalStateException("spec runner template file '" + name + "' not found!");
		} catch (IOException e) {
			throw new IllegalStateException("spec runner template file '" + name + "' could not be read!", e);
		}
	}
}
