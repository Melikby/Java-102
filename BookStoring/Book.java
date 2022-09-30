public class Book implements Comparable<Book> {
    private String bookName, authorName;
    private int numberOfPages, releaseDate;

    public Book(String bookName,int numberoOfPages,String authorName,int releaseDate){
        this.bookName=bookName;
        this.numberOfPages =numberoOfPages;
        this.authorName=authorName;
        this.releaseDate=releaseDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int compareTo(Book o) {
        return getBookName().compareTo(o.getBookName());
    }
}
