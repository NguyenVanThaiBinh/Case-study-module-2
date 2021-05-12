package motels.presentation;

import motels.model.RoomObject;
import motels.service.MotelsService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MotelsMain extends Thread{
    @Override
    public void run() {
        MotelsService motelsService = new MotelsService();
        try {
            motelsService.loadFile();
        } catch (IOException e) {
            System.err.println("File is empty!");
            System.out.println();
        }
        try {
            motelsService.loadFileHistory();
        } catch (IOException e) {
            System.err.println("File  history is empty!");
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {

    MotelsService motelsService = new MotelsService();
       MotelsMain motelsMain = new MotelsMain();
       motelsMain.start();
        motelsMain.join();
        int choose;
        String choose1;
    do{
        showMenu();
        System.out.println("Your choice:");
        choose1 = new Scanner(System.in).nextLine();
        String regexchoose = "^[012345]{1}$";
        Pattern patternchoose = Pattern.compile(regexchoose);

        Matcher matcherchoose = patternchoose.matcher(choose1);
        while(!matcherchoose.find()){
            System.out.println("Please input right!");
            choose1 = new Scanner(System.in).nextLine();
            matcherchoose = patternchoose.matcher(choose1);
        }
         choose =Integer.parseInt(choose1);

        switch (choose){
            case 0:
                try {
                    motelsService.showList();
                  Thread.sleep(3000);
                    RoomObject roomObject = new RoomObject();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 1:

                try {
                    motelsService.checkIn();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:

                motelsService.addService();
                break;
            case 3:
                try {
                    motelsService.editCustomer();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
                try {
                    motelsService.showHistory();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                try {
                    motelsService.checkOut();
                } catch (IOException | ParseException e) {

                }
                break;
            case 6:
                System.out.println("Bye bye!");
                break;
            default:
                System.out.println("Wrong input!");
                break;

        }
    }while(choose != 6);

    }
    public  static  void showMenu(){
        System.out.println("Welcome Binh Hu Motels! ");
        System.out.println("0.Currently Customer    |");
        System.out.println("1.Check In              |");
        System.out.println("2.Add service           |");
        System.out.println("3.Edit Customer         |");
        System.out.println("4.Check Out             |");
        System.out.println("5.Show history          |");
        System.out.println("6.Exit                  |");
    }

}


