package com.example.jason.multithreading;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jason on 2/17/2015.
 */
public class saveThread implements Runnable {
    private FileOutputStream outputStream;

    saveThread(FileOutputStream outputStream){
        this.outputStream = outputStream;
    }

    public FileOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void run() {
        for(int x = 1; x < 11; x++){
            String output = x + "\n";
            try {
                outputStream.write(output.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
