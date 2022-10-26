package br.org.serratec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

	@Autowired
	private JavaMailSender javaMailSender;

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
