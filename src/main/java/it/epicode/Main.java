package it.epicode;

import it.epicode.dao.implementations.JpaBookDao;
import it.epicode.dao.implementations.JpaMagazineDao;
import it.epicode.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        List<LibraryItem> libraryItems = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<BookLoan> bookLoans = new ArrayList<>();

        Book book1 = new Book(9783161484100L, "The Lord of the Rings - The Fellowship of the Ring", 1954, 956, "J.R.R. Tolkien", "Fantasy");
        Book book2 = new Book(9781408855652L, "Harry Potter and the Philosopher's Stone", 1997, 223, "J.K. Rowling", "Fantasy");
        Book book3 = new Book(9781408828652L, "Harry Potter and the Chamber of Secrets", 1998, 341, "J.K. Rowling", "Fantasy");
        Book book4 = new Book(9780439136365L, "Harry Potter and the Prisoner of Azkaban", 1999, 435, "J.K. Rowling", "Fantasy");
        Book book5 = new Book(9780439139595L, "Harry Potter and the Goblet of Fire", 2000, 734, "J.K. Rowling", "Fantasy");
        Book book6 = new Book(9780439358071L, "Harry Potter and the Order of the Phoenix", 2003, 870, "J.K. Rowling", "Fantasy");
        Book book7 = new Book(9780439785969L, "Harry Potter and the Half-Blood Prince", 2005, 652, "J.K. Rowling", "Fantasy");
        Book book8 = new Book(9780545010221L, "Harry Potter and the Deathly Hallows", 2007, 784, "J.K. Rowling", "Fantasy");
        Book book9 = new Book(9780395489314L, "The Lord of the Rings - The Two Towers", 1954, 352, "J.R.R. Tolkien", "Fantasy");
        Book book10 = new Book(9780395489307L, "The Lord of the Rings - The Return of the King", 1955, 416, "J.R.R. Tolkien", "Fantasy");
        Book book11 = new Book(9781582872919L, "Tess of the d'Urbervilles", 1891, 544, "Thomas Hardy", "Novel");
        Book book12 = new Book(9781569786729L, "The Castle of Otranto", 1764, 192, "Horace Walpole", "Gothic Novel");

        Magazine magazine1 = new Magazine(9781419704406L, "Vogue", 2024, 100, Periodicity.MONTHLY);
        Magazine magazine2 = new Magazine(9783161484100L, "National Geographic", 2023, 80, Periodicity.MONTHLY);
        Magazine magazine3 = new Magazine(9780321965516L, "Time", 2022, 64, Periodicity.WEEKLY);
        Magazine magazine4 = new Magazine(9780670020553L, "The New Yorker", 2023, 96, Periodicity.MONTHLY);
        Magazine magazine5 = new Magazine(9780679641635L, "Science", 2024, 120, Periodicity.SIX_MONTHLY);
        Magazine magazine6 = new Magazine(9780195153445L, "Nature", 2022, 88, Periodicity.MONTHLY);
        Magazine magazine7 = new Magazine(9781408128761L, "National Geographic Traveler", 2023, 72, Periodicity.MONTHLY);
        Magazine magazine8 = new Magazine(9780749477500L, "Harvard Business Review", 2024, 56, Periodicity.MONTHLY);
        Magazine magazine9 = new Magazine(9781510726189L, "Smithsonian", 2022, 84, Periodicity.MONTHLY);
        Magazine magazine10 = new Magazine(9781546076572L, "New Scientist", 2023, 48, Periodicity.WEEKLY);
        Magazine magazine11 = new Magazine(9781785417693L, "Discover", 2024, 68, Periodicity.MONTHLY);
        Magazine magazine12 = new Magazine(9781734625182L, "Popular Science", 2023, 32, Periodicity.MONTHLY);

        libraryItems.add(book1);
        libraryItems.add(book2);
        libraryItems.add(book3);
        libraryItems.add(book4);
        libraryItems.add(book5);
        libraryItems.add(book6);
        libraryItems.add(book7);
        libraryItems.add(book8);
        libraryItems.add(book9);
        libraryItems.add(book10);
        libraryItems.add(book11);
        libraryItems.add(book12);

        libraryItems.add(magazine1);
        libraryItems.add(magazine2);
        libraryItems.add(magazine3);
        libraryItems.add(magazine4);
        libraryItems.add(magazine5);
        libraryItems.add(magazine6);
        libraryItems.add(magazine7);
        libraryItems.add(magazine8);
        libraryItems.add(magazine9);
        libraryItems.add(magazine10);
        libraryItems.add(magazine11);
        libraryItems.add(magazine12);

        logger.debug("Aggiunta degli items Book e Magazine a libraryItems:");
        libraryItems.forEach(libraryItem -> System.out.println(libraryItem));

        logger.debug("Aggiunta degli items al db:");

        try (JpaBookDao bookDao = new JpaBookDao();
             JpaMagazineDao magazineDao = new JpaMagazineDao()) {

            libraryItems.forEach(item -> {
                if (item instanceof Book) {
                    bookDao.addItem((Book) item);
                } else if (item instanceof Magazine) {
                    magazineDao.addItem((Magazine) item);
                } else {
                    logger.warn("Tipo non gestito: {}", item.getClass().getName());
                }
            });

        } catch (Exception e) {
            logger.error("Errore durante l'esecuzione delle operazioni", e);
        }







    }
}