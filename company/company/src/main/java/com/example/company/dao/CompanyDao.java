package com.example.company.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.company.dto.CommonData;
import com.example.company.dto.Commute;
import com.example.company.dto.Emp;

@Mapper
public interface CompanyDao {
	public Emp findCommon(int empno);
		
	public Commute findComeOut(int empno);
	
	public void checkIn(int empno);
	
	public void checkOut(int empno);
	
	public List<CommonData> getCommonLists(int empno, int start, int end); 
	
	public int getDataCount(int empno);
	
	public void deleteCommute(int empno);
	
	public void deleteEmp(int empno);
	
	public void insertEmployee(String name);
	
	public int getNowEmpno();
	
	public List<CommonData> getListForAdmin(int start, int end, String date);
	
	public int getDataCountForAdmin(String date);
	
	public int getDataCountForFire();
	
	public List<Emp> getListForFire(int start, int end);
}
