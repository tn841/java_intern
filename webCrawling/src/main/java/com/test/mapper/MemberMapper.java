package com.test.mapper;

import java.util.ArrayList;

import com.test.member.Member;

public interface MemberMapper {
	public int insert_member(Member member);
	public Member select_by_no(int no);
	public ArrayList<Member> selectAll();
	public int select_last_no();
	public int update_member(Member member);
	public int delete_member(int no);
}
