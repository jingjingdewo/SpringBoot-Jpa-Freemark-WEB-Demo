package com.demo.framework.dao;

import com.demo.framework.dao.entity.*;
import com.demo.framework.dao.enums.CriteriaEnum;
import com.demo.framework.dao.enums.OrderEnum;

import java.util.List;
import java.util.Map;

/**
 * @Author 静畏人心
 * @Date 2020/4/8 22:41
 * @Description 基础数据库操作类
 */
@SuppressWarnings("rawtypes")
public interface BaseDao<T> {

	/**
	 * 将瞬态（自由态）的实体加入EntityManage，变成持久化对象
	 *
	 * @param o
	 *
	 */
	public void save(T o);

	/**
	 * 将瞬态（自由态）的实体加入EntityManage，变成持久化对象
	 *
	 * @param o
	 * @param flush 是否同步数据库
	 *
	 */
	public void save(T o, boolean flush);

	/**
	 * 将持久化的对象变成游离态（托管状态）
	 *
	 * @param o
	 *
	 */
	public void delete(T o);

	/**
	 * 将持久化的对象变成游离态（托管状态）
	 *
	 * @param o
	 * @param flush 是否同步数据库
	 *
	 */
	public void delete(T o, boolean flush);

	/**
	 * 将持久化的对象变成游离态（托管状态）
	 *
	 * @param clazz 对象
	 * @param id    对象ID
	 *
	 */
	public void delete(Class<T> clazz, Object id);

	/**
	 * 将持久化的对象变成游离态（托管状态）
	 *
	 * @param clazz 对象
	 * @param flush 是否同步数据库
	 * @param id    对象ID
	 *
	 */
	public void delete(Class<T> clazz, Object id, boolean flush);

	/**
	 * 将持久化的对象变成游离态（托管状态）,并直接数据库删除
	 *
	 * @param clazz 对象
	 * @param ids 对象ID组
	 *
	 */
	public void deleteByIds(Class<T> clazz, List<Object> ids);

	/**
	 * 将持久化的对象变成游离态（托管状态）,并直接数据库删除
	 *
	 * @param objects 对象
	 *
	 */
	public void deleteByObjects(List<T> objects);

	/**
	 * 将我们提供的对象变成一个持久化对象，对象会纳入EnitiyManage
	 *
	 * @param o
	 *
	 */
	public void saveOrUpdate(T o);

	/**
	 * 将我们提供的对象变成一个持久化对象，对象会纳入EnitiyManage 返回该对象
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
	 *
	 */
	public void saveOrUpdate(T o, boolean flush);

	/**
	 * 将我们提供的对象组变成一个持久化对象，对象会纳入EnitiyManage,并写入数据库
	 *
	 * @param os
	 *
	 */
	public void saveOrUpdate(List<T> os);

	public List<T> updateOrSave(List<T> os);

	/**
	 * 获得一个单个对象
	 *
	 * @param clazz 对象
	 * @param id 对象ID
	 * @return Object
	 */
	public T find(Class<T> clazz, Object id);

	public List<T> find(Class<T> clazz, Object[] ids, String pk);

	/**
	 * 查询
	 *
	 * @param hql
	 * @return List<T>或者List<Object[]>
	 */
	public List find(String hql, Object... param);

	/**
	 * 查询集合(带分页)
	 *
	 * @param hql
	 * @param param 参数数组
	 * @param page  查询第几页
	 * @param rows  每页显示几条记录
	 * @return List
	 */
	public List find(String hql, Integer page, Integer rows, Object... param);

	/**
	 * select count(*) from 类
	 *
	 * @param whereHql
	 * @param param    参数数组
	 * @return Long 记录数
	 */
	public Long count(String whereHql, Object... param);

	/**
	 * 执行HQL语句insert，update，delete等
	 *
	 * @param hql
	 * @param param <String,Object>param 参数数组
	 * @return Integer 响应数目
	 */
	public Integer executeHql(String hql, Object... param);

	/**
	 * 执行HQL语句,select
	 *
	 * @param hql
	 * @param param 参数数组
	 * @return Object 返回唯一值
	 */
	public Object findHqlRetUnique(String hql, Object... param);

	/**
	 * SQL查询语句
	 *
	 * @param sql
	 * @return List<Map<String,Object>> 返回Map
	 *
	 */
	public List findSQL(String sql, Class<T> clazz);

	/**
	 * SQL查询语句
	 *
	 * @param sql
	 * @return List<Object[]>
	 *
	 */
	public List findSQL(String sql);

	/**
	 * SQL查询语句，返回分页数据
	 *
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public List findSQL(String sql, Integer page, Integer rows);

	/**
	 * 执行SQL语句,select
	 *
	 * @param sql
	 * @param param 参数数组
	 * @return List<Object[]>
	 */
	public List findSQL(String sql, Object... param);

	public Object findSQLRetUnique(String sql, Object... param);

	/**
	 * SQL查询语句，返回分页数据
	 *
	 * @param sql
	 * @param page
	 * @param rows
	 * @param param
	 * @return
	 */
	public List findSQL(String sql, Integer page, Integer rows, Object... param);

	/**
	 * SQL查询语句
	 *
	 * @param sql
	 * @return
	 */
	public List findMapBySQL(String sql);

	/**
	 * 执行SQL语句,select
	 *
	 * @param sql
	 * @param param 参数数组
	 * @return List<Map>
	 */
	public List findMapBySQL(String sql, Object... param);

