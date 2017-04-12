package com.example.aidlobserve.aidl;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * book 实体类要实现Parcelable，才可以进程间传输
 * @author yl
 *
 */
public class Book implements Parcelable{

	
	public int bookId;
	public String bookName;
	
	
	
	public Book(int bookId, String bookName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(bookId);
		out.writeString(bookName);
	}

	public static final Parcelable.Creator<Book> CREATOR=new Creator<Book>() {
		
		@Override
		public Book[] newArray(int size) {
			
			return new Book[size];
		}
		
		@Override
		public Book createFromParcel(Parcel in) {
			
			return new Book(in);
		}
	};
	
	private Book(Parcel in){
		bookId=in.readInt();
		bookName=in.readString();
	}
}
