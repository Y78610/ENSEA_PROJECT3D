package src;

import java.time.LocalDateTime;

public class Flight {
    private String airlineCode;
    private String airlineName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureDateTime;
    private int number;

    Flight()
    {

    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    @Override
    public String toString()
    {
        return  "";
    }
}
