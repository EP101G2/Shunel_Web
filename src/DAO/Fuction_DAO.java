package DAO;

import java.util.List;

import Bean.Fuction;


public interface Fuction_DAO {
	int insert( Fuction fuction);
	int update(Fuction user_Account);
	int delete(int Fuction_ID);
	Fuction findById(int Fuction_ID);
	List<Fuction> getAll();

}
