package tn.esprit.vbank.configurations;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@org.springframework.context.annotation.Configuration
public class NotificationConfiguration {

	public Configuration initNotificationConfiguration() throws IOException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
		configuration.setDirectoryForTemplateLoading(new File("/notification/templates"));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
		configuration.setFallbackOnNullLoopVariable(false);
		return configuration;
	}

}
