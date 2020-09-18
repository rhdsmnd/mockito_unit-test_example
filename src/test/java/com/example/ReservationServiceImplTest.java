package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationDao mockDao;
    @Mock
    private EmailService mockEmailService;

    private ReservationServiceImpl reservationService;

    @Before
    public void setup() {
        this.reservationService = new ReservationServiceImpl(mockDao, mockEmailService);
    }

    @Test
    public void testSavesToDatabase() {
        ReservationRequest sampleRequest = new ReservationRequest("1234", "2020-01-01", "2020-01-01", "double");

        Reservation dbResponse = new Reservation("987", null, null, null, null, null);

        // Arrange
        when(mockDao.saveOrder(sampleRequest)).thenReturn(dbResponse);

        // Act
        Reservation reservationResponse = this.reservationService.placeReservation(sampleRequest);

        // Assert
        assertEquals("987", reservationResponse.getId());
    }

    @Test
    public void testSendsEmail() {
        // Arrange
        Reservation dbResponse = new Reservation("987", null, null, null, null, null);
        ReservationRequest sampleRequest = new ReservationRequest("1234", "2020-01-01", "2020-01-01", "double");

        when(mockDao.saveOrder(sampleRequest)).thenReturn(dbResponse);

        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);
        doNothing().when(mockEmailService).sendEmail(reservationCaptor.capture());


        // Act
        Reservation reservationResponse = this.reservationService.placeReservation(sampleRequest);

        // Assert
        assertEquals(dbResponse, reservationCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyUserIdRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest("", "2020-01-01", "2020-01-01", "double");

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUserIdRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest(null, "2020-01-01", "2020-01-01", "double");

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyStartDateRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest("1234", "", "2020-01-01", "double");

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullStartDateRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest("1234", null, "2020-01-01", "double");

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyEndDateRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest("4321", "2020-01-01", "", "double");

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEndDateRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest("3345", "2020-01-01", null, "double");

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyRoomTypeRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest("8765", "2020-01-01", "2020-01-01", "");

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRoomTypeRejected() {
        // Arrange
        ReservationRequest sampleRequest = new ReservationRequest("4567", "2020-01-01", "2020-01-01", null);

        // Act
        reservationService.placeReservation(sampleRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullReservationRequestRejected() {
        // Act
        reservationService.placeReservation(null);
    }

}
