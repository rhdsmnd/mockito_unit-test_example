package com.fake.twilio;

public interface TwilioEmailClient {

    public void send(String emailAddress, String message);
}
