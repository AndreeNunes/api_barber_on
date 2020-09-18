package br.com.api.barberon.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp {

	public void recuperar(String nomeUsuario, String email) {
		System.out.println("TOMAAAA");

		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("amdre.nicola.nunes@gmail.com", "andre242");
			}
		});

		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("andre.nicola.nune@gmail.com"));
			// Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse("amdre.nicola.nunes@gmail.com");

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(nomeUsuario + ", aqui esta seu codigo");// Assunto
			message.setText("Olá " + nomeUsuario + "\nRecebemos uma solicitação para redefinir a senha de sua conta no Barber on. \n CODIGO \n insira este código para concluir a redefinição");
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}