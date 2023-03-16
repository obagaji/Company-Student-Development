    package com.jtc.jtcaccountstudentloan.company.Config;
    import jakarta.persistence.EntityManagerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
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
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.transaction.PlatformTransactionManager;
    import org.springframework.transaction.annotation.EnableTransactionManagement;
    import javax.sql.DataSource;
    @Configuration
    @EnableWebSecurity
    @EnableTransactionManagement
    @EnableJpaRepositories(transactionManagerRef = "transactionManagerFactory",entityManagerFactoryRef = "entityManagerFactory"
    ,basePackages = "com/jtc/jtcaccountstudentloan/company/Repository/CompanyRepository.java")
    public class CompanyConfig {
    @Autowired
    Environment env;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Bean(name="dataSource")
    @Qualifier
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSources() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return dataSource;
    }
    @Bean(name = "entityManagerFactory")
    @Qualifier
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("dataSource") DataSource dataSource)
    {
        return builder
                .dataSource(dataSource)
                .packages("com/jtc/jtcaccountstudentloan/company/Entity.CompanyLoan")
                .persistenceUnit("CompanyLoan")
                .build();
    }
    @Bean(name = "transactionManagerFactory")
    @Qualifier
    public PlatformTransactionManager transactionManagerFactory(@Qualifier("entityManagerFactory")EntityManagerFactory
                                                                      entityTransactionManagerFactory)
    {
        return new JpaTransactionManager(entityTransactionManagerFactory);
    }
    @Bean
    @Qualifier
    public UserDetailsService CompanyUserDetailsService()
    {
        return new UserDetialsCredential();
    }
    @Bean
    @Qualifier
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(CompanyUserDetailsService());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain companySecurityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity
                .authorizeHttpRequests((authorizeConfig)-> {
                    authorizeConfig.requestMatchers("/company").authenticated();
                    authorizeConfig.requestMatchers("/company/loan/").authenticated();
                    authorizeConfig.requestMatchers("/company/loan/{loanId}").hasRole("USER");
                    authorizeConfig.requestMatchers("/login").permitAll();
                    authorizeConfig.requestMatchers("/register").permitAll();
                    authorizeConfig.requestMatchers("/check").hasRole("ADMIN");
                }).httpBasic(Customizer.withDefaults())
                    .build();
        }
    }
