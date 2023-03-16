package ru.clevertec.check.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CheckFileWriter implements CheckWriter {

    @Override
    public void write(String check) {

        File file = new File("Check.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(fileWriter);

        try {
            writer.write(check);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
