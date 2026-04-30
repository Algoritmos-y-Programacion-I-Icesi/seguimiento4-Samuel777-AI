package model;
import java.util.ArrayList;
import java.time.LocalDate;

public class Controller {

    public static final int FLOORS = 12;
    public static final int DEVICES_PER_FLOOR = 4;

    private Device[][] deviceMatrix;

    public Controller() {
        deviceMatrix = new Device[FLOORS][DEVICES_PER_FLOOR];
    }

    /**
     * Busca un dispositivo en la matriz por su serial.
     * Retorna el dispositivo si lo encuentra, o null si no existe.
     */
    public Device findDevice(String serial) {
        for (int i = 0; i < FLOORS; i++) {
            for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
                if (deviceMatrix[i][j] != null
                        && deviceMatrix[i][j].getSerial().equalsIgnoreCase(serial)) {
                    return deviceMatrix[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Agrega un dispositivo en la primera posicion disponible del piso indicado.
     * Verifica que el piso sea valido y que el serial no este repetido.
     */
    public void addDevice(int floor, String serial, double consumption) {
        if (floor < 1 || floor > FLOORS) {
            System.out.println("Piso invalido. Debe estar entre 1 y " + FLOORS);
            return;
        }

        if (findDevice(serial) != null) {
            System.out.println("Ya existe un dispositivo con el serial " + serial);
            return;
        }

        int i = floor - 1;

        for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
            if (deviceMatrix[i][j] == null) {
                deviceMatrix[i][j] = new Device(serial, consumption);
                System.out.println("Dispositivo " + serial + " agregado en el piso " + floor + ", posicion " + (j + 1));
                return;
            }
        }

        System.out.println("El piso " + floor + " ya tiene el maximo de dispositivos permitidos.");
    }

    /**
     * Registra un evento en el dispositivo con el serial indicado.
     * Verifica que el dispositivo exista y que las horas sean mayores a 0.
     */
    public void addEvent(String serial, LocalDate date, double hours) {
        Device d = findDevice(serial);

        if (d == null) {
            System.out.println("No se encontro ningun dispositivo con el serial " + serial);
            return;
        }

        if (hours <= 0) {
            System.out.println("Las horas deben ser un valor mayor a 0.");
            return;
        }

        d.addEvent(date, hours);
        System.out.println("Evento registrado correctamente en el dispositivo " + serial);
    }

    /**
     * Actualiza el consumo nominal del dispositivo con el serial indicado.
     * Verifica que el dispositivo exista y que el nuevo valor sea mayor a 0.
     */
    public void updateConsumption(String serial, double newConsumption) {
        Device d = findDevice(serial);

        if (d == null) {
            System.out.println("No se encontro ningun dispositivo con el serial " + serial);
            return;
        }

        if (newConsumption <= 0) {
            System.out.println("El valor de consumo debe ser mayor a 0.");
            return;
        }

        d.setConsumption(newConsumption);
        System.out.println("Consumo actualizado a " + newConsumption + " KWh para el dispositivo " + serial);
    }

    /**
     * Calcula y muestra el consumo total del dispositivo con el serial indicado.
     * El consumo total es la suma de horas de todos los eventos multiplicada por el consumo nominal.
     */
    public void calculateDeviceConsumption(String serial) {
        Device d = findDevice(serial);

        if (d == null) {
            System.out.println("No se encontro ningun dispositivo con el serial " + serial);
            return;
        }

        if (d.getEvents().isEmpty()) {
            System.out.println("El dispositivo " + serial + " no tiene eventos registrados.");
            return;
        }

        double total = d.calculateTotalConsumption();

        System.out.println("\nDispositivo: " + d);
        System.out.printf("Consumo total calculado: %.4f KWh%n", total);
        System.out.println("Detalle de eventos:");
        for (Event e : d.getEvents()) {
            System.out.println(e);
        }
    }

    /**
     * Muestra la lista de dispositivos cuyo consumo nominal supera 0.3 KWh.
     */
    public void listDevicesRequiringReplacement() {
        ArrayList<Device> lista = new ArrayList<>();

        for (int i = 0; i < FLOORS; i++) {
            for (int j = 0; j < DEVICES_PER_FLOOR; j++) {
                if (deviceMatrix[i][j] != null
                        && deviceMatrix[i][j].getConsumption() > 0.3) {
                    lista.add(deviceMatrix[i][j]);
                }
            }
        }

        if (lista.isEmpty()) {
            System.out.println("Ningun dispositivo supera el umbral de 0.3 KWh.");
            return;
        }

        System.out.println("\nDispositivos que requieren cambio (consumo mayor a 0.3 KWh):");
        for (Device d : lista) {
            System.out.println(d);
        }
        System.out.println("Total encontrados: " + lista.size());
    }

}