package motels.dal;


import motels.model.RoomObject;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

public class MotelsOfDataBase {
    public static LinkedList<RoomObject> roomObjectLinkedList = new LinkedList<>();
    public static LinkedList<RoomObject> customerHistory = new LinkedList<>();

    public void loadFile() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("MotelsFile.csv"));
        String line;
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(", ");

            if (arr[0].equals("STT")) {
                continue;
            }
            try {
                RoomObject roomObject = new RoomObject(++count, arr[1],
                        Integer.parseInt(arr[2].trim()), Integer.parseInt(arr[3]), (arr[4].trim())
                        , Integer.parseInt(arr[5].trim()));
                roomObjectLinkedList.add(roomObject);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }

        }


        bufferedReader.close();
    }

    public void loadFileHistory() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("MotelsHistoryFile.csv"));
        String line;
        int count = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(", ");

            if (arr[0].equals("STT")) {
                continue;
            }
            try {
                RoomObject roomObject = new RoomObject(++count, arr[1],
                        Integer.parseInt(arr[2].trim()), Integer.parseInt(arr[3]), (arr[4].trim())
                        , (arr[5].trim()), Integer.parseInt(arr[6].trim()));
                customerHistory.add(roomObject);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }

        }
        bufferedReader.close();
    }


    public void saveFile() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("MotelsFile.csv"));
        String header = "STT, Name, ID Customer, Room Number, Checkin Time, Money\n";
        bufferedWriter.write(header);
        for (RoomObject object : roomObjectLinkedList) {
            String line = object.toCSVForm();
            bufferedWriter.write(line);
        }
        bufferedWriter.close();
    }

    public void saveFileHistory() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("MotelsHistoryFile.csv"));

        String header = "STT, Name, ID Customer, Room Number, Checkin Time, Checkout Time, Money\n";
        bufferedWriter.write(header);

        for (RoomObject object : customerHistory) {
            String line = object.toCSVHistoryForm();
            bufferedWriter.write(line);
        }
        bufferedWriter.close();
    }

    public void readFileHistory() throws IOException {
        LinkedList<RoomObject> printCustomerList1 = new LinkedList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("MotelsHistoryFile.csv"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(", ");

            if (arr[0].equals("STT")) {
                continue;
            }

            try {
                RoomObject roomObject = new RoomObject(Integer.parseInt(arr[0].trim()), arr[1],
                        Integer.parseInt(arr[2].trim()), Integer.parseInt(arr[3]), (arr[4].trim())
                        , (arr[5].trim()), Integer.parseInt(arr[6].trim()));
                printCustomerList1.add(roomObject);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }

        }
        System.out.printf("                         |%-3s |", "STT");
        System.out.printf("%-15s |", "Name");
        System.out.printf("%-15s |", "Room Number");
        System.out.printf("%-15s |", "Checkin Time");
        System.out.printf("%-15s |", "Checkout Time");
        System.out.printf("%-10s   |\n", "Money");


        for (RoomObject customerObject : printCustomerList1) {
            customerObject.toHistoryString();
        }
        System.out.println();
        System.out.println("                                  There are " + printCustomerList1.size() + " customer in system.");
        System.out.println("                                         --------------");

        bufferedReader.close();
    }

    public void readFile() throws IOException {
        LinkedList<RoomObject> printCustomerList = new LinkedList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("MotelsFile.csv"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(", ");

            if (arr[0].equals("STT")) {
                continue;
            }
            try {
                RoomObject roomObject = new RoomObject(Integer.parseInt(arr[0].trim()), arr[1],
                        Integer.parseInt(arr[2].trim()), Integer.parseInt(arr[3]), (arr[4].trim())
                        , Integer.parseInt(arr[5].trim()));
                printCustomerList.add(roomObject);
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }

        }
        RoomObject roomObject = new RoomObject();
        roomObject.disappear();
        System.out.println("Welcome Binh Hu Motels! ");
        System.out.println("0.Currently Customer  :    ");
        System.out.printf("                         |%-3s |", "STT");
        System.out.printf("%-15s |", "Name");
        System.out.printf("%-10s |", "CMND Number");
        System.out.printf("%-10s |", "Room Number");
        System.out.printf("%-10s      |\n", "CheckinTime");

        for (RoomObject customerObject : printCustomerList) {
            customerObject.toStringBoard();

        }
        System.out.println();
        System.out.println("                                  There are " + printCustomerList.size() + " customer in system.");
        System.out.println("                                         --------------");
        bufferedReader.close();
    }


}


