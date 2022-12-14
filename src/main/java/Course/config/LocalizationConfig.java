package Course.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

@Configuration
public class LocalizationConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    private static final Locale LOCALE_AZ = new Locale("az");
    private static final Locale LOCALE_EN = new Locale("en");
    private static final Locale LOCALE_TR = new Locale("tr");


    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");
        if (Objects.isNull(lang) || lang.isEmpty()) {
            return Locale.getDefault();
        } else {
            return Locale.lookup(Locale.LanguageRange.parse(lang), Arrays.asList(LOCALE_AZ, LOCALE_EN,LOCALE_TR));
        }
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setDefaultLocale(LOCALE_AZ);
        rs.setUseCodeAsDefaultMessage(true); // TODO
        return rs;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource){
        LocalValidatorFactoryBean lfb = new LocalValidatorFactoryBean();
        lfb.setValidationMessageSource(messageSource);
        return lfb;
    }

}
