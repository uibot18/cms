package com.cms.common.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.application.util.CMSMailer;
import com.application.util.EmailPayload;
import com.cms.common.db.connection.DBConnection;
import com.cms.common.db.util.DBUtil;

public class Test{
	public static void main(String[] args)  {
		//	DevUtil.generateDOAndDAO(DBConnection.getConnection(), "admin_login_master");

		EmailPayload payload = new EmailPayload();
		payload.setTo("vijay.cool35@gmail.com");
		payload.setSubject("Reg: User Registration");
		payload.setMessage("Dear User,\n\n\t\t Your Account successfully created..!\n\n"
				+ "User Name: asmin\n password: admin\n\n\n"
				+ "regards\nTeam\nUI-BOT");
		
		CMSMailer.send(payload);
	}

	private static void sendMail() {
		final String username = "uibot18@gmail.com";
		final String password = "ui@12345";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("vijay.cool35@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}}


	private static void getData(Integer i) {
		i++;
		System.out.println("i:ssss"+i);
	}





	public static boolean gwnerateTask(Connection preCon, int taskProcessMasterId ) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;

		try {
			con=preCon==null?DBConnection.getConnection():preCon;
			stmt=con.prepareStatement("CALL task_config_det_by_id("+taskProcessMasterId+",0,0,0,0,0,1,@a);");
			int a =stmt.executeUpdate();

		} catch (Exception e) { e.printStackTrace(); } 
		finally { DBUtil.close( stmt, preCon==null?con:null, rs  ); }
		return false;
	}




}
class AA implements Cloneable{
	public int a;

}