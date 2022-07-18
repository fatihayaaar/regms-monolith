package com.fayardev.regms;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

@SpringBootApplication
public class RegMSApplication extends SpringBootServletInitializer {

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
    private static final Class<RegMSApplication> applicationClass = RegMSApplication.class;
    private static Session session = SESSION_FACTORY.openSession();

    public static void main(String[] args) {
        SpringApplication.run(RegMSApplication.class, args);

        getSession();
        closeSession();
    }

    public static Session getSession() {
        if (!session.isOpen()) {
            session = SESSION_FACTORY.openSession();
        }
        return session;
    }

    public static void closeSession() {
        try {
            getSession().close();
        } catch (Exception ignored) {
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("example.com");
        mailSender.setPort(587);

        mailSender.setUsername("password@example.com");
        mailSender.setPassword("xxx");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
