package com.jtc.jtcaccountstudentloan.student.config;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef ="studentEntityManagerFactory",transactionManagerRef = "studentTransactionManager",
basePackages = {"com.jtc.jtcaccountstudentloan.student.repository.StudentRepository.class"})
public class StudentConfig {

    @Autowired
    Environment env;

    @Bean(name="spring-student")
    @Primary
    @ConfigurationProperties(prefix="spring-student")
    public DataSource dataSources() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.student-datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("spring.student-datasource.username"));
        dataSource.setPassword(env.getProperty("spring.student-datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.student-datasource.driver-class-name"));
        return dataSource;
    }
    @Bean(name = "studentEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactorys(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("spring-student") DataSource dataSources)
    {
        return builder
                .dataSource(dataSources)
                .packages("com.jtc.jtcaccountstudentloan.student.entity.Students.repository.StudentRepository")
                .persistenceUnit("Students")
                .build();
    }
    @Bean(name = "studentTransactionManager")
    @Primary
    public PlatformTransactionManager platformTransactionManagers(@Qualifier ("studentEntityManagerFactory")
                                                                 EntityManagerFactory studentEntityManagerFactory)
    {
        JpaTransactionManager jpaTransactionManager= new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(studentEntityManagerFactory);
        return jpaTransactionManager;
    }
    @Bean
    @Primary
    public UserDetailsService studentUserDetailsServices()
    {
        return new UsersDetailDatas();
    }
    @Bean
    public SecurityFilterChain studentSecurityFilterChains(HttpSecurity httpSecurity) throws Exception
    {
        return   httpSecurity.authorizeHttpRequests((authorizeConfig)-> {
            authorizeConfig.requestMatchers("/student").authenticated();
            authorizeConfig.requestMatchers("/student/view").authenticated();
            authorizeConfig.requestMatchers("/student/save/student").hasRole("ADMIN");
            authorizeConfig.requestMatchers("/jtc/api/login").permitAll();
            authorizeConfig.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults()).build();

    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(studentUserDetailsServices());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
