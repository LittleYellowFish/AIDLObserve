package com.example.aidlobserve.aidl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class BookManagerService extends Service{

	private static final String TAG="BookManagerService";
	
	//在这个Boolean值的变化的时候不允许在之间插入，保持操作的原子性
	private AtomicBoolean mIsServiceDestroy=new AtomicBoolean(false);
	
	//CopyOnWriteArrayList是ArrayList 的一个线程安全的变体,高并发
	private CopyOnWriteArrayList<Book> mBookList=new CopyOnWriteArrayList<>();
	
	//专门用于删除跨进程listener的接口，内部使用map实现的
	private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList=new RemoteCallbackList<>();
	
	/**
	 * 获取IBookManager的Binder
	 */
	private Binder mBinder=new IBookManager.Stub() {
		
		@Override
		public List<Book> getBookList() throws RemoteException {
			return mBookList;
		}
		
		@Override
		public void addBook(Book book) throws RemoteException {
			mBookList.add(book);
		}

		@Override
		public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
			mListenerList.register(listener);
			Log.i(TAG, "register success");
		}

		@Override
		public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
			mListenerList.unregister(listener);
			Log.i(TAG, "unregister success");
		}
	};
	
	/**
	 * 新书到达
	 * @param book
	 */
	private void onNewBookArrived(Book book){
		mBookList.add(book);
		Log.d(TAG, "onNewBookArrived,notify listeners");
		//beginBroadcast（）和finishBroadcast（）必须成对使用
		final int N=mListenerList.beginBroadcast();
		for(int i=0;i<N;i++){
			IOnNewBookArrivedListener l=mListenerList.getBroadcastItem(i);
			if(l!=null){
				try {
					l.onNewBookArrivedListener(book);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		mListenerList.finishBroadcast();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mBookList.add(new Book(1, "Android"));
		mBookList.add(new Book(2, "IOS"));
		
		new Thread(new ServiceWorker()).start();
	}
	
	/**
	 * 实现每隔5s添加一本新书
	 * @author yl
	 *
	 */
	private class ServiceWorker implements Runnable{

		@Override
		public void run() {
			while(!mIsServiceDestroy.get()){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int bookId=mBookList.size()+1;
				Book newBook=new Book(bookId, "new book"+bookId);
				onNewBookArrived(newBook);
			}
			
		}
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	@Override
	public void onDestroy() {
		mIsServiceDestroy.set(true);
		super.onDestroy();
	}
	
	
}
