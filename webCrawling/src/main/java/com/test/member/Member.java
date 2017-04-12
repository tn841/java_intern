package com.test.member;

public class Member {
	private int member_no;
	private String member_name;
	private int member_age;
	
	
	
	
	public Member() {
		super();
	}

	public Member(String member_name, int member_age) {
		super();
		this.member_name = member_name;
		this.member_age = member_age;
	}

	public Member(int member_no, String member_name, int member_age) {
		super();
		this.member_no = member_no;
		this.member_name = member_name;
		this.member_age = member_age;
	}
	
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getMember_age() {
		return member_age;
	}
	public void setMember_age(int member_age) {
		this.member_age = member_age;
	}
	
	
	
	
}
