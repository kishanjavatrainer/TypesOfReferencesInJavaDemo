package com.infotech.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceExample {
	private String status = new String("KK JavaTutorials");
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ReferenceExample [status=" + status + "]";
	}
	public void strongReference() {
		ReferenceExample referenceExample = new ReferenceExample();
		System.out.println(referenceExample);
	}
	public void softReference() {
		SoftReference<ReferenceExample> softReference = new SoftReference<ReferenceExample>(getRefrence());
		System.out.println("Soft refrence :: " + softReference.get());
	}
	public void weakReference() {
		int counter = 0;
		WeakReference<ReferenceExample> ex = new WeakReference<ReferenceExample>(getRefrence());
		while (ex.get() != null) {
			counter++;
			System.gc();
			System.out.println("Weak reference deleted  after counts:: " + counter +",Week Reference Value:"+ ex.get());
		}
	}
	public void phantomReference() throws InterruptedException {
		final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
		PhantomReference<ReferenceExample> ex = new PhantomReference<ReferenceExample>(getRefrence(), queue);
		System.gc();
		queue.remove();
		System.out.println("Phantom reference deleted:");
		System.out.println(ex.get());
	}
	private ReferenceExample getRefrence() {
		return new ReferenceExample();
	}
	public static void main(String[] args) {
		ReferenceExample ex = new ReferenceExample();
		ex.strongReference();
		ex.softReference();
		ex.weakReference();
		try {
			ex.phantomReference();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
