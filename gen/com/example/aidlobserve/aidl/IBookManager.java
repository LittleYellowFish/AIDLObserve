/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\Users\\Administrator\\workspace\\android\\AIDLObserve\\src\\com\\example\\aidlobserve\\aidl\\IBookManager.aidl
 */
package com.example.aidlobserve.aidl;
public interface IBookManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.aidlobserve.aidl.IBookManager
{
private static final java.lang.String DESCRIPTOR = "com.example.aidlobserve.aidl.IBookManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.aidlobserve.aidl.IBookManager interface,
 * generating a proxy if needed.
 */
public static com.example.aidlobserve.aidl.IBookManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.aidlobserve.aidl.IBookManager))) {
return ((com.example.aidlobserve.aidl.IBookManager)iin);
}
return new com.example.aidlobserve.aidl.IBookManager.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getBookList:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<com.example.aidlobserve.aidl.Book> _result = this.getBookList();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_addBook:
{
data.enforceInterface(DESCRIPTOR);
com.example.aidlobserve.aidl.Book _arg0;
if ((0!=data.readInt())) {
_arg0 = com.example.aidlobserve.aidl.Book.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addBook(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_registerListener:
{
data.enforceInterface(DESCRIPTOR);
com.example.aidlobserve.aidl.IOnNewBookArrivedListener _arg0;
_arg0 = com.example.aidlobserve.aidl.IOnNewBookArrivedListener.Stub.asInterface(data.readStrongBinder());
this.registerListener(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterListener:
{
data.enforceInterface(DESCRIPTOR);
com.example.aidlobserve.aidl.IOnNewBookArrivedListener _arg0;
_arg0 = com.example.aidlobserve.aidl.IOnNewBookArrivedListener.Stub.asInterface(data.readStrongBinder());
this.unregisterListener(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.aidlobserve.aidl.IBookManager
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.util.List<com.example.aidlobserve.aidl.Book> getBookList() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.example.aidlobserve.aidl.Book> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getBookList, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.example.aidlobserve.aidl.Book.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void addBook(com.example.aidlobserve.aidl.Book book) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((book!=null)) {
_data.writeInt(1);
book.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registerListener(com.example.aidlobserve.aidl.IOnNewBookArrivedListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterListener(com.example.aidlobserve.aidl.IOnNewBookArrivedListener listener) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((listener!=null))?(listener.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregisterListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_registerListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregisterListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public java.util.List<com.example.aidlobserve.aidl.Book> getBookList() throws android.os.RemoteException;
public void addBook(com.example.aidlobserve.aidl.Book book) throws android.os.RemoteException;
public void registerListener(com.example.aidlobserve.aidl.IOnNewBookArrivedListener listener) throws android.os.RemoteException;
public void unregisterListener(com.example.aidlobserve.aidl.IOnNewBookArrivedListener listener) throws android.os.RemoteException;
}
