package com.zhenjinzi.util;

import java.util.LinkedList;

/**
 * 多线程模型之队列，存储流水作业中的数据
 * 
 * @author peter
 * 
 */
public class MQueue<T> {

	private String name; // 队列名称
	private LinkedList<T> items; // 队列数据
	private int limitNum = 65535; //队列元素个数限制
	
	public MQueue() {
		items = new LinkedList<T>();
	}

	public MQueue(String qName) {
		this.name = name;
		items = new LinkedList<T>();
	}
	
	public MQueue(int num) {
		items = new LinkedList<T>();
		this.limitNum = num;
	}

	public MQueue(String qName,int num) {
		this.name = name;
		this.limitNum = num;
		items = new LinkedList<T>();
	}
	
	public boolean isEmpty(){
		return this.items.isEmpty();
	}
	
	public int size(){
		return this.items.size();
	}
	
	public boolean contains(T o){
		return this.items.contains(o);
	}

	/**
	 * 插入待处理数据对象到队列
	 * 
	 * @param o T
	 */
	public Object push(T o) {
		synchronized (items) {
			if (items.size() >= this.limitNum) {
				try {
					items.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			items.add(o);
			items.notifyAll();
			return o;
		}
	}

	/**
	 * 取出队列头数据,如果队列为空,则等待
	 * 
	 * @return T
	 */
	public synchronized T pull() {
		synchronized (items) {
			T obj = null;
			if (items.size() == 0) {
				try {
					items.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			obj = items.removeFirst();
			return obj;
		}
	}
	
	/**
	 * 读取队列头数据（最早插入）,如果队列为空,则返回null
	 * 
	 * @return T
	 */
	public  T getHead() {
			return items.size()==0 ? null : items.getFirst();
	}
	
	/**
	 * 读取队列尾数据（最后插入）,如果队列为空,则返回null
	 * 
	 * @return T
	 */
	public  T getTail() {
			return items.size()==0 ? null : items.getLast();
	}
}
