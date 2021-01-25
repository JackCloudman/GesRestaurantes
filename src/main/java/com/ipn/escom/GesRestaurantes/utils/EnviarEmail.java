package com.ipn.escom.GesRestaurantes.utils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
/**
 *
 * @author cesar
 */
public class EnviarEmail {
    public static void enviarCorreo(String destinatario, String asunto, String mensaje){
        try {
            Properties propiedades = new Properties();
            propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedades.setProperty("mail.smtp.starttls.enable", "true"); //o quitar enable
            propiedades.setProperty("mail.smtp.port", "587");
            propiedades.setProperty("mail.smtp.user", "gesrestaurantes@gmail.com");
            propiedades.setProperty("mail.smtp.auth", "true");

            Session  session = Session.getDefaultInstance(propiedades);
            MimeMessage elMensaje = new MimeMessage(session);
            elMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            elMensaje.setSubject(asunto);
            elMensaje.setText(mensaje);

            Transport t = session.getTransport("smtp");
            t.connect(propiedades.getProperty("mail.smtp.user"), "foobar!!");
            //myaccount.google.com/lesssecureapp habilitar aplicaciones menos seguras en google
            t.sendMessage(elMensaje, elMensaje.getAllRecipients());
            t.close();
        } catch (AddressException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

