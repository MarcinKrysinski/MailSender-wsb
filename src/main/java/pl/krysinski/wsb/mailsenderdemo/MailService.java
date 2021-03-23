package pl.krysinski.wsb.mailsenderdemo;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class MailService {

    final private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    void send(Mail mail){
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(mail.recipient);
            mimeMessageHelper.setSubject(mail.subject);
            mimeMessageHelper.setText(mail.content);

            mimeMessageHelper.addAttachment(Objects.requireNonNull(mail.attachment.getOriginalFilename()), mail.attachment);

            javaMailSender.send(mimeMessage);

        }catch (Exception e){
            System.out.println("Coś poszło nie tak ;(");
            e.printStackTrace();
        }
    }

}
