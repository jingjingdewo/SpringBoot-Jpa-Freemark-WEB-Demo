package com.demo.framework.service.impl;

import com.demo.framework.dao.BaseDao;
import com.demo.framework.dao.entity.*;
import com.demo.framework.dao.enums.CriteriaEnum;
import com.demo.framework.dao.enums.OrderEnum;
import com.demo.framework.ehcache.EhcacheHandler;
import com.demo.framework.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class BaseServiceImpl<T> extends EhcacheHandler implements BaseService<T> {

	@Autowired
	protected BaseDao<T> dao;

	private Class clazz = null;

	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(T o) {
		dao.save(o);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(T o, boolean flush) {
		dao.save(o, flush);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Object id) {
		dao.delete(clazz, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Object id, boolean flush) {
		dao.delete(clazz, id, flush);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByIds(List<Object> ids) {
		dao.deleteByIds(clazz, ids);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByObjects(List<T> objects) {
		dao.deleteByObjects(objects);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(T o) {
		dao.saveOrUpdate(o);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public T updateOrSave(T o) {
		return dao.updateOrSave(o);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(T o, boolean flush) {
		dao.saveOrUpdate(o, flush);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(List<T> os) {
		dao.saveOrUpdate(os);
	}

	@Override
	public T get(Object id) {
		return (T) dao.find(clazz, id);
	}

	@Override
	public List<T> get(Object[] ids, String pk) {
		return (List<T>) dao.find(clazz, ids, pk);
	}

	@Override
	public List<T> getByIds(Object[] ids) {
		return (List<T>) dao.find(clazz, ids, "id");
	}

	@Override
	public T getByUuid(String uuid) {
		List<T> models = getByProperty("uuid", CriteriaEnum.Equal, uuid);
		if (models.isEmpty()) {
			return null;
		} else {
			return models.get(0);
		}
	}

	@Override
	public List get(String hql, Object... param) {
		return dao.find(hql, param);
	}

	@Override
	public List get(String hql, Integer page, Integer rows, Object... param) {
		return dao.find(hql, page, rows, param);
	}

	@Override
	public Long count(String whereHql, Object... param) {
		return dao.count(whereHql, param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Integer executeJpql(String hql, Object... param) {
		return dao.executeHql(hql, param);
	}

	@Override
	public Object getJpqlRetUnique(String hql, Object... param) {
		return dao.findHqlRetUnique(hql, param);
	}

	@Override
	public List getSQL(String sql, Object... param) {
		return dao.findSQL(sql, param);
	}

	@Override
	public Object getSQLRetUnique(String sql, Object... param) {
		return dao.findSQLRetUnique(sql, param);
	}

	@Override
	public List getSQL(String sql, Integer page, Integer rows, Object... param) {
		return dao.findSQL(sql, page, rows, param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Integer executeSQL(String sql, Object... param) {
		return dao.executeSQL(sql, param);
	}

	@Override
	public Long totalCount(CriteriaUnit unit) {
		return dao.totalCount(clazz, unit);
	}

	@Override
	public Long totalCount(String whereCondition, Map<String, Object> params) {
		return dao.totalCount(clazz, whereCondition, params);
	}

	@Override
	public Page<T> getPageByType(CriteriaUnit criteriaUnit, Page<T> page) {
		return dao.findPageByType(clazz, criteriaUnit, page);
	}

	@Override
	public JqPage<T> getPageByType(CriteriaUnit criteriaUnit, JqPage<T> jqPage) {
		return dao.findPageByType(clazz, criteriaUnit, jqPage);
	}

	@Override
	public LaPage<T> getPageByType(CriteriaUnit criteriaUnit, LaPage<T> laPage) {
		return dao.findPageByType(clazz, criteriaUnit, laPage);
	}

	@Override
	public Page<T> getPageByType(String whereCondition, Page<T> page, Map<String, Object> paramMap) {
		return dao.findPageByType(clazz, whereCondition, page, paramMap);
	}

	@Override
	public JqPage<T> getPageByType(String whereCondition, JqPage<T> jqPage, Map<String, Object> paramMap) {
		return dao.findPageByType(clazz, whereCondition, jqPage, paramMap);
	}

	@Override
	public LaPage<T> getPageByType(String whereCondition, LaPage<T> laPage, Map<String, Object> paramMap) {
		return dao.findPageByType(clazz, whereCondition, laPage, paramMap);
	}

	@Override
	public Page<T> getPageDistinctByType(CriteriaUnit criteriaUnit, Page<T> page) {
		return dao.findPageDistinctByType(clazz, criteriaUnit, page);
	}

	@Override
	public JqPage<T> getPageDistinctByType(CriteriaUnit criteriaUnit, JqPage<T> jqPage) {
		return dao.findPageDistinctByType(clazz, criteriaUnit, jqPage);
	}

	@Override
	public LaPage<T> getPageDistinctByType(CriteriaUnit criteriaUnit, LaPage<T> laPage) {
		return dao.findPageDistinctByType(clazz, criteriaUnit, laPage);
	}

	@Override
	public List<T> getListByType(CriteriaUnit criteriaUnit) {
		return dao.findListByType(clazz, criteriaUnit);
	}

	@Override
	public List<T> getListByType(CriteriaUnit criteriaUnit, int max) {
		return dao.findListByType(clazz, criteriaUnit, max);
	}

	@Override
	public List<T> getListByWereCondition(String whereCondition, Map<String, Object> paramMap) {
		return dao.findListByType(clazz, whereCondition, paramMap);
	}

	@Override
	public List<T> getListByJpql(String jpql, Map<String, Object> paramMap) {
		return dao.findListByTypeAndJpql(clazz, jpql, paramMap);
	}

	@Override
	public List<T> getAll() {
		return dao.findAll(clazz);
	}

	@Override
	public Page<T> getPage(Page<T> page, List<CriteriaCondition> criteriaConditions,
			List<OrderCondition> orderConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		if (orderConditions == null || orderConditions.isEmpty()) {
			unit.getOrderConditions().add(OrderCondition.addOrder(OrderEnum.asc, "id"));
		} else {
			unit.setOrderConditions(orderConditions);
		}
		page = dao.findPageByType(clazz, unit, page);
		return page;
	}

	@Override
	public JqPage<T> getPage(JqPage<T> jqPage, List<CriteriaCondition> criteriaConditions,
			List<OrderCondition> orderConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		if (orderConditions == null || orderConditions.isEmpty()) {
			unit.getOrderConditions().add(OrderCondition.addOrder(OrderEnum.asc, "id"));
		} else {
			unit.setOrderConditions(orderConditions);
		}
		jqPage = dao.findPageByType(clazz, unit, jqPage);
		return jqPage;
	}

	@Override
	public LaPage<T> getPage(LaPage<T> laPage, List<CriteriaCondition> criteriaConditions,
			List<OrderCondition> orderConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		if (orderConditions == null || orderConditions.isEmpty()) {
			unit.getOrderConditions().add(OrderCondition.addOrder(OrderEnum.asc, "id"));
		} else {
			unit.setOrderConditions(orderConditions);
		}
		laPage = dao.findPageByType(clazz, unit, laPage);
		return laPage;
	}

	@Override
	public List<T> getList(List<CriteriaCondition> criteriaConditions, List<OrderCondition> orderConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		if (orderConditions == null || orderConditions.isEmpty()) {
			unit.getOrderConditions().add(OrderCondition.addOrder(OrderEnum.asc, "id"));
		} else {
			unit.setOrderConditions(orderConditions);
		}
		return dao.findListByType(clazz, unit);
	}

	@Override
	public List<T> getList(List<CriteriaCondition> criteriaConditions, List<OrderCondition> orderConditions, int max) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		if (orderConditions == null || orderConditions.isEmpty()) {
			unit.getOrderConditions().add(OrderCondition.addOrder(OrderEnum.asc, "id"));
		} else {
			unit.setOrderConditions(orderConditions);
		}
		return getListByType(unit, max);
	}

	@Override
	public T getFristModel(List<CriteriaCondition> criteriaConditions, List<OrderCondition> orderConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		if (orderConditions == null || orderConditions.isEmpty()) {
			unit.getOrderConditions().add(OrderCondition.addOrder(OrderEnum.asc, "id"));
		} else {
			unit.setOrderConditions(orderConditions);
		}
		List<T> models = dao.findListByType(clazz, unit, 1);
		if (models.isEmpty()) {
			return null;
		} else {
			return models.get(0);
		}
	}

	@Override
	public List<T> getByProperty(String property, CriteriaEnum type, Object value) {
		return dao.findByProperty(clazz, property, type, value);
	}

	@Override
	public List<T> getByProperty(String property, CriteriaEnum type, Object value, String orderProperty,
			OrderEnum order) {
		return dao.findByProperty(clazz, property, type, value, orderProperty, order);
	}

	@Override
	public Long totalCount(List<CriteriaCondition> criteriaConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		return dao.totalCount(clazz, unit);
	}

	@Override
	public Object sumField(String property, List<CriteriaCondition> criteriaConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		return dao.sumField(clazz, property, unit);
	}

	@Override
	public Object[] sumField(String[] properties, List<CriteriaCondition> criteriaConditions) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.setCriterias(criteriaConditions);
		return dao.sumField(clazz, properties, unit);
	}

	@Override
	public Integer getSortCode() {
		StringBuffer hql = new StringBuffer("SELECT MAX(E.sortCode) From ").append(clazz.getSimpleName()).append(" E");
		hql.append(" WHERE E.deleted = false ORDER BY E.sortCode DESC");
		Object ret = getJpqlRetUnique(hql.toString(), null);
		if (ret == null) {
			return 1;
		} else {
			return (Integer) ret + 1;
		}
	}

	@Override
	public List getMapBySQL(String sql, Object... param) {
		return dao.findMapBySQL(sql, param);
	}

	@Override
	public List getMapBySQL(String sql, Integer page, Integer rows, Object... param) {
		return dao.findMapSQL(sql, page, rows, param);
	}

}