	/**
	 * SQL查询语句，返回分页数据
	 *
	 * @param sql
	 * @param page
	 * @param rows
	 * @param param
	 * @return
	 */
	public List findMapSQL(String sql, Integer page, Integer rows, Object... param);

	/**
	 * 执行SQL语句,insert，update，delete等
	 *
	 * @param sql
	 * @param param 参数数组
	 * @return Integer 响应数目
	 */
	public Integer executeSQL(String sql, Object... param);

	/**
	 * 获取类名
	 *
	 * @param clazz 对象
	 * @return String
	 */
	public String getEntityName(Class<T> clazz);

	/**
	 * 根据排序组拼接排序语句JPQL
	 *
	 * @param orderConditions 排序组
	 * @return String
	 */
	public String createOrderCondition(List<OrderCondition> orderConditions);

	/**
	 * 根据连接条件组拼接连接JPQL
	 *
	 * @param joins 连接条件组
	 * @return
	 */
	public String createJoinCondition(List<JoinCondition> joins);

	/**
	 * 根据条件组凭借条件JPQL
	 *
	 * @param criterias 条件组
	 * @return
	 */
	public String createWhereCondition(List<CriteriaCondition> criterias);

	/**
	 * @param clazz 对象
	 * @param criteriaUnit
	 * @return
	 */
	public Long totalCount(Class<T> clazz, CriteriaUnit criteriaUnit);

	/**
	 * @param clazz
	 * @param whereCondition
	 * @param params
	 * @return
	 */
	public Long totalCount(Class<T> clazz, String whereCondition, Map<String, Object> params);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public Page<T> findPageByType(Class<T> clazz, CriteriaUnit criteriaUnit, Page<T> page);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @param jqPage
	 * @return
	 */
	public JqPage<T> findPageByType(Class<T> clazz, CriteriaUnit criteriaUnit, JqPage<T> jqPage);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @param laPage
	 * @return
	 */
	public LaPage<T> findPageByType(Class<T> clazz, CriteriaUnit criteriaUnit, LaPage<T> laPage);

	/**
	 * @param clazz
	 * @param whereCondition
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public Page<T> findPageByType(Class<T> clazz, String whereCondition, Page<T> page, Map<String, Object> paramMap);

	/**
	 * @param clazz
	 * @param whereCondition
	 * @param jqPage
	 * @param paramMap
	 * @return
	 */
	public JqPage<T> findPageByType(Class<T> clazz, String whereCondition, JqPage<T> jqPage,
                                    Map<String, Object> paramMap);

	/**
	 * @param clazz
	 * @param whereCondition
	 * @param laPage
	 * @param paramMap
	 * @return
	 */
	public LaPage<T> findPageByType(Class<T> clazz, String whereCondition, LaPage<T> laPage,
                                    Map<String, Object> paramMap);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @param page
	 * @return
	 */
	public Page<T> findPageDistinctByType(Class<T> clazz, CriteriaUnit criteriaUnit, Page<T> page);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @param jqPage
	 * @return
	 */
	public JqPage<T> findPageDistinctByType(Class<T> clazz, CriteriaUnit criteriaUnit, JqPage<T> jqPage);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @param laPage
	 * @return
	 */
	public LaPage<T> findPageDistinctByType(Class<T> clazz, CriteriaUnit criteriaUnit, LaPage<T> laPage);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @return
	 */
	public List<T> findListByType(Class<T> clazz, CriteriaUnit criteriaUnit);

	/**
	 * @param clazz
	 * @param criteriaConditions
	 * @param orderConditions
	 * @return
	 */
	public List<T> findListByType(Class<T> clazz, List<CriteriaCondition> criteriaConditions,
                                  List<OrderCondition> orderConditions);

	/**
	 * @param clazz
	 * @param criteriaConditions
	 * @param orderConditions
	 * @param max
	 * @return
	 */
	public List<T> findListByType(Class<T> clazz, List<CriteriaCondition> criteriaConditions,
                                  List<OrderCondition> orderConditions, int max);

	/**
	 * @param clazz
	 * @param criteriaUnit
	 * @param max
	 * @return
	 */
	public List<T> findListByType(Class<T> clazz, CriteriaUnit criteriaUnit, int max);

	/**
	 * @param clazz
	 * @param whereCondition
	 * @param paramMap
	 * @return
	 */
	public List<T> findListByType(Class<T> clazz, String whereCondition, Map<String, Object> paramMap);

	/**
	 * @param clazz
	 * @param jpql
	 * @param paramMap
	 * @return
	 */
	public List<T> findListByTypeAndJpql(Class<T> clazz, String jpql, Map<String, Object> paramMap);

	/**
	 * @param clazz
	 * @param property
	 * @param type
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value);

	public T findFirstByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value);

	/**
	 * @param clazz
	 * @param property
	 * @param type
	 * @param value
	 * @param orderProperty
	 * @param order
	 * @return
	 */
	public List<T> findByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value,
                                  String orderProperty, OrderEnum order);

	public T findFirstByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value, String orderProperty,
                                 OrderEnum order);

	/**
	 * @param clazz
	 * @return
	 */
	public List<T> findAll(Class<T> clazz);

	/**
	 * @param clazz
	 * @param property
	 * @param unit
	 * @return
	 */
	public Object sumField(Class<T> clazz, String property, CriteriaUnit unit);

	/**
	 * @param clazz
	 * @param properties
	 * @param unit
	 * @return
	 */
	public Object[] sumField(Class<T> clazz, String[] properties, CriteriaUnit unit);

}
