package es.plexus.reservas.reservaSalas.services.impl;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.plexus.reservas.reservaSalas.services.IEmailService;

@Service
@Transactional
public class EmailServiceImpl implements IEmailService{
	@Autowired
    private JavaMailSender sender;

    public boolean sendMail(String para, String texto, String asunto) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(para);
            helper.setText(texto);
            helper.setSubject(asunto);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        sender.send(message);
        return true;
    }
}

