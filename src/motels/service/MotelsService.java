package motels.service;

import motels.dal.MotelsOfDataBase;
import motels.model.RoomObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static motels.dal.MotelsOfDataBase.customerHistory;
import static motels.dal.MotelsOfDataBase.roomObjectLinkedList;


public class MotelsService {
    MotelsOfDataBase motelsOfDataBase = new MotelsOfDataBase();

    public void loadFile() throws IOException {
        motelsOfDataBase.loadFile();

    }

    public void loadFileHistory() throws IOException {

        motelsOfDataBase.loadFileHistory();
    }


    public void checkIn() throws IOException, InterruptedException {
        RoomObject roomObject = new RoomObject();
        roomObject.checkIn();

        if (roomObject.getCheckinTime() == null) {
            return;
        }
        int count = roomObjectLinkedList.size();
        roomObject.setStt(++count);

        roomObjectLinkedList.add(roomObject);
        motelsOfDataBase.saveFile();
        roomObject.disappear();
        System.out.println("                                                WELCOME '" + roomObject.getName() + "' TO BINH HU MOTELS!");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        Thread.sleep(3000);
        roomObject.disappear();


    }

    public void addService() throws InterruptedException {
        if (roomObjectLinkedList.size() == 0) {
            System.err.println("There is not customer now!");
            System.out.println();
            return;
        }

        int room_number = getRoomNumber();

        for (RoomObject object : roomObjectLinkedList) {
            if (room_number == object.getRoomNumber()) {
                object.disappear();
                showService();
                int choose;
                String choose1;
                System.out.println("Input room number:");
                do {


                    choose1 = new Scanner(System.in).nextLine();
                    String regexchoose = "^[1234]{1}$";
                    Pattern patternchoose = Pattern.compile(regexchoose);

                    Matcher matcherchoose = patternchoose.matcher(choose1);
                    while (!matcherchoose.find()) {
                        System.out.println("Please input right!");
                        choose1 = new Scanner(System.in).nextLine();
                        matcherchoose = patternchoose.matcher(choose1);
                    }
                    choose = Integer.parseInt(choose1);
                    switch (choose) {
                        case 1:
                            object.setMoney(object.getMoney() + 5);
                            System.out.println("You added water successfully!");
                            break;
                        case 2:
                            object.setMoney(object.getMoney() + 12);
                            System.out.println("You added food successfully!");
                            break;
                        case 3:
                            object.setMoney(object.getMoney() + 100);
                            System.out.println("You called service successfully!");
                            System.out.println("She will come right now!");
                            break;
                    }
                } while (choose != 4);
                object.disappear();
                return;
            }
        }
        System.out.println("Can't find room!");
        System.out.println();

    }

    public void showService() throws InterruptedException {
        System.out.println("                                                Welcome Binh Hu Motels Service!");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        Thread.sleep(3000);
        RoomObject roomObject = new RoomObject();
        roomObject.disappear();
        System.out.println("1.Water,Coca,7up : 5$");
        System.out.println("2.Mi Tom, Rice :12$");
        System.out.println("3.Massage overnight :100$");
        System.out.println("4.Exit");
    }

    public void editCustomer() throws IOException {
        if (roomObjectLinkedList.size() == 0) {
            System.err.println("There is not customer now!");
            System.out.println();
            return;
        }
        int room_number = getRoomNumber();
        for (RoomObject object : roomObjectLinkedList) {
            if (object.getRoomNumber() == room_number) {
                object.editCustomer();
                motelsOfDataBase.saveFile();
                System.out.println("Edit successfully!");
                return;
            }
        }

        System.out.println("Can't not find room!");
        System.out.println("-------------");


    }

    public void checkOut() throws IOException, ParseException {
        if (roomObjectLinkedList.size() == 0) {
            System.err.println("There is not customer now!");
            System.out.println();
            return;
        }

        final int UNIT_PRICE = 10;
        int room_number = getRoomNumber();
        for (RoomObject object : roomObjectLinkedList) {
            if (room_number == object.getRoomNumber()) {
                String checkoutTime = getNowTime();
                int timeStay = countMoney(object.getCheckinTime(), checkoutTime);
                int money = UNIT_PRICE * timeStay;
                object.setMoney(object.getMoney() + money);
                if (timeStay < 24) {
                    System.out.print(
                            " Customer= '" + object.getName() + '\'' +
                                    ", roomNumber= " + object.getRoomNumber() +
                                    ", TimeStay= " + timeStay + " hours" +
                                    ", money= " + object.getMoney() + "$");
                } else {
                    System.out.print(
                            " Customer= '" + object.getName() + '\'' +
                                    ", RoomNumber= " + object.getRoomNumber() +
                                    ", TimeStay= " + (timeStay / 24) + " days " + timeStay + " hours" +
                                    ", Money= " + object.getMoney() + "$");
                }
                System.out.println();
                System.out.println("                    Thanks you so much!!!");
                System.out.println();
                object.setCheckoutTime(checkoutTime);
                customerHistory.add(object);
                roomObjectLinkedList.remove(object);
                motelsOfDataBase.saveFile();
                motelsOfDataBase.saveFileHistory();


                return;
            }
        }
        motelsOfDataBase.saveFile();
        System.out.println("Can't not find room!");
        System.out.println("-------------");
    }

    public void showHistory() throws IOException {
        if (customerHistory.size() == 0) {
            System.err.println("There is not customer now!");
            System.out.println();
            return;
        }
        motelsOfDataBase.readFileHistory();
    }

    public void showList() throws IOException {
        if (roomObjectLinkedList.size() == 0) {
            System.err.println("There is not customer now!");
            System.out.println();
            return;
        }
        motelsOfDataBase.readFile();
    }

    public String getNowTime() {
        String daytime = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Input day:");
        String day = sc.nextLine();
        String regexDay = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
        Pattern patternDay = Pattern.compile(regexDay);
        Matcher matcherDay = patternDay.matcher(day);
        while (!matcherDay.find()) {
            System.out.println("Please input right Form! ");
            System.out.println("dd/mm/yy or dd-mm-yy");
            day = sc.nextLine();
            matcherDay = patternDay.matcher(day);
        }
        daytime += day;

        int hour;
        do {
            System.out.println("Input hour:");
            while (!sc.hasNextInt()) {
                System.out.println("Please input right Form!");
                System.out.println("From 1 to 23");
                sc.nextInt();
            }
            hour = sc.nextInt();
        } while (hour < 0 || hour > 24);
        daytime += " " + hour;
        sc.nextLine();

        return daytime;
    }

    public int countMoney(String dayin, String dayout) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH");
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy HH");
        Date d1 = null;
        Date d2 = null;

        d1 = format.parse(dayin);
        d2 = format.parse(dayout);

        if (d1 == null || d2 == null) {
            d1 = formater.parse(dayin);
            d2 = formater.parse(dayout);
        }
        long diff = d2.getTime() - d1.getTime();
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (60 * 60 * 1000 * 24);

        return Integer.parseInt(String.valueOf(diffHours));


    }

    public int getRoomNumber() {
        int roomNumber;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Input room number:");
            while (!sc.hasNextInt()) {
                System.out.println("Please input right Form!");
                System.out.println("Form 1 to 10");
                sc.next();
            }
            roomNumber = sc.nextInt();
            sc.nextLine();

        } while (roomNumber < 1 || roomNumber > 10);
        return roomNumber;
    }
}




