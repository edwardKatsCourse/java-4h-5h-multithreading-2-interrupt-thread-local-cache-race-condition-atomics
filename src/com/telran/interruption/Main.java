package com.telran.interruption;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    //Thread...
        //Работники компании
        //Аеропорт (самолет - тред)
        Worker worker = new Worker();
        worker.start();

        while (!worker.isInterrupted()) {
            System.out.println("Enter command: ");
            String consoleInput = new Scanner(System.in).nextLine();
            if (consoleInput.equals("interrupt")) {
                worker.interrupt();
                break;
            }
        }

        System.out.println("Main ended");

        ///



//        worker.stop();
//        worker.destroy();
//        worker.suspend();
//        worker.resume();

        //Interruption


    }
}

/**
 * Use another class
 */
//@Deprecated
class Worker extends Thread {
    //boolean isInterrupted;

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(isInterrupted()); //isInterrupted() remains false
                interrupt(); //isInterrupted() -> true
            }

            if (isInterrupted()) { //isInterrupted - is true
                System.out.println("Thread interrupted: " + isInterrupted());
            }
        }
    }
}
