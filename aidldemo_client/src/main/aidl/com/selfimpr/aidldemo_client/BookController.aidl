// BookController.aidl
package com.selfimpr.aidldemo;
import com.selfimpr.aidldemo.Book;
// Declare any non-default types here with import statements

interface BookController {
    List<Book> getBookList();
    void addBookList(inout Book book);
}
