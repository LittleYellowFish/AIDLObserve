package com.example.aidlobserve.aidl;

import com.example.aidlobserve.aidl.Book;

interface IOnNewBookArrivedListener{
	void onNewBookArrivedListener(in Book book);
}