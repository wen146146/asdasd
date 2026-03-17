package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.mapper.AddressMapper;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.util.MybatisUtlils;

public class AddressServiceImpl implements AddressService {

	private AddressMapper addressMapper = MybatisUtlils.getSqlSession().getMapper(AddressMapper.class);
	
	@Override
	public List<Address> findByUserId(Integer userId) {
		// 数据库操作: SELECT * FROM address WHERE user_id = ?
		// 参数: userId - 用户ID
		// 返回: 该用户的所有地址列表
		return addressMapper.findByUserId(userId);
	}
	
	@Override
	public Address findById(Integer id) {
		// 数据库操作: SELECT * FROM address WHERE id = ?
		// 参数: id - 地址ID
		// 返回: 地址对象，找不到返回null
		return addressMapper.findById(id);
	}
	
	@Override
	public int add(Address address) {
		// 数据库操作: INSERT INTO address (...)
		// 参数: address - 地址对象（需包含用户ID、收货人、手机、地址等信息）
		// 返回: 受影响的行数（成功返回1）
		int result = addressMapper.add(address);
		MybatisUtlils.getSqlSession().commit();
		return result;
	}
	
	@Override
	public int update(Address address) {
		// 数据库操作: UPDATE address SET ... WHERE id = ?
		// 参数: address - 地址对象（需包含ID和要修改的字段）
		// 返回: 受影响的行数（成功返回1）
		int result = addressMapper.update(address);
		MybatisUtlils.getSqlSession().commit();
		return result;
	}
	
	@Override
	public int delete(Integer id) {
		// 数据库操作: DELETE FROM address WHERE id = ?
		// 参数: id - 地址ID
		// 返回: 受影响的行数（成功返回1）
		int result = addressMapper.delete(id);
		MybatisUtlils.getSqlSession().commit();
		return result;
	}
}
