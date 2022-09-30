import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Book>book=new TreeSet<>();
        Book book1 = new Book("Şeker Portakalı", 183, "Jose Mauro de Vasconcelos", 1968);
        Book book2 = new Book("Kadınlar Sıcak Erkekler Soğuk Sever", 296, "Esra Ezmeci", 2021);
        Book book3 = new Book("İz Bıraktığın Kadar Varsın", 328, "Esra Ezmeci", 2021);
        Book book4 = new Book("Gece Yarısı Kütüphanesi", 295, "Matt Haig", 2021);
        Book book5 = new Book("Mermer Adam", 608, "Jean-Christophe Grange", 2022);

        book.add(book1);
        book.add(book2);
        book.add(book3);
        book.add(book4);
        book.add(book5);

        System.out.println("Sort By Name");
        for(Book bookName: book){
            System.out.println("Book Name: " + bookName.getBookName()+"\nNumber Of Page : " + bookName.getNumberOfPages() +
                    "\nAuthor Name : " + bookName.getAuthorName() +
                    "\nRelased Date : " + bookName.getReleaseDate());
            System.out.println("****************************************************");
        }
        System.out.println("\n\nSort By Pagesize");
        TreeSet<Book> books = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getNumberOfPages() - o2.getNumberOfPages();
            }
        });
        book.add(book1);
        book.add(book2);
        book.add(book3);
        book.add(book4);
        book.add(book5);
        for(Book bookPage: books){
            System.out.println("Book Name : " + bookPage.getBookName() +
                    "\nNumber Of Page : " + bookPage.getNumberOfPages() +
                    "\nAuthor Name : " + bookPage.getAuthorName() +
                    "\nRelased Date : " + bookPage.getReleaseDate());
            System.out.println("****************************************************");
        }
    }
}
