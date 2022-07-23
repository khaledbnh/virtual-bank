package tn.esprit.vbank.configurations;

import java.io.IOException;

import org.springframework.context.annotation.Bean;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@org.springframework.context.annotation.Configuration
public class NotificationConfiguration {

	@Bean
	Configuration configuration() throws IOException {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
		configuration.setClassForTemplateLoading(NotificationConfiguration.class, "/");
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
		configuration.setFallbackOnNullLoopVariable(false);
		return configuration;
	}

}
