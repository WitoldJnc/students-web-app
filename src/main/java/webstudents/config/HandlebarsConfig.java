package webstudents.config;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class HandlebarsConfig {

    @Value("${handlebars.prefix}")
    private String prefix;

    @Value("${handlebars.suffix}")
    private String suffix;

    @Autowired
    private HandlebarsViewResolver resolver;

    @Bean
    public HandlebarsViewResolver handlebarsViewResolver() {
        HandlebarsViewResolver hbvr = new HandlebarsViewResolver(new Handlebars());
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix(prefix);
        loader.setSuffix(suffix);
        hbvr.getHandlebars().with(loader);
        return hbvr;
    }

    @PostConstruct
    public void registerHelper() {
        resolver.registerHelpers(ConditionalHelpers.class);
    }
}
