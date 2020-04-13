package com.demo.framework.service;

import com.demo.framework.dao.entity.*;
import com.demo.framework.dao.enums.CriteriaEnum;
import com.demo.framework.dao.enums.OrderEnum;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface BaseService<T> {

	/**
	 * 将瞬态（自由态）的实体加入EntityManage，变成持久化对象
	 * 
	 * @param o
	 * @return boolean 是否成功
	 */
	public void save(T o);

	/**
	 * 将瞬态（自由态）的实体加入EntityManage，变成持久化对象
	 * 
	 * @param o
	 * @param flush 是否同步数据库
	 * @return boolean 是否成功
	 */
	public void save(T o, boolean flush);

	/**
	 * 将持久化的对象变成游离态（托管状态）
	 * 
	 * @param clazz 对象
	 * @param id    对象ID
	 * @return boolean 是否成功
	 */
	public void delete(Object id);

	/**
	 * 将持久化的对象变成游离态（托管状态）
	 * 
	 * @param c     对象类型
	 * @param flush 是否同步数据库
	 * @param id    对象ID
	 * @return boolean 是否成功
	 */
	public void delete(Object id, boolean flush);

	/**
	 * 将持久化的对象变成游离态（托管状态）,并直接数据库删除
	 * 
	 * @param objects 对象组
	 * @return boolean 是否成功
	 */
	public void deleteByObjects(List<T> objects);

	/**
	 * 将持久化的对象变成游离态（托管状态）,并直接数据库删除
	 * 
	 * @param c   对象类型
	 * @param ids 对象ID组
	 * @return boolean 是否成功
	 */
	public void deleteByIds(List<Object> ids);

	/**
	 * 将我们提供的对象变成一个持久化对象，对象会纳入EnitiyManage
	 * 
	 * @param o
	 * @return boolean 是否成功
	 */
	public void saveOrUpdate(T o);

	/**
	 * 将我们提供的对象变成一个持久化对象，对象会纳入EnitiyManage
	 * 
	 * @param o
	 * @return
	 */
	public T updateOrSave(T o);

	/**
	 * 将我们提供的对象变成一个持久化对象，对象会纳入EnitiyManage
	 * 
	 * @param o
	 * @param flush 是否同步数据库
	 * @return boolean 是否成功
	 */
	public void saveOrUpdate(T o, boolean flush);

	/**
	 * 将我们提供的对象组变成一个持久化对象，对象会纳入EnitiyManage,并写入数据库
	 * 
	 * @param os
	 * @return boolean 是否成功
	 */
	public void saveOrUpdate(List<T> os);

	/**
	 * 获得一个单个对象
	 * 
	 * @param c  对象类型
	 * @param id 对象ID
	 * @return Object
	 */
	public T get(Object id);

	/**
	 * @param ids
	 * @return
	 */
	public List<T> get(Object[] ids, String pk);

	public List<T> getByIds(Object[] ids);

	/**
	 * 获得一个单个对象
	 * 
	 * @param c  对象类型
	 * @param id 对象UUID
	 * @return Object
	 */
	public T getByUuid(String uuid);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return List<T>或者List<Object[]>
	 */
	public List get(String hql, Object... param);

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param 参数数组
	 * @param page  查询第几页
	 * @param rows  每页显示几条记录
	 * @return List
	 */
	public List get(String hql, Integer page, Integer rows, Object... param);

	/**
	 * select count(*) from 类
	 * 
	 * @param whereHql
	 * @param param    参数数组
	 * @return Long 记录数
	 */
	public Long count(String whereHql, Object... param);

	/**
	 * 执行JPQL语句insert，update，delete等
	 * 
	 * @param hql
	 * @param param 参数数组
	 * @return Integer 响应数目
	 */
	public Integer executeJpql(String hql, Object... param);

	/**
	 * 执行JPQL语句,select
	 * 
	 * @param hql
	 * @param param 参数数组
	 * @return Object 返回唯一值
	 */
	public Object getJpqlRetUnique(String hql, Object... param);

	/**
	 * 执行SQL语句,select
	 * 
	 * @param sql
	 * @param param 参数数组
	 * @return List<Object[]>
	 */
	public List getSQL(String sql, Object... param);

	/**
	 * 执行SQL语句,select
	 * 
	 * @param sql
	 * @param param 参数数组
	 * @return Object 返回唯一值
	 */
	public Object getSQLRetUnique(String sql, Object... param);

	/**
	 * 
	 * @param sql
	 * @param page
	 * @param rows
	 * @param param
	 * @return
	 */
	public List getSQL(String sql, Integer page, Integer rows, Object... param);

	/**
	 * 执行SQL语句,select
	 * 
	 * @param sql
	 * @param param 参数数组
	 * @return List<Map>
	 */
	public List getMapBySQL(String sql, Object... param);

	/**
	 * 
	 * @param sql
	 * @param page
	 * @param rows
	 * @param param
	 * @return
	 */
	public List getMapBySQL(String sql, Integer page, Integer rows, Object... param);

	/**
	 * 执行SQL语句,insert，update，delete等
	 * 
	 * @param sql
	 * @param param 参数数组
	 * @return Integer 响应数目
	 */
	public Integer executeSQL(String sql, Object... param);

	/**
	 * @param em
	 * @param c
	 * @param unit
	 * @return
	 */
	public Long totalCount(CriteriaUnit unit);

	/**
	 * @param c
	 * @param whereCondition
	 * @param params
	 * @return
	 */
	public Long totalCount(String whereCondition, Map<String, Object> params);

	/**
	 * @param c
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public Page<T> getPageByType(CriteriaUnit criteriaUnit, Page<T> page);

	/**
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public JqPage<T> getPageByType(CriteriaUnit criteriaUnit, JqPage<T> page);

	/**
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public LaPage<T> getPageByType(CriteriaUnit criteriaUnit, LaPage<T> page);

	/**
	 * @param c
	 * @param whereCondition
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public Page<T> getPageByType(String whereCondition, Page<T> page, Map<String, Object> paramMap);

	/**
	 * @param whereCondition
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public JqPage<T> getPageByType(String whereCondition, JqPage<T> page, Map<String, Object> paramMap);

	/**
	 * @param whereCondition
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public LaPage<T> getPageByType(String whereCondition, LaPage<T> page, Map<String, Object> paramMap);

	/**
	 * @param c
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public Page<T> getPageDistinctByType(CriteriaUnit criteriaUnit, Page<T> page);

	/**
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public JqPage<T> getPageDistinctByType(CriteriaUnit criteriaUnit, JqPage<T> page);

	/**
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public LaPage<T> getPageDistinctByType(CriteriaUnit criteriaUnit, LaPage<T> page);

	/**
	 * @param c
	 * @param criteriaUnit
	 * @return
	 */
	public List<T> getListByType(CriteriaUnit criteriaUnit);

	/**
	 * @param c
	 * @param criteriaUnit
	 * @param max
	 * @return
	 */
	public List<T> getListByType(CriteriaUnit criteriaUnit, int max);

	/**
	 * 通过jpql where条件查询对象集合
	 * 
	 * @param c
	 * @param whereCondition
	 * @param paramMap
	 * @return
	 */
	public List<T> getListByWereCondition(String whereCondition, Map<String, Object> paramMap);

	/**
	 * 通过jpql查询对象集合
	 * 
	 * @param c
	 * @param jpql
	 * @param paramMap
	 * @return
	 */
	public List<T> getListByJpql(String jpql, Map<String, Object> paramMap);

	/**
	 * 查询全部对象集合
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * 查询对象page分页数据
	 * 
	 * @param page
	 * @param criteriaConditions
	 * @param orderConditions
	 * @return
	 */
	public Page<T> getPage(Page<T> page, List<CriteriaCondition> criteriaConditions,
                           List<OrderCondition> orderConditions);

	/**
	 * 查询对象jqgrid分页数据
	 *
	 * @param jqPage
	 * @param criteriaConditions
	 * @param orderConditions
	 * @return
	 */
	public JqPage<T> getPage(JqPage<T> jqPage, List<CriteriaCondition> criteriaConditions,
                             List<OrderCondition> orderConditions);

	/**
	 * 查询对象lagrid分页数据
	 *
	 * @param jqPage
	 * @param criteriaConditions
	 * @param orderConditions
	 * @return
	 */
	public LaPage<T> getPage(LaPage<T> laPage, List<CriteriaCondition> criteriaConditions,
                             List<OrderCondition> orderConditions);

	/**
	 * 查询对象集合
	 *
	 * @param criteriaConditions
	 * @param orderConditions
	 * @return
	 */
	public List<T> getList(List<CriteriaCondition> criteriaConditions, List<OrderCondition> orderConditions);

	/**
	 * @author huangyh
	 *
	 * @param criteriaConditions
	 * @param orderConditions
	 * @param max
	 * @return
	 */
	public List<T> getList(List<CriteriaCondition> criteriaConditions, List<OrderCondition> orderConditions, int max);

	/**
	 * 查询第一个对象
	 *
	 * @param criteriaConditions
	 * @param orderConditions
	 * @return
	 */
	public T getFristModel(List<CriteriaCondition> criteriaConditions, List<OrderCondition> orderConditions);

	/**
	 * 通过对象属性，返回查到的对象集合
	 *
	 * @param property
	 * @param type
	 * @param value
	 * @return
	 */
	public List<T> getByProperty(String property, CriteriaEnum type, Object value);

	/**
	 * 通过对象属性，并设定排序，返回查到的对象集合
	 *
	 * @param property
	 * @param type
	 * @param value
	 * @param orderProperty
	 * @param order
	 * @return
	 */
	public List<T> getByProperty(String property, CriteriaEnum type, Object value, String orderProperty,
                                 OrderEnum order);

	/**
	 * 获取数据条数
	 * 
	 * @param criteriaConditions
	 * @return
	 */
	public Long totalCount(List<CriteriaCondition> criteriaConditions);

	/**
	 * 统计字段sum
	 * 
	 * @param property
	 * @param criteriaConditions
	 * @return
	 */
	public Object sumField(String property, List<CriteriaCondition> criteriaConditions);

	/**
	 * 统计多字段的对应sum
	 * 
	 * @param properties
	 * @param criteriaConditions
	 * @return
	 */
	public Object[] sumField(String[] properties, List<CriteriaCondition> criteriaConditions);

	/**
	 * 获取排序
	 * 
	 * @return
	 */
	public Integer getSortCode();

}