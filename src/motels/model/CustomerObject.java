package motels.model;

import java.util.Scanner;

public class CustomerObject {
    Scanner sc = new Scanner(System.in);
    private int stt;
    private String name;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    private int CMNDNumber;

    public CustomerObject() {
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

    public CustomerObject(int stt, String name, int CMNDNumber) {
        this.stt = stt;
        this.name = name;
        this.CMNDNumber = CMNDNumber;
    }

    public String toCSVFormat() {
        return stt + "," + name + "," + CMNDNumber + "\n";
    }
    public void inputCustomer(){
        System.out.println("Input name:");
        this.name = sc.nextLine();
        sc.nextLine();
        System.out.println("Input CMND Numer:");
        this.CMNDNumber = sc.nextInt();
    }
}
