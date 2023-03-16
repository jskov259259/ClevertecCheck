package ru.clevertec.check.writer;

public class CheckConsoleWriter implements CheckWriter {

    @Override
    public void write(String check) {
        System.out.println(check);
    }
}
