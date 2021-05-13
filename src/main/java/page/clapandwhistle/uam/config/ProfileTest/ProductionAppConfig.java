package page.clapandwhistle.uam.config.ProfileTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProductionAppConfig {
    @Bean
    EnvStringBean sampleBean(){
        return new EnvStringBean("プロダクション");
    }
}
