package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Address;
import java.util.List;

public interface AddressMapper {
	// 数据库操作: SELECT * FROM address WHERE user_id = ?
	// 参数: userId - 用户ID
	// 返回: 该用户的所有地址列表
	List<Address> findByUserId(Integer userId);
	
	// 数据库操作: SELECT * FROM address WHERE id = ?
	// 参数: id - 地址ID
	// 返回: 地址对象，找不到返回null
	Address findById(Integer id);
	
	// 数据库操作: INSERT INTO address (...)
	// 参数: address - 地址对象
	// 返回: 受影响的行数（成功返回1）
	Integer add(Address address);
	
	// 数据库操作: UPDATE address SET ... WHERE id = ?
	// 参数: address - 地址对象
	// 返回: 受影响的行数（成功返回1）
	Integer update(Address address);
	
	// 数据库操作: DELETE FROM address WHERE id = ?
	// 参数: id - 地址ID
	// 返回: 受影响的行数（成功返回1）
	Integer delete(Integer id);
}
