package com.example.aidlobserve.aidl;
import com.example.aidlobserve.aidl.Book;
import com.example.aidlobserve.aidl.IOnNewBookArrivedListener;
interface IBookManager{
	List<Book> getBookList();
	void addBook(in Book book);
	void registerListener(IOnNewBookArrivedListener listener);
	void unregisterListener(IOnNewBookArrivedListener listener);
}