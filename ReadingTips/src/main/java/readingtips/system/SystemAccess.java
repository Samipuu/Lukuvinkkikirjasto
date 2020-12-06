package readingtips.system;

import java.util.concurrent.TimeUnit;

import readingtips.Book;
import readingtips.Tip;

public class SystemAccess {

    public static void main(String[] args) {
        SystemAccess systemAccess = new SystemAccess();
        Book book = new Book();
        systemAccess.open(book);
    }

    public void open(Tip tip) {

        System.out.println("doing something 10 secs..");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("done.");

    }

}
