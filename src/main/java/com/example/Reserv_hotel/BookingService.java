package com.example.Reserv_hotel;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public List<Booking> createBooking(BookingRequest bookingRequest) {
        if (bookingRequest.getRoomNumber() < 1 || bookingRequest.getRoomNumber() > 9) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Les numéros de chambres sont compris entre 1 et 9 uniquement"
            );
        }

        boolean isRoomBooked = bookings.stream()
                .anyMatch(booking ->
                        booking.getRoomNumber().equals(bookingRequest.getRoomNumber()) &&
                                booking.getBookingDate().equals(bookingRequest.getBookingDate())
                );

        if (isRoomBooked) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "La chambre " + bookingRequest.getRoomNumber() +
                            " n'est plus disponible pour la date " + bookingRequest.getBookingDate()
            );
        }

        Booking newBooking = new Booking(
                bookingRequest.getClientName(),
                bookingRequest.getPhoneNumber(),
                bookingRequest.getEmail(),
                bookingRequest.getRoomNumber(),
                bookingRequest.getRoomDescription(),
                bookingRequest.getBookingDate()
        );

        bookings.add(newBooking);

        return new ArrayList<>(bookings);
    }
}