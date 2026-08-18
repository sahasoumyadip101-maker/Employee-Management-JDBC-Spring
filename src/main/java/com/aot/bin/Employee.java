package com.aot.bin;
//Model Layer
public class Employee {
	private Integer eno;
	private String ename;
	private Float esal;
	private String eaddr;
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getEno() {
		return eno;
	}


	public void setEno(Integer eno) {
		this.eno = eno;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public Float getEsal() {
		return esal;
	}


	public void setEsal(Float esal) {
		this.esal = esal;
	}


	public String getEaddr() {
		return eaddr;
	}


	public void setEaddr(String eaddr) {
		this.eaddr = eaddr;
	}


	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + ", esal=" + esal + ", eaddr=" + eaddr + "]";
	}
	
}
