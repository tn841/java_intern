package com.test.member;

import java.util.ArrayList;

import com.test.mapper.MemberMapper;

public class MemberDaoImpl implements MemberDaoInterface{
	
	MemberMapper memberMapper;
	
	public void setMemberMapper(MemberMapper memberMapper) {
		System.out.println(" *** MemberDaoImpl에서 setMemberMapper() ***");
		this.memberMapper = memberMapper;
	}

	public int insert_member(Member member) {
		return memberMapper.insert_member(member);
	}

	public int delete_member(int no) {
		// TODO Auto-generated method stub
		return memberMapper.delete_member(no);
	}

	public Member select(int no) {
		Member m = memberMapper.select_by_no(no);
		System.out.println("MemberDaoImpl select("+no+"), find member : "+m);
		return m;
	}

	public ArrayList<Member> selectAll() {
		return memberMapper.selectAll();
	}

	public int update(Member member) {
		return memberMapper.update_member(member);
	}

	@Override
	public int select_last_no() {
		// TODO Auto-generated method stub
		return memberMapper.select_last_no();
	}
	
}
