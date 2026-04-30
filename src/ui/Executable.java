package ui;

import model.Controller;

import java.time.LocalDate;
import java.util.Scanner;

public class Executable {

  private Controller control;
  private Scanner reader;

  public Executable() {
    this.control = new Controller();
    this.reader = new Scanner(System.in);
  }

  public static void main(String[] args) {
    Executable exe = new Executable();
    exe.showMenu();
  }

  public void showMenu() {

    System.out.print("\033[H\033[2J");

    int option = 0;

    do {
      System.out.println("\tMENU PRINCIPAL");
      System.out.println("1. Agregar un dispositivo");
      System.out.println("2. Agregar evento a un dispositivo");
      System.out.println("3. Actualizar la unidad de consumo de un dispositivo");
      System.out.println("4. Mostrar el consumo total de un dispositivo");
      System.out.println("5. Identificar dispositivos que requieren cambio");
      System.out.println("0. Salir del sistema");
      option = reader.nextInt();

      switch (option) {
        case 1:
          addDevice();
          break;
        case 2:
          registerEventForDevice();
          break;
        case 3:
          updateDeviceConsumptionUnit();
          break;
        case 4:
          showDeviceConsumption();
          break;
        case 5:
          control.listDevicesRequiringReplacement();
          break;
        case 0:
          System.out.println("Gracias por usar nuestros servicios!");
          break;
        default:
          System.out.println("Digite una opcion valida");
          break;
      }

    } while (option != 0);

  }

  public void addDevice() {
    reader.nextLine();
    System.out.println("Agregar Dispositivo:");
    System.out.println("Digite el serial del dispositivo");
    String serial = reader.nextLine();
    System.out.println("Digite el valor de consumo del dispositivo");
    double consumption = reader.nextDouble();
    System.out.println("Digite el piso donde se encuentra el dispositivo");
    int floor = reader.nextInt();
    reader.nextLine();
    control.addDevice(floor, serial, consumption);
  }

  public void registerEventForDevice() {
    reader.nextLine();
    System.out.println("Agregar evento:");
    System.out.println("Digite el serial del dispositivo");
    String serial = reader.nextLine();
    System.out.println("Digite la fecha del evento (dd/MM/yyyy)");
    LocalDate date = LocalDate.parse(reader.nextLine(), java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    System.out.println("Digite la cantidad de horas que el dispositivo estuvo encendido");
    double hours = reader.nextDouble();
    control.addEvent(serial, date, hours);
  }

  public void updateDeviceConsumptionUnit() {
    reader.nextLine();
    System.out.println("Actualizar unidad de consumo:");
    System.out.println("Digite el serial del dispositivo");
    String serial = reader.nextLine();
    System.out.println("Digite el nuevo valor de consumo del dispositivo");
    double newConsumption = reader.nextDouble();
    control.updateConsumption(serial, newConsumption);
  }

  public void showDeviceConsumption() {
    reader.nextLine();
    System.out.println("Mostrar consumo total:");
    System.out.println("Digite el serial del dispositivo");
    String serial = reader.nextLine();
    control.calculateDeviceConsumption(serial);
  }

}