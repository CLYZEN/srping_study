package com.example.company.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.company.dto.Commute;
import com.example.company.dto.Emp;

@Mapper
public interface CompanyDao {
	public Emp findCommon(int empno);
		
	public Commute findComeOut(int empno);
	
	public void checkIn(int empno);
	
	public void checkOut(int empno);
}
