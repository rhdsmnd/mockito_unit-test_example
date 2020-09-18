# Simple Junit Test Example with Mockito

This is a sample project that shows the basics of using mocking to unit test Java code.  It uses [Junit](https://junit.org/junit4/) for testing, [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/2.24.5/org/mockito/Mockito.html) for mocking, and [Maven](https://maven.apache.org/) for building & running the code.  Note, this project doesn't use the latest and greatest library verions: it's merely for learning the basics of mocking and unit testing.

## Overview

The service class to test is `ReservationServiceImpl`, which makes method calls to a fake DAO layer and email service.  Notice how in `ReservationServiceImplTest` the two dependendent classes are mocked: we don't care about their implementation, only that `ReservationServiceImpl` performs the correct interactions with them (e.g. it validates the user reservation request, sends the request to the database/DAO layer, and invokes the `EmailService` to update the `User`.


### Running the Tests

Make sure that Java and [Maven](https://maven.apache.org/) are installed.  Navigate to the base directory of this project in your favorite command line application (Command Prompt for Windows, or Terminal for Mac) and run:

`mvn test`
