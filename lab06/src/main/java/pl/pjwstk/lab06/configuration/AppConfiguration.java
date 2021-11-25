package pl.pjwstk.lab06.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pjwstk.lab06.service.CreditService;
import pl.pjwstk.lab06.service.RepaymentScheduleService;

@Configuration
public class AppConfiguration {
    @Bean
    public CreditService creditServiceConfiguration(){
        return new CreditService();
    }
    @Bean
    public RepaymentScheduleService repaymentScheduleService(){return new RepaymentScheduleService();}
}
