package com.test.member;

import java.util.ArrayList;

public interface MemberDaoInterface {
	public int insert_member(Member member);
	public int delete_member(int no);
	public Member select(int no);
	public ArrayList<Member> selectAll();
	public int update(Member member);
	public int select_last_no();
	
}
