package by.bsu.ilyin.dao;

import java.io.File;

public class Database {

    final File db;

    public Database(String path) {
        this.db = new File(path);
    }

    public File getDb() {
        return this.db;
    }

}
