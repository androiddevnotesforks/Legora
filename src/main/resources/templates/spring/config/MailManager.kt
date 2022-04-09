package #{PackageName}.config


import #{PackageName}.models.entities.account.Account
import org.springframework.stereotype.Component
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Component
open class MailManager {

    fun sendMessage(user: Account?, messageText: String?, subject: String?) {
        val from = "email address here"
        val host = "smtp.gmail.com"
        val properties = System.getProperties()
        properties["mail.smtp.host"] = host
        properties["mail.smtp.port"] = "465"
        properties["mail.smtp.ssl.enable"] = "true"
        properties["mail.smtp.auth"] = "true"
        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(from, "")
            }
        })
        session.debug = true
        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(from))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(user?.email))
            message.subject = subject
            message.setText(messageText)
            println("sending...")
            Transport.send(message)
            println("Sent message successfully....")
        } catch (mex: MessagingException) {
            mex.printStackTrace()
        }
    }

    fun sendMessage(mail: String, messageText: String?, subject: String?) {
        val from = "email address here"
        val host = "smtp.gmail.com"
        val properties = System.getProperties()
        properties["mail.smtp.host"] = host
        properties["mail.smtp.port"] = "465"
        properties["mail.smtp.ssl.enable"] = "true"
        properties["mail.smtp.auth"] = "true"
        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(from, "")
            }
        })
        session.debug = true
        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(from))
            message.addRecipient(Message.RecipientType.TO, InternetAddress(mail))
            message.subject = subject
            message.setText(messageText)
            println("sending...")
            Transport.send(message)
            println("Sent message successfully....")
        } catch (mex: MessagingException) {
            mex.printStackTrace()
        }
    }

}
