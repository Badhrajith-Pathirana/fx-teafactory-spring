package lk.beempz.tf.main;

import lk.beempz.tf.business.custom.impl.BankBOImpl;
import lk.beempz.tf.dao.custom.impl.BankDAOImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {BankBOImpl.class, BankDAOImpl.class})
public class AppConfig {
}