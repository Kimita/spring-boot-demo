package page.clapandwhistle.uam.config.ProfileTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {
    @Bean
    EnvStringBean sampleBean(){
        return new EnvStringBean("デフォルト");
    }
}
