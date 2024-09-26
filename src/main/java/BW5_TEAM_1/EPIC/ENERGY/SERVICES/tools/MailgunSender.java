package BW5_TEAM_1.EPIC.ENERGY.SERVICES.tools;


import BW5_TEAM_1.EPIC.ENERGY.SERVICES.entities.User;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MailgunSender {

    private String apiKey;
    private String domainName;
    private String emailFrom;

    public MailgunSender(@Value("${mailgun.key}") String apiKey, @Value("${mailgun.domain}") String domainName, @Value("${mailgun.emailFrom}") String emailFrom) {
        this.apiKey = apiKey;
        this.domainName = domainName;
        this.emailFrom = emailFrom;
    }

    public void sendRegistrationEmail(User recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", this.emailFrom)
                .queryString("to", recipient.getEmail()) // N.B. Ricordarsi di verificare tramite dashboard Mailgun l'indirizzo del ricevente
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Ciao " + recipient.getName() + ", grazie per esserti registrato!")
                .asJson();
        System.out.println(response.getBody()); // <- Stampo il messaggio in risposta per rilevare eventuali errori
    }
}
