package com.demo.framework.dao.impl;


import com.demo.framework.dao.BaseDao;
import com.demo.framework.dao.BaseEntityManagerFactory;
import com.demo.framework.dao.entity.*;
import com.demo.framework.dao.enums.CriteriaEnum;
import com.demo.framework.dao.enums.OrderEnum;
import com.demo.framework.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.*;

/**
 * @Author 静畏人心
 * @Date 2020/4/10 0:13
 * @Description 基础数据库操作类
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class BaseDaoImpl<T> extends BaseEntityManagerFactory implements BaseDao<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(T o) {
		getEm().persist(o);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(T o, boolean flush) {
		getEm().persist(o);
		if (flush)
			getEm().flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(T o) {
		getEm().remove(o);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(T o, boolean flush) {
		getEm().remove(o);
		if (flush)
			getEm().flush();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Class<T> clazz, Object id) {
		getEm().remove(getEm().getReference(clazz, id));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Class<T> clazz, Object id, boolean flush) {
		getEm().remove(getEm().getReference(clazz, id));
		if (flush)
			getEm().flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByIds(Class<T> clazz, List<Object> ids) {
		int i = 0;
		for (Object id : ids) {
			getEm().remove(getEm().getReference(clazz, id));
			i++;
			if (i % 20 == 0) {
				getEm().flush();
			}
		}
		getEm().flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteByObjects(List<T> objects) {
		int i = 0;
		for (Object object : objects) {
			getEm().remove(object);
			i++;
			if (i % 20 == 0) {
				getEm().flush();
			}
		}
		getEm().flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(T o) {
		getEm().merge(o);
	}

	// 返回存入的对象
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public T updateOrSave(T o) {
		return getEm().merge(o);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(T o, boolean flush) {
		getEm().merge(o);
		if (flush)
			getEm().flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdate(List<T> os) {
		boolean lastFlush = true;
		int max = os.size();
		for (int i = 0; i < max; i++) {
			getEm().merge(os.get(i));
			if (i % 20 == 0) {
				getEm().flush();
				lastFlush = false;
			} else {
				lastFlush = true;
			}
		}
		if (lastFlush)
			getEm().flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<T> updateOrSave(List<T> os) {
		List<T> ts = new ArrayList<>();
		boolean lastFlush = true;
		int max = os.size();
		for (int i = 0; i < max; i++) {
			ts.add(getEm().merge(os.get(i)));
			if (i % 20 == 0) {
				getEm().flush();
				lastFlush = false;
			} else {
				lastFlush = true;
			}
		}
		if (lastFlush)
			getEm().flush();
		return ts;
	}

	@Override
	public T find(Class<T> clazz, Object id) {
		return getEm().find(clazz, id);
	}

	@Override
	public List<T> find(Class<T> clazz, Object[] ids, String pk) {
		if (CommonUtil.notEmpty(ids)) {
			StringBuffer hql = new StringBuffer("SELECT E FROM　").append(getEntityName(clazz));
			hql.append(" E WHERE E.").append(pk).append(" IN (?1)");
			return (List<T>) find(hql.toString(), ids);
		}
		return Collections.emptyList();
	}

	@Override
	public List find(String hql, Object... param) {
		Query q = getEm().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
		}
		return q.getResultList();
	}

	@Override
	public List find(String hql, Integer page, Integer rows, Object... param) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = getEm().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
	}

	@Override
	public List findSQL(String sql, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = getEm().createNativeQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
	}

	@Override
	public List findSQL(String sql) {
		Query q = getEm().createNativeQuery(sql);
		return q.getResultList();
	}

	@Override
	public List findSQL(String sql, Class<T> clazz) {
		Query q = getEm().createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return q.getResultList();
	}

	@Override
	public Long count(String whereHql, Object... param) {
		Query q = getEm().createQuery("SELECT count(E) FROM " + whereHql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
		}
		return (Long) q.getSingleResult();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Integer executeHql(String hql, Object... param) {
		Query q = getEm().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Object findHqlRetUnique(String hql, Object... param) {
		Query q = getEm().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
		}
		if (q.getResultList().size() == 0) {
			return null;
		} else {
			return q.getSingleResult();
		}
	}

	@Override
	public List findSQL(String sql, Object... param) {
		Query q = getEm().createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.getResultList();
	}

	@Override
	public Object findSQLRetUnique(String sql, Object... param) {
		Query q = getEm().createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		if (q.getResultList().size() == 0) {
			return null;
		} else {
			return q.getSingleResult();
		}
	}

	@Override
	public List findSQL(String sql, Integer page, Integer rows, Object... param) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = getEm().createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Integer executeSQL(String sql, Object... param) {
		Query q = getEm().createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public String getEntityName(Class<T> clazz) {
		return clazz.getSimpleName();
	}

	@Override
	public String createOrderCondition(List<OrderCondition> orderConditions) {
		if (orderConditions == null || orderConditions.isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (OrderCondition oc : orderConditions) {
			result.append(addOrder(oc)).append(", ");
		}
		return " ORDER BY " + result.toString().substring(0, result.toString().lastIndexOf(","));
	}

	@Override
	public String createJoinCondition(List<JoinCondition> joins) {
		if (joins == null || joins.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (JoinCondition join : joins) {
			sb.append("LEFT JOIN E.").append(join.getFieldName()).append(" ").append(join.getAlias()).append(" ");
		}
		return sb.toString();
	}

	@Override
	public String createWhereCondition(List<CriteriaCondition> criterias) {
		if (criterias.isEmpty() || criterias == null) {
			return "";
		}
		StringBuilder result = new StringBuilder(" WHERE ");
		result.append(whereCondition(criterias));
		return result.toString();
	}

	private String whereCondition(List<CriteriaCondition> criterias) {
		if (criterias.isEmpty() || criterias == null) {
			return "";
		}
		StringBuilder result = new StringBuilder("(");
		boolean f = false;
		for (CriteriaCondition sc : criterias) {
			if (f) {
				result.append(StringUtils.isBlank(sc.getRelationType()) ? " AND " : " " + sc.getRelationType() + " ");
			}
			result.append(dealWithCriteria(sc));
			f = true;
			if (sc.getChildCriterias().size() > 0) {
				result.append(StringUtils.isBlank(sc.getChildRelationType()) ? " AND "
						: " " + sc.getChildRelationType() + " ");
				result.append(whereCondition(sc.getChildCriterias()));
			}
		}
		return result.append(")").toString();
	}

	private void setParameterToQuery(Query query, List<CriteriaCondition> criterias) {
		for (CriteriaCondition criteria : criterias) {
			setParameter(query, criteria);
			if (criteria.getChildCriterias().size() > 0) {
				setParameterToQuery(query, criteria.getChildCriterias());
			}
		}
	}

	private void setParameter(Query query, CriteriaCondition criteria) {
		switch (criteria.getCriteriaType()) {
		case Like:
			query.setParameter(criteria.getParamName(), "%" + criteria.getFieldValue1() + "%");
			break;
		case NotLike:
			query.setParameter(criteria.getParamName(), "%" + criteria.getFieldValue1() + "%");
			break;
		case LeftLike:
			query.setParameter(criteria.getParamName(), "%" + criteria.getFieldValue1());
			break;
		case LeftNotLike:
			query.setParameter(criteria.getParamName(), "%" + criteria.getFieldValue1());
			break;
		case RightLike:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1() + "%");
			break;
		case RightNotLike:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1() + "%");
			break;
		case Equal:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		case NotEqual:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		case Between:
			query.setParameter(criteria.getParamName() + "Begin", criteria.getFieldValue1());
			query.setParameter(criteria.getParamName() + "End", criteria.getFieldValue2());
			break;
		case LessEqual:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		case MoreEqual:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		case Less:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		case More:
			query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		case DateMore:
			query.setParameter(criteria.getParamName(), (Date) criteria.getFieldValue1(), TemporalType.TIMESTAMP);
			break;
		case DateLess:
			query.setParameter(criteria.getParamName(), (Date) criteria.getFieldValue1(), TemporalType.TIMESTAMP);
			break;
		case IN:
			// query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		case NOTIN:
			// query.setParameter(criteria.getParamName(), criteria.getFieldValue1());
			break;
		default:
			break;
		}
	}

	private String addOrder(OrderCondition oc) {
		StringBuilder sb = new StringBuilder();
		switch (oc.getOrderType()) {
		case asc:
			sb.append(wrapFieldName(oc.getFieldName()) + " ASC ");
			break;
		case desc:
			sb.append(wrapFieldName(oc.getFieldName()) + " DESC ");
			break;
		default:
			break;
		}
		return sb.toString();
	}

	private String dealWithCriteria(CriteriaCondition sc) {
		StringBuilder result = new StringBuilder();
		switch (sc.getCriteriaType()) {
		case Like:
			result.append(wrapFieldName(sc.getFieldName()) + " like :" + sc.getParamName());
			break;
		case NotLike:
			result.append(wrapFieldName(sc.getFieldName()) + " not like :" + sc.getParamName());
			break;
		case LeftLike:
			result.append(wrapFieldName(sc.getFieldName()) + " like :" + sc.getParamName());
			break;
		case LeftNotLike:
			result.append(wrapFieldName(sc.getFieldName()) + " not like :" + sc.getParamName());
			break;
		case RightLike:
			result.append(wrapFieldName(sc.getFieldName()) + " like :" + sc.getParamName());
			break;
		case RightNotLike:
			result.append(wrapFieldName(sc.getFieldName()) + " not like :" + sc.getParamName());
			break;
		case Equal:
			result.append(wrapFieldName(sc.getFieldName()) + " = :" + sc.getParamName());
			break;
		case NotEqual:
			result.append(wrapFieldName(sc.getFieldName()) + " <> :" + sc.getParamName());
			break;
		case Between:
			result.append(wrapFieldName(sc.getFieldName()) + " between :" + sc.getParamName() + "Begin and :"
					+ sc.getParamName() + "End");
			break;
		case IsNotNull:
			result.append(wrapFieldName(sc.getFieldName()) + " IS NOT NULL");
			break;
		case IsNull:
			result.append(wrapFieldName(sc.getFieldName()) + " IS NULL");
			break;
		case LessEqual:
			result.append(wrapFieldName(sc.getFieldName()) + " <= :" + sc.getParamName());
			break;
		case MoreEqual:
			result.append(wrapFieldName(sc.getFieldName()) + " >= :" + sc.getParamName());
			break;
		case Less:
			result.append(wrapFieldName(sc.getFieldName()) + " < :" + sc.getParamName());
			break;
		case More:
			result.append(wrapFieldName(sc.getFieldName()) + " > :" + sc.getParamName());
			break;
		case DateMore:
			result.append(wrapFieldName(sc.getFieldName()) + " > :" + sc.getParamName());
			break;
		case DateLess:
			result.append(wrapFieldName(sc.getFieldName()) + " < :" + sc.getParamName());
			break;
		case IN:
			result.append(wrapFieldName(sc.getFieldName()) + " in (");
			for (Object o : (List<Object>) sc.getFieldValue1()) {
				if (o != null) {
					boolean number = false;
					if (o instanceof Long || o instanceof Integer || o instanceof Float || o instanceof Double) {
						number = true;
					} else {
						number = false;
					}
					if (number) {
						result.append(o.toString());
					} else {
						result.append("'" + o.toString() + "'");
					}
					result.append(",");
				}
			}
			result.deleteCharAt(result.length() - 1);
			result.append(")");
			break;
		case NOTIN:
			result.append(wrapFieldName(sc.getFieldName()) + " not in (");
			for (Object o : (List<Object>) sc.getFieldValue1()) {
				boolean number = false;
				if (o instanceof Long || o instanceof Integer || o instanceof Float || o instanceof Double) {
					number = true;
				} else {
					number = false;
				}
				if (number) {
					result.append(o.toString());
				} else {
					result.append("'" + o.toString() + "'");
				}
				result.append(",");
			}
			result.deleteCharAt(result.length() - 1);
			result.append(")");
			break;
		default:
			break;
		}
		return result.toString();
	}

	private String wrapFieldName(String fieldName) {
		return MessageFormat.format("{0}.{1}", new Object[] { "E", fieldName });
	}

	protected void generatePaginator(Page<T> page) {
		page.getPaginator().clear();
		int totalPage = (int) Math.ceil(page.getTotalCount().doubleValue() / page.getPageSize().doubleValue());
		page.setTotalPage(totalPage);
		if (page.getPageNo() <= page.getTotalPage()) {
			if (page.getTotalPage() <= 5) {
				for (int i = 1; i <= page.getTotalPage(); i++) {
					page.getPaginator().add(i);
				}
			} else if (page.getPageNo() <= 2) {
				for (int i = 1; i <= 5; i++) {
					page.getPaginator().add(i);
				}
			} else if (page.getPageNo() > 2 && (page.getTotalPage() - page.getPageNo() + 1) > 3) {
				for (int i = page.getPageNo() - 1; i <= page.getPageNo() + 3; i++) {
					page.getPaginator().add(i);
				}
			} else {
				for (int i = page.getTotalPage() - 4; i <= page.getTotalPage(); i++) {
					page.getPaginator().add(i);
				}
			}
		}
	}

	@Override
	public Long totalCount(Class<T> clazz, CriteriaUnit unit) {
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(unit.getCriterias());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(E) FROM ").append(entityName).append(" E ")
				.append(createJoinCondition(unit.getJoinConditions()));
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, unit.getCriterias());
		return (Long) query.getSingleResult();
	}

	@Override
	public Long totalCount(Class<T> clazz, String whereCondition, Map<String, Object> params) {
		String entityName = getEntityName(clazz);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(E) FROM ").append(entityName).append(" E WHERE ");
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		Set<String> keySet = params.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, params.get(key));
		}
		return (Long) query.getSingleResult();
	}

	@Override
	public Page<T> findPageByType(Class<T> clazz, CriteriaUnit criteriaUnit, Page<T> page) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, criteriaUnit.getCriterias());
		query.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, criteriaUnit).intValue();
		page.setTotalCount(totalCount);
		page.setResults(resultList);
		generatePaginator(page);
		return page;
	}

	@Override
	public JqPage<T> findPageByType(Class<T> clazz, CriteriaUnit criteriaUnit, JqPage<T> jqPage) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, criteriaUnit.getCriterias());
		query.setFirstResult((jqPage.getPage() - 1) * jqPage.getPageSize()).setMaxResults(jqPage.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, criteriaUnit).intValue();
		jqPage.setRecords(totalCount);
		jqPage.setRows(resultList);
		jqPage.setTotal((int) Math.ceil(jqPage.getRecords().doubleValue() / jqPage.getPageSize().doubleValue()));
		return jqPage;
	}

	@Override
	public LaPage<T> findPageByType(Class<T> clazz, CriteriaUnit criteriaUnit, LaPage<T> laPage) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, criteriaUnit.getCriterias());
		query.setFirstResult((laPage.getPage() - 1) * laPage.getPageSize()).setMaxResults(laPage.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, criteriaUnit).intValue();
		laPage.setCount(totalCount);
		laPage.setData(resultList);
		return laPage;
	}

	@Override
	public Page<T> findPageDistinctByType(Class<T> clazz, CriteriaUnit criteriaUnit, Page<T> page) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT Distinct E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, criteriaUnit.getCriterias());
		query.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, criteriaUnit).intValue();
		page.setTotalCount(totalCount);
		page.setResults(resultList);
		generatePaginator(page);
		return page;
	}

	@Override
	public JqPage<T> findPageDistinctByType(Class<T> clazz, CriteriaUnit criteriaUnit, JqPage<T> jqPage) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT Distinct E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, criteriaUnit.getCriterias());
		query.setFirstResult((jqPage.getPage() - 1) * jqPage.getPageSize()).setMaxResults(jqPage.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, criteriaUnit).intValue();
		jqPage.setRecords(totalCount);
		jqPage.setRows(resultList);
		jqPage.setTotal((int) Math.ceil(jqPage.getRecords().doubleValue() / jqPage.getPageSize().doubleValue()));
		return jqPage;
	}

	@Override
	public LaPage<T> findPageDistinctByType(Class<T> clazz, CriteriaUnit criteriaUnit, LaPage<T> laPage) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT Distinct E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, criteriaUnit.getCriterias());
		query.setFirstResult((laPage.getPage() - 1) * laPage.getPageSize()).setMaxResults(laPage.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, criteriaUnit).intValue();
		laPage.setCount(totalCount);
		laPage.setData(resultList);
		return laPage;
	}

	@Override
	public Page<T> findPageByType(Class<T> clazz, String whereCondition, Page<T> page, Map<String, Object> paramMap) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E WHERE ");
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		Set<String> keySet = paramMap.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, paramMap.get(key));
		}
		resultList = query.setFirstResult(page.getPageNo()).setMaxResults(page.getPageSize()).getResultList();
		Integer totalCount = totalCount(clazz, whereCondition, paramMap).intValue();
		page.setTotalCount(totalCount);
		page.setResults(resultList);
		generatePaginator(page);
		return page;
	}

	@Override
	public JqPage<T> findPageByType(Class<T> clazz, String whereCondition, JqPage<T> jqPage,
			Map<String, Object> paramMap) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E WHERE ");
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		Set<String> keySet = paramMap.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, paramMap.get(key));
		}
		query.setFirstResult((jqPage.getPage() - 1) * jqPage.getPageSize()).setMaxResults(jqPage.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, whereCondition, paramMap).intValue();
		jqPage.setRecords(totalCount);
		jqPage.setRows(resultList);
		jqPage.setTotal((int) Math.ceil(jqPage.getRecords().doubleValue() / jqPage.getPageSize().doubleValue()));
		return jqPage;
	}

	@Override
	public LaPage<T> findPageByType(Class<T> clazz, String whereCondition, LaPage<T> laPage,
			Map<String, Object> paramMap) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E WHERE ");
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		Set<String> keySet = paramMap.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, paramMap.get(key));
		}
		query.setFirstResult((laPage.getPage() - 1) * laPage.getPageSize()).setMaxResults(laPage.getPageSize());
		resultList = query.getResultList();
		Integer totalCount = totalCount(clazz, whereCondition, paramMap).intValue();
		laPage.setCount(totalCount);
		laPage.setData(resultList);
		return laPage;
	}

	@Override
	public List<T> findListByType(Class<T> clazz, CriteriaUnit criteriaUnit) {
		List<T> resultList = new ArrayList<T>();
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, criteriaUnit.getCriterias());
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<T> findListByType(Class<T> clazz, List<CriteriaCondition> criteriaConditions,
			List<OrderCondition> orderConditions) {
		CriteriaUnit criteriaUnit = new CriteriaUnit();
		criteriaUnit.setCriterias(criteriaConditions);
		criteriaUnit.setOrderConditions(orderConditions);
		return findListByType(clazz, criteriaUnit);
	}

	@Override
	public List<T> findListByType(Class<T> clazz, List<CriteriaCondition> criteriaConditions,
			List<OrderCondition> orderConditions, int max) {
		CriteriaUnit criteriaUnit = new CriteriaUnit();
		criteriaUnit.setCriterias(criteriaConditions);
		criteriaUnit.setOrderConditions(orderConditions);
		return findListByType(clazz, criteriaUnit, max);
	}

	@Override
	public List<T> findListByType(Class<T> clazz, CriteriaUnit criteriaUnit, int max) {
		List<T> resultList = new ArrayList<T>();
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(criteriaUnit.getCriterias());
		String orderCondition = createOrderCondition(criteriaUnit.getOrderConditions());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E ");
		sb.append(createJoinCondition(criteriaUnit.getJoinConditions()));
		sb.append(whereCondition);
		sb.append(orderCondition);
		Query query = getEm().createQuery(sb.toString());
		query.setMaxResults(max);
		setParameterToQuery(query, criteriaUnit.getCriterias());
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<T> findListByType(Class<T> clazz, String whereCondition, Map<String, Object> paramMap) {
		List<T> resultList = null;
		String entityName = getEntityName(clazz);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT E FROM ").append(entityName).append(" E WHERE ");
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		for (String key : paramMap.keySet()) {
			query.setParameter(key, paramMap.get(key));
		}
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<T> findListByTypeAndJpql(Class<T> clazz, String jpql, Map<String, Object> paramMap) {
		List<T> resultList = null;
		TypedQuery<T> query = getEm().createQuery(jpql, clazz);
		Set<String> keySet = paramMap.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String key = it.next();
			query.setParameter(key, paramMap.get(key));
		}
		resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<T> findByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.getCriterias().add(CriteriaCondition.getCriteria(type, property, property.replace(".", ""), value));
		return findListByType(clazz, unit);
	}

	@Override
	public T findFirstByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.getCriterias().add(CriteriaCondition.getCriteria(type, property, property.replace(".", ""), value));
		List<T> models = findListByType(clazz, unit, 1);
		return models.isEmpty() ? null : models.get(0);
	}

	@Override
	public List<T> findByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value,
			String orderProperty, OrderEnum order) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.getCriterias().add(CriteriaCondition.getCriteria(type, property, property.replace(".", ""), value));
		unit.getOrderConditions().add(OrderCondition.addOrder(order, orderProperty));
		return findListByType(clazz, unit);
	}

	@Override
	public T findFirstByProperty(Class<T> clazz, String property, CriteriaEnum type, Object value, String orderProperty,
			OrderEnum order) {
		CriteriaUnit unit = new CriteriaUnit();
		unit.getCriterias().add(CriteriaCondition.getCriteria(type, property, property.replace(".", ""), value));
		unit.getOrderConditions().add(OrderCondition.addOrder(order, orderProperty));
		List<T> models = findListByType(clazz, unit, 1);
		return models.isEmpty() ? null : models.get(0);
	}

	@Override
	public List<T> findAll(Class<T> clazz) {
		try {
			Query query = getEm().createQuery("SELECT E FROM " + getEntityName(clazz) + " E");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object sumField(Class<T> clazz, String property, CriteriaUnit unit) {
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(unit.getCriterias());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT SUM(E.").append(property).append(") FROM ").append(entityName).append(" E ")
				.append(createJoinCondition(unit.getJoinConditions()));
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, unit.getCriterias());
		return query.getSingleResult();
	}

	@Override
	public Object[] sumField(Class<T> clazz, String[] properties, CriteriaUnit unit) {
		String entityName = getEntityName(clazz);
		String whereCondition = createWhereCondition(unit.getCriterias());
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		for (String property : properties) {
			sb.append(" SUM(E.").append(property).append("),");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" FROM ").append(entityName).append(" E ").append(createJoinCondition(unit.getJoinConditions()));
		sb.append(whereCondition);
		Query query = getEm().createQuery(sb.toString());
		setParameterToQuery(query, unit.getCriterias());
		List<Object> objects = query.getResultList();
		if (objects.size() > 0) {
			return (Object[]) objects.get(0);
		}
		return null;
	}

	@Override
	public List findMapBySQL(String sql) {
		Query q = getEm().createNativeQuery(sql);
		return q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List findMapBySQL(String sql, Object... param) {
		Query q = getEm().createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List findMapSQL(String sql, Integer page, Integer rows, Object... param) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = getEm().createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

}
