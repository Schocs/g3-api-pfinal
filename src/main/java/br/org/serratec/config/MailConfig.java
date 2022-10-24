package br.org.serratec.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;



@Configuration
@EnableAutoConfiguration
public class MailConfig {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("myHost");
        javaMailSender.setPort(25);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "false");
        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.debug", "false");
        return properties;
    }

    public void sendEmail(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("grupo3serratec2022.2@gmail.com");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Dados do cadastro do usuário:\n" + texto + "\n\n\n\n" + "Serratec Residência-2022");
        javaMailSender.send(message);
    }
    
    public void sendEmailRemover(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("grupo3serratec2022.2@gmail.com");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Remoção de cadastro:\n" + texto + "\n\n\n\n" + "Serratec Residência-2022");
        javaMailSender.send(message);
    }
    
    public void sendEmailAtualizar(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("grupo3serratec2022.2@gmail.com");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Atualização do cadastro do usuário:\n" + texto + "\n\n\n\n" + "Serratec Residência-2022");
        javaMailSender.send(message);
    }
}

