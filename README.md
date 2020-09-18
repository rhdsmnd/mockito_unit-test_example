# Simple Junit Test Example with Mockito

This is a sample project that shows the basics of using mocking to unit test Java code.  It uses Junit for testing, Mockito for mocking, and Maven for building & running.


The service class in test is `ReservationServiceImpl`, which makes method calls to a DAO layer and a fake email service.  Notice how in `ReservationServiceImplTest` the two dependendent classes are mocked: we don't care about their implementation, only that `ReservationServiceImpl` performs the correct interactions with them (e.g. it validates the user reservation request, sends the request to the database/DAO layer, and invokes the `EmailService` to update the `User`.

### Running the Tests

Make sure that [Maven](https://maven.apache.org/) is installed.

`mvn test`
