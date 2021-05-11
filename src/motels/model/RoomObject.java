package motels.model;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static motels.dal.MotelsOfDataBase.roomObjectLinkedList;

public class RoomObject {


    private int stt;

    public RoomObject() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCMNDNumber() {
        return CMNDNumber;
    }

    public void setCMNDNumber(int CMNDNumber) {
        this.CMNDNumber = CMNDNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public RoomObject(int stt, String name, int CMNDNumber, int roomNumber, String timeStay, int money) {
        this.stt = stt;
        this.name = name;
        this.CMNDNumber = CMNDNumber;
        this.roomNumber = roomNumber;
        this.checkinTime = timeStay;
        this.money = money;
    }

    public RoomObject(int stt, String name, int CMNDNumber, int roomNumber,
                      String checkinTime, String checkoutTime, int money) {
        this.stt = stt;
        this.name = name;
        this.CMNDNumber = CMNDNumber;
        this.roomNumber = roomNumber;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.money = money;
    }


    private String name;
    private int CMNDNumber;
    private int roomNumber;
    private String checkinTime, checkoutTime;
    private int money;

    public String toCSVForm() {
        return stt + ", " + name + ", " + CMNDNumber + ", " + roomNumber + ", " + checkinTime + ", " + money + "\n";
    }

    public String toCSVHistoryForm() {
        return stt + ", " + name + ", " + CMNDNumber + ", " + roomNumber + ", " + checkinTime + ", "
                + checkinTime + ", " + money + "\n";
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }


    public void toStringBoard() {
        System.out.printf("                         |%-3s |", stt);
        System.out.printf("%-15s |", name);
        System.out.printf("%-11s |", CMNDNumber);
        System.out.printf("%-11s |", roomNumber);
        System.out.printf("%-10s   |\n", checkinTime);

    }

    public void toHistoryString() {
        System.out.printf("                         |%-3s |", stt);
        System.out.printf("%-15s |", name);
        System.out.printf("%-15s |", roomNumber);
        System.out.printf("%-15s |", checkinTime);
        System.out.printf("%-15s |", checkoutTime);
        System.out.printf("%-10s   |\n", money);

    }

    public static void disappear() {
        for (int clear = 0; clear < 30; clear++) {
            System.out.println("\b");
        }
    }


    public void checkIn() {
        Scanner sc = new Scanner(System.in);
        String regexName = "^[A-Za-z]*+$";
        disappear();
        System.out.println("Welcome Binh Hu Motels! |");
        System.out.println("0.Currently Customer    |");
        System.out.print("1.Check In              |      -->  Input name:  ");
        name = sc.nextLine();


        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherName = patternName.matcher(name);
        while (!matcherName.find()) {
            disappear();
            System.out.println("Welcome Binh Hu Motels! |");
            System.out.println("0.Currently Customer    |");
            System.out.print("1.Check In              |    -->  Please input right Name:  ");
            name = sc.nextLine();
            matcherName = patternName.matcher(name);
        }


        String regex = "^\\d{9}$";
        disappear();
        System.out.println("Welcome Binh Hu Motels! |");
        System.out.println("0.Currently Customer    |");
        System.out.print("1.Check In              |      -->  Input Customer CMND:  ");
        String CMNDNumber = sc.nextLine();


        Pattern pattern1 = Pattern.compile(regex);
        Matcher matcher1 = pattern1.matcher(CMNDNumber);

        while (!matcher1.find()) {
            disappear();
            System.out.println("Welcome Binh Hu Motels! |");
            System.out.println("0.Currently Customer    |");
            System.out.print("1.Check In              |      -->  Please input 9 number!  ");
            CMNDNumber = sc.nextLine();

            matcher1 = pattern1.matcher(CMNDNumber);
        }

        this.CMNDNumber = Integer.parseInt(CMNDNumber);
        for (RoomObject object : roomObjectLinkedList) {
            if (object.getCMNDNumber() == this.CMNDNumber) {
                System.out.println("The CMND Number is existed!");
                System.out.println("    --------------");
                System.out.println();
                return;
            }
        }

        do {
            disappear();
            System.out.println("Welcome Binh Hu Motels! |");
            System.out.println("0.Currently Customer    |");
            System.out.print("1.Check In              |     -->  Input room number 1-10:  ");
            while (!sc.hasNextInt()) {
                disappear();
                System.out.println("Welcome Binh Hu Motels! |");
                System.out.println("0.Currently Customer    |");
                System.out.print("1.Check In              |      -->  Please input form 1 to 10!  ");

                sc.next();
            }
            this.roomNumber = sc.nextInt();

        } while (roomNumber < 1 || roomNumber > 10);

        for (RoomObject object : roomObjectLinkedList) {
            if (object.getRoomNumber() == this.roomNumber) {
                System.out.println("The Room Number is existed!");
                System.out.println("    --------------");
                System.out.println();
                return;
            }
        }

        sc.nextLine();
        disappear();
        System.out.println("Welcome Binh Hu Motels! |");
        System.out.println("0.Currently Customer    |");
        System.out.print("1.Check In              |      -->  Input day:  ");
        String day = sc.nextLine();


        String regexDay = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
        Pattern patternDay = Pattern.compile(regexDay);
        Matcher matcherDay = patternDay.matcher(day);
        while (!matcherDay.find()) {
            disappear();
            System.out.println("Welcome Binh Hu Motels! |");
            System.out.println("0.Currently Customer    |");
            System.out.print("1.Check In              |  -->  Please input dd/mm/yy or dd-mm-yy:  ");
            day = sc.nextLine();
            matcherDay = patternDay.matcher(day);
        }
        checkinTime = "";
        checkinTime += day;

        int hour;
        do {
            disappear();
            System.out.println("Welcome Binh Hu Motels! |");
            System.out.println("0.Currently Customer    |");
            System.out.print("1.Check In              |      -->  Input hour from 1 to 23:  ");
            while (!sc.hasNextInt()) {
                disappear();
                System.out.println("Welcome Binh Hu Motels! |");
                System.out.println("0.Currently Customer    |");
                System.out.print("1.Check In              |      -->  Please input from 1 to 23:  ");
                sc.nextInt();
            }
            hour = sc.nextInt();
        } while (hour <= 0 || hour >= 24);
        sc.nextLine();


        checkinTime += " " + hour + "h";
        disappear();

    }


}

