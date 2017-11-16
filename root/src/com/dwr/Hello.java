package com.dwr;
public class Hello {
	private String name;
	public Hello(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String sayHello(String name) {
        return "Hello, " + name;
    }

}