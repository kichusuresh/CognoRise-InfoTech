package Library;
import java.util.ArrayList;
import java.util.List;
class Book {
	     private String title;
	     private String author;
	     private boolean checkedOut;

	     public Book(String title, String author) {
	         this.title = title;
	         this.author = author;
	         this.checkedOut = false;
	     }

	     public String getTitle() {
	         return title;
	     }

	     public String getAuthor() {
	         return author;
	     }

	     public boolean isCheckedOut() {
	         return checkedOut;
	     }

	     public void checkOut() {
	         checkedOut = true;
	     }

	     public void returnBook() {
	         checkedOut = false;
	     }

	     @Override
	     public String toString() {
	         return "Title: " + title + ", Author: " + author + ", Status: " + (checkedOut ? "Checked Out" : "Available");
	     }
	 }

class LibraryCatalog {
    private List<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public void checkOutBook(Book book) {
        if (!book.isCheckedOut()) {
            book.checkOut();
            System.out.println("Book checked out successfully: " + book.getTitle());
        } else {
            System.out.println("Book is already checked out: " + book.getTitle());
        }
    }

    public void returnBook(Book book) {
        if (book.isCheckedOut()) {
            book.returnBook();
            System.out.println("Book returned successfully: " + book.getTitle());
        } else {
            System.out.println("Book is already available: " + book.getTitle());
        }
    }

    public void displayCatalog() {
        System.out.println("Library Catalog:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        LibraryCatalog library = new LibraryCatalog();

        // Adding books to the library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen"));

        // Displaying the library catalog
        library.displayCatalog();

        // Searching for a book by title
        System.out.println("\nSearching for a book by title:");
        List<Book> searchResults = library.searchByTitle("To Kill a Mockingbird");
        for (Book book : searchResults) {
            System.out.println(book);
        }

        // Checking out a book
        System.out.println("\nChecking out a book:");
        Book bookToCheckOut = library.searchByTitle("To Kill a Mockingbird").get(0);
        library.checkOutBook(bookToCheckOut);
        library.displayCatalog();

        // Returning a book
        System.out.println("\nReturning a book:");
        library.returnBook(bookToCheckOut);
        library.displayCatalog();
    }
}

