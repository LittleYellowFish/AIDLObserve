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
	
	//�����Booleanֵ�ı仯��ʱ��������֮����룬���ֲ�����ԭ����
	private AtomicBoolean mIsServiceDestroy=new AtomicBoolean(false);
	
	//CopyOnWriteArrayList��ArrayList ��һ���̰߳�ȫ�ı���,�߲���
	private CopyOnWriteArrayList<Book> mBookList=new CopyOnWriteArrayList<>();
	
	//ר������ɾ�������listener�Ľӿڣ��ڲ�ʹ��mapʵ�ֵ�
	private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList=new RemoteCallbackList<>();
	
	/**
	 * ��ȡIBookManager��Binder
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
	 * ���鵽��
	 * @param book
	 */
	private void onNewBookArrived(Book book){
		mBookList.add(book);
		Log.d(TAG, "onNewBookArrived,notify listeners");
		//beginBroadcast������finishBroadcast��������ɶ�ʹ��
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
	 * ʵ��ÿ��5s���һ������
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
