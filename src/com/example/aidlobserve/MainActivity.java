package com.example.aidlobserve;

import java.util.List;

import com.example.aidlobserve.aidl.Book;
import com.example.aidlobserve.aidl.BookManagerService;
import com.example.aidlobserve.aidl.IBookManager;
import com.example.aidlobserve.aidl.IOnNewBookArrivedListener;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
	private IBookManager mRemoteBookManager;

	/**
	 * 用于更新UI
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MESSAGE_NEW_BOOK_ARRIVED:
				Log.d(TAG, "receive new book:" + msg.obj);
				break;

			default:
				super.handleMessage(msg);
			}
		};
	};

	private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

		@Override
		public void onNewBookArrivedListener(Book book) throws RemoteException {
			//切换到主线程更新UI
			handler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, book).sendToTarget();
			
		}
	};

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mRemoteBookManager = null;
			Log.e(TAG, "binder died");
		}

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			IBookManager bookManager = IBookManager.Stub.asInterface(arg1);
			mRemoteBookManager = bookManager;
			try {
				List<Book> list = bookManager.getBookList();
				//getCanonicalName()用于获取list的类型
				Log.i(TAG, "query book list,list type:" + list.getClass().getCanonicalName());
				Book newBook = new Book(3, "Android 进阶");
				bookManager.addBook(newBook);
				Log.i(TAG, "add book:" + newBook);
				List<Book> newlist = bookManager.getBookList();
				Log.i(TAG, "query book list:" + newlist.toString());
				//注册监听操作
				bookManager.registerListener(mOnNewBookArrivedListener);

			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//绑定service
		Intent intent = new Intent(this, BookManagerService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

	}

	@Override
	protected void onDestroy() {
		//注销监听操作
		if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
			try {
				Log.i(TAG, "unregister listener:" + mOnNewBookArrivedListener);
				mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		//断开连接
		unbindService(mConnection);

		super.onDestroy();
	}
}
