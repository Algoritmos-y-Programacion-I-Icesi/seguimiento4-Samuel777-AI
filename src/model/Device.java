package model;
import java.util.ArrayList;
import java.time.LocalDate;

public class Device {

    private String serial;
    private double consumption;
    private ArrayList<Event> events;

    public Device(String serial, double consumption) {
        this.serial = serial;
        this.consumption = consumption;
        this.events = new ArrayList<>();
    }

    public String getSerial() {
        return serial;
    }

    public double getConsumption() {
        return consumption;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public void addEvent(LocalDate date, double duration) {
        events.add(new Event(date, duration));
    }

    public double calculateTotalConsumption() {
        double totalHours = 0;
        for (Event e : events) {
            totalHours += e.getDuration();
        }
        return totalHours * consumption;
    }

    public String toString() {
        return "Serial: " + serial + " | Consumo: " + consumption + " KWh";
    }
}