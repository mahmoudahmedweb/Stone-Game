package stonegame.model;

import java.time.LocalDateTime;

/**
 * The {@code DateAndTime} class provides functionality to obtain the current date and time.
 */
public class DateAndTime {

    private LocalDateTime dataAndTime = LocalDateTime.now();

    /**
     * Retrieves the current date and time.
     *
     * @return The current date and time as a {@link LocalDateTime} object.
     */
    public LocalDateTime getCurrentDateTime() {

        return dataAndTime;

    }
}
