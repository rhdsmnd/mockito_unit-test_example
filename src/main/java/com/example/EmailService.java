package com.example;

import com.fake.twilio.TwilioEmailClient;

public class EmailService {

    EmailService(UserDatabase userDb, TwilioEmailClient emailClient) {
        this.userDatabase = userDb;
        this.thirdPartyEmailClient = emailClient;
    }

    UserDatabase userDatabase;

    TwilioEmailClient thirdPartyEmailClient;

    public void sendEmail(Reservation newReservation) {
        User userToNotify = userDatabase.getUserById(newReservation.getUserId());


        String message = "Hello "
                    + userToNotify.getFirstName()
                    + ", your reservation is confirmed."
                    + "Please keep this id for your records: " + newReservation.getId();

        thirdPartyEmailClient.send(userToNotify.getEmailAddress(), message);
    }

}
