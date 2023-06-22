package com.example.company.service;

import java.util.List;

import com.example.company.dto.CommonData;
import com.example.company.dto.Commute;
import com.example.company.dto.Emp;

public interface CompanyService {
	public Emp findCommon(int empno);
	
	public Commute findComeOut(int empno);
	
	public void checkIn(int empno);
	
	public void checkOut(int empno);
	
	public List<CommonData> getCommonLists(int empno,int start, int end);
	
	public int getDataCount(int empno);
}
