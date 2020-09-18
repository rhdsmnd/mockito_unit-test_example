package com.example;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class ReservationServiceImpl
{
    ReservationDao reservationStore;
    EmailService emailNotificationService;

    public ReservationServiceImpl(ReservationDao reservationDao, EmailService emailNotificationService) {
        this.reservationStore = reservationDao;
        this.emailNotificationService = emailNotificationService;
    }

    public Reservation placeReservation(ReservationRequest userReservation) {

        validateReservation(userReservation);

        Reservation orderReservation = this.reservationStore.saveOrder(userReservation);
        emailNotificationService.sendEmail(orderReservation);

        return orderReservation;
    }

    public static void validateReservation(ReservationRequest userRequest) {

        String msg = " is invalid";
        String field = null;

        if (userRequest == null) {
            throw new IllegalArgumentException("User Request object" + msg);
        } else if (isEmpty(userRequest.getUserId())) {
            throw new IllegalArgumentException("User id" + msg);
        } else if (isEmpty(userRequest.getStartDate())) {
            throw new IllegalArgumentException("Start date" + msg);
        } else if (isEmpty(userRequest.getEndDate())) {
            throw new IllegalArgumentException("End date" + msg);
        } else if (isEmpty(userRequest.getRoomType())) {
            throw new IllegalArgumentException("Room type" + msg);
        }
    }

    public static boolean isEmpty(String field) {
        return field == null || field.trim().length() == 0;
    }
}
