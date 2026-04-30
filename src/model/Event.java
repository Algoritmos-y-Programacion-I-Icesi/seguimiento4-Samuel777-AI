package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {

    private LocalDate timestamp;
    private double duration;

    public Event(LocalDate timestamp, double duration) {
        this.timestamp = timestamp;
        this.duration = duration;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public double getDuration() {
        return duration;
    }

    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Fecha: " + timestamp.format(fmt) + " | Duracion: " + duration + " horas";
    }
}

