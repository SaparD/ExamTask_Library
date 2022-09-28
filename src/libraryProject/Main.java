package libraryProject;

import libraryProject.dao.Dao;
import libraryProject.model.Book;
import libraryProject.model.Library;
import libraryProject.model.LibraryMember;
import libraryProject.service.impl.LibraryServiceImpl;

import java.util.Scanner;

public class Main {
    static Scanner scannerN = new Scanner(System.in);
    static Scanner scannerS = new Scanner(System.in);
    public static void main(String[] args) {
        Dao dao = new Dao(new Library());
        LibraryServiceImpl libraryService = new LibraryServiceImpl(dao);
        //buttons();
        String a = "";
        do {
            a = pressButton();
            if (a.equals("1")) {
                System.out.print("Input LibraryMember's id: ");
                Long memberId = scannerN.nextLong();
                System.out.print("Input LibraryMember's name: ");
                String memberName = scannerS.nextLine();
                libraryService.addLibraryMember(new LibraryMember(memberId, memberName));
            } else if (a.equals("2")) {
                System.out.println(libraryService.getLibraryMembers());
            } else if (a.equals("3")) {
                System.out.print("Input LibraryMember's id: ");
                Long memberId = scannerN.nextLong();
                LibraryMember m = libraryService.findLibraryMemberById(memberId);
                System.out.println("LibraryMember: id=" + m.getMemberId() + ", name=" + m.getName() +
                        ", readingBook=" + (m.getCurrentlyReading() != null ? m.getCurrentlyReading().getTitle() : null) +
                        ", finishedBook=" + m.getFinishedBooks().stream().map(x -> x.getTitle()).toList());
            } else if (a.equals("4")) {
                System.out.print("Input LibraryMember's id: ");
                Long memberId = scannerN.nextLong();
                libraryService.deleteLibraryMemberByID(memberId);
            } else if (a.equals("5")) {
                System.out.print("Input Book's id: ");
                Long bookId = scannerN.nextLong();
                System.out.print("Input Book's title: ");
                String bookName = scannerS.nextLine();
                libraryService.addBookToLibrary(new Book(bookId, bookName));
            } else if (a.equals("6")) {
                System.out.println(libraryService.getLibraryBooks());
            } else if (a.equals("7")) {
                System.out.print("Input Book's id: ");
                Long bookId = scannerN.nextLong();
                System.out.println(libraryService.findLibraryBookById(bookId));
            } else if (a.equals("8")) {
                System.out.print("Input Book's id: ");
                Long bookId = scannerN.nextLong();
                libraryService.deleteLibraryBookByID(bookId);
            } else if (a.equals("9")) {
                System.out.print("Input LibraryMember's id: ");
                Long memberId = scannerN.nextLong();
                System.out.print("Input Book's id: ");
                Long bookId = scannerN.nextLong();
                libraryService.addBookToMember(memberId, bookId);
            } else if (a.equals("10")) {
                System.out.print("Input LibraryMember's id: ");
                Long memberId = scannerN.nextLong();
                System.out.print("Input Book's id: ");
                Long bookId = scannerN.nextLong();
                libraryService.removeBookFromReading(memberId, bookId);
            }
        } while (!a.equals("x"));
    }

    public static void buttons(){
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 1, чтобы добавить нового участника в библиотеку.");
        System.out.println("Нажмите 2, чтобы увидеть всех участников библиотеки.");
        System.out.println("Нажмите 3, чтобы найти участника по ID и увидеть данные участника, читаемая книга и прочитанные.");
        System.out.println("Нажмите 4, чтобы удалить участника по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 5, чтобы добавить книгу в библиотеку.");
        System.out.println("Нажмите 6, чтобы увидеть все книги в библиотеке.");
        System.out.println("Нажмите 7, чтобы найти книгу по ID.");
        System.out.println("Нажмите 8, чтобы удалить книгу по ID.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Нажмите 9, чтобы ввести memberId участника и bookId книги, добавить в читаемые");
        System.out.println("Нажмите 10, чтобы ввести memberId участника и bookId книги, добавить в прочитанные");
        System.out.println("Нажмите x, чтобы завершить программу.");
    }

    public static String pressButton(){
        System.out.print("Choose a command: ");
        return scannerS.nextLine();
    }
}
