/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\Users\\Administrator\\workspace\\android\\AIDLObserve\\src\\com\\example\\aidlobserve\\aidl\\IOnNewBookArrivedListener.aidl
 */
package com.example.aidlobserve.aidl;
public interface IOnNewBookArrivedListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.example.aidlobserve.aidl.IOnNewBookArrivedListener
{
private static final java.lang.String DESCRIPTOR = "com.example.aidlobserve.aidl.IOnNewBookArrivedListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.example.aidlobserve.aidl.IOnNewBookArrivedListener interface,
 * generating a proxy if needed.
 */
public static com.example.aidlobserve.aidl.IOnNewBookArrivedListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.example.aidlobserve.aidl.IOnNewBookArrivedListener))) {
return ((com.example.aidlobserve.aidl.IOnNewBookArrivedListener)iin);
}
return new com.example.aidlobserve.aidl.IOnNewBookArrivedListener.Stub.Proxy(obj);
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
case TRANSACTION_onNewBookArrivedListener:
{
data.enforceInterface(DESCRIPTOR);
com.example.aidlobserve.aidl.Book _arg0;
if ((0!=data.readInt())) {
_arg0 = com.example.aidlobserve.aidl.Book.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onNewBookArrivedListener(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.example.aidlobserve.aidl.IOnNewBookArrivedListener
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
@Override public void onNewBookArrivedListener(com.example.aidlobserve.aidl.Book book) throws android.os.RemoteException
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
mRemote.transact(Stub.TRANSACTION_onNewBookArrivedListener, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onNewBookArrivedListener = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onNewBookArrivedListener(com.example.aidlobserve.aidl.Book book) throws android.os.RemoteException;
}
