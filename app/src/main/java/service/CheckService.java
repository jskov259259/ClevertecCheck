package service;

import dao.ItemDao;
import dao.ItemDaoCollection;
import dao.ItemDaoFile;

public class CheckService {

    private ItemDao itemDaoCollection = new ItemDaoCollection();
    private ItemDao itemDaoFile = new ItemDaoFile();

    public void executeData(String[] args, boolean fileExistence, boolean cardExistence) {


        System.out.println("executing data " + fileExistence + " " + cardExistence);
    }
}
