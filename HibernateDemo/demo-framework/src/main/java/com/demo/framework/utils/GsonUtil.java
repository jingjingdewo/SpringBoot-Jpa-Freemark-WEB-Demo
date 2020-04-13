package com.demo.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonUtil {

	private static Gson gson = new Gson();

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

	public static String toJsonExpose(Object object, boolean expose) {
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();
		return gson.toJson(object);
	}

	public static String toJsonWithFilter(Object object) {
		List<TargetStrategy> tStrategies = getIncludeFields(object.getClass());
		return toJson(object, tStrategies, true);
	}

	public static String toJson(Object object, String dateFormat) {
		GsonBuilder builder = new GsonBuilder().setDateFormat(dateFormat);
		return builder.create().toJson(object);
	}

	public static String toJson(Object object, boolean proxy) {
		GsonBuilder builder = new GsonBuilder();
		if (proxy) {
			builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		}
		return builder.create().toJson(object);
	}

	public static String toJson(Object object, TargetStrategy targetStrategy, boolean proxy) {
		GsonBuilder builder = new GsonBuilder();
		builder.addSerializationExclusionStrategy(targetStrategy);
		if (proxy) {
			builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		}
		return builder.create().toJson(object);
	}

	public static String toJson(Object object, List<TargetStrategy> tStrategies, boolean proxy) {
		GsonBuilder builder = new GsonBuilder();
		for (TargetStrategy ts : tStrategies) {
			builder.addSerializationExclusionStrategy(ts);
		}
		if (proxy) {
			builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		}
		return builder.create().toJson(object);
	}

	public static <T> T getEntity(String json, Class<T> clazz) {
		if (StringUtils.isNotBlank(json)) {
			return gson.fromJson(json, clazz);
		} else {
			return null;
		}
	}

	public static <T> T getEntityExpose(String json, Class<T> clazz) {
		if (StringUtils.isNotBlank(json)) {
			GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			Gson gson = builder.create();
			return gson.fromJson(json, clazz);
		} else {
			return null;
		}
	}

	public static <T> List<T> getEntites(String json, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		if (StringUtils.isNotBlank(json)) {
			try {
				List<JsonObject> temp = gson.fromJson(json, new TypeToken<List<JsonObject>>() {
				}.getType());
				for (JsonObject jo : temp) {
					list.add(gson.fromJson(jo, clazz));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static Map<String, Object> getKeyMap(String json) {
		Map<String, Object> list = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(json)) {
			try {
				list = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
				}.getType());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<Map<String, Object>> getListKeyMaps(String json) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		if (StringUtils.isNotBlank(json)) {
			try {
				map = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {
				}.getType());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static List<TargetStrategy> getIncludeFields(Class<?> bean) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		TargetStrategy ts = new TargetStrategy(bean);
		for (Field field : fields) {
			if (field.getAnnotation(OneToMany.class) == null && field.getAnnotation(ManyToMany.class) == null
					&& field.getAnnotation(ManyToOne.class) == null && !field.getName().equals("serialVersionUID")) {
				list.add(field.getName());
				if (field.getAnnotation(OneToOne.class) != null) {
					List<TargetStrategy> _tStrategies = getIncludeFields(field.getType());
					if (_tStrategies != null && _tStrategies.size() > 0) {
						tStrategies.addAll(_tStrategies);
					}
				}
			}
		}
		ts.setFields(list.toArray(new String[0]));
		ts.setReverse(true);
		tStrategies.add(ts);
		return tStrategies;
	}

	public static List<TargetStrategy> getIncludeFieldsHasManyToOne(Class<?> bean) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		TargetStrategy ts = new TargetStrategy(bean);
		for (Field field : fields) {
			if (field.getAnnotation(OneToMany.class) == null && field.getAnnotation(ManyToMany.class) == null
					&& !field.getName().equals("serialVersionUID")) {
				list.add(field.getName());
				if (field.getAnnotation(OneToOne.class) != null || field.getAnnotation(ManyToOne.class) != null) {
					List<TargetStrategy> _tStrategies = getIncludeFieldsHasManyToOne(field.getType());
					if (_tStrategies != null && _tStrategies.size() > 0) {
						tStrategies.addAll(_tStrategies);
					}
				}
			}
		}
		ts.setFields(list.toArray(new String[0]));
		ts.setReverse(true);
		tStrategies.add(ts);
		return tStrategies;
	}

	public static List<TargetStrategy> getIncludeFieldsNoOneToOne(Class<?> bean) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		TargetStrategy ts = new TargetStrategy(bean);
		for (Field field : fields) {
			if (field.getAnnotation(OneToMany.class) == null && field.getAnnotation(ManyToMany.class) == null
					&& field.getAnnotation(ManyToOne.class) == null && field.getAnnotation(OneToOne.class) == null
					&& !field.getName().equals("serialVersionUID")) {
				list.add(field.getName());
			}
		}
		ts.setFields(list.toArray(new String[0]));
		ts.setReverse(true);
		tStrategies.add(ts);
		return tStrategies;
	}

	public static List<TargetStrategy> getIncludeFieldsNoOneToOne(Class<?> bean, List<String> exClass) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		TargetStrategy ts = new TargetStrategy(bean);
		for (Field field : fields) {
			if (field.getAnnotation(OneToMany.class) == null && field.getAnnotation(ManyToMany.class) == null
					&& field.getAnnotation(ManyToOne.class) == null && field.getAnnotation(OneToOne.class) == null
					&& !field.getName().equals("serialVersionUID") && !exClass.contains(field.getType().getName())) {
				list.add(field.getName());
			}
		}
		ts.setFields(list.toArray(new String[0]));
		ts.setReverse(true);
		tStrategies.add(ts);
		return tStrategies;
	}

	public static List<TargetStrategy> getIncludeFields(Class<?> bean, int deepLevel) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		if (deepLevel > 0) {
			Field[] fields = bean.getDeclaredFields();
			List<String> list = new ArrayList<String>();
			List<String> _Class = new ArrayList<String>();
			_Class.add(bean.getName());
			for (Field field : fields) {
				if (field.getAnnotation(OneToMany.class) == null && field.getAnnotation(ManyToMany.class) == null
						&& field.getAnnotation(ManyToOne.class) == null && !field.getName().equals("serialVersionUID")
						&& !_Class.contains(field.getType().getName())) {
					list.add(field.getName());
					if (field.getAnnotation(OneToOne.class) != null) {
						_Class.add(field.getType().getName());
						List<TargetStrategy> _tStrategies = getIncludeFields(field.getType(), deepLevel - 1, _Class);
						if (_tStrategies != null && _tStrategies.size() > 0) {
							tStrategies.addAll(_tStrategies);
						}
					}
				}
			}
			TargetStrategy ts = new TargetStrategy(bean);
			ts.setFields(list.toArray(new String[0]));
			ts.setReverse(true);
			tStrategies.add(ts);
		}
		return tStrategies;
	}

	public static List<TargetStrategy> getIncludeFields(Class<?> bean, int deepLevel, List<String> exClass) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		if (deepLevel > 0) {
			Field[] fields = bean.getDeclaredFields();
			List<String> list = new ArrayList<String>();
			for (Field field : fields) {
				if (field.getAnnotation(OneToMany.class) == null && field.getAnnotation(ManyToMany.class) == null
						&& field.getAnnotation(ManyToOne.class) == null && !field.getName().equals("serialVersionUID")
						&& !exClass.contains(field.getType().getName())) {
					list.add(field.getName());
					if (field.getAnnotation(OneToOne.class) != null) {
						exClass.add(field.getType().getName());
						List<TargetStrategy> _tStrategies = getIncludeFields(field.getType(), deepLevel - 1);
						if (_tStrategies != null && _tStrategies.size() > 0) {
							tStrategies.addAll(_tStrategies);
						}
					}
				}
			}
			TargetStrategy ts = new TargetStrategy(bean);
			ts.setFields(list.toArray(new String[0]));
			ts.setReverse(true);
			tStrategies.add(ts);
		}
		return tStrategies;
	}

	public static List<TargetStrategy> getIncludeFields(Class<?> bean, Boolean deep) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		List<String> _Class = new ArrayList<String>();
		_Class.add(bean.getName());
		for (Field field : fields) {
			if (field.getAnnotation(OneToMany.class) == null && field.getAnnotation(ManyToMany.class) == null
					&& !field.getName().equals("serialVersionUID")) {
				list.add(field.getName());
				if (field.getAnnotation(OneToOne.class) != null && !_Class.contains(field.getType().getName())) {
					_Class.add(field.getType().getName());
					List<TargetStrategy> _tStrategies = deep ? getIncludeFields(field.getType())
							: getIncludeFieldsNoOneToOne(field.getType(), _Class);
					if (_tStrategies != null && _tStrategies.size() > 0) {
						tStrategies.addAll(_tStrategies);
					}
				}
			}
		}
		TargetStrategy ts = new TargetStrategy(bean);
		ts.setFields(list.toArray(new String[0]));
		ts.setReverse(true);
		tStrategies.add(ts);
		return tStrategies;
	}

	public static List<TargetStrategy> getExcludeFields(Class<?> bean) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		TargetStrategy ts = new TargetStrategy(bean);
		for (Field field : fields) {
			if (field.getAnnotation(OneToOne.class) != null || field.getAnnotation(OneToMany.class) != null
					|| field.getAnnotation(ManyToMany.class) != null || field.getAnnotation(ManyToOne.class) != null
					|| field.getName().equals("serialVersionUID")) {
				list.add(field.getName());
			}
		}
		ts.setFields(list.toArray(new String[0]));
		tStrategies.add(ts);
		if (bean.getSuperclass() != null) {
			fields = bean.getSuperclass().getDeclaredFields();
			list.clear();
			TargetStrategy sts = new TargetStrategy(bean.getSuperclass());
			for (Field field : fields) {
				if (field.getAnnotation(OneToOne.class) != null || field.getAnnotation(OneToMany.class) != null
						|| field.getAnnotation(ManyToMany.class) != null || field.getAnnotation(ManyToOne.class) != null

						|| field.getName().equals("serialVersionUID")) {
					list.add(field.getName());
				}
			}
			sts.setFields(list.toArray(new String[0]));
			tStrategies.add(sts);
		}
		return tStrategies;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<TargetStrategy> getExcludeFields(Class<?> bean, Class... exClasses) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		TargetStrategy ts = new TargetStrategy(bean);
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID")) {
				list.add(field.getName());
			} else {
				for (Class _ExClass : exClasses) {
					if (field.getAnnotation(_ExClass) != null) {
						list.add(field.getName());
						break;
					}
				}
			}
		}
		ts.setFields(list.toArray(new String[0]));
		tStrategies.add(ts);
		if (bean.getSuperclass() != null) {
			fields = bean.getSuperclass().getDeclaredFields();
			list.clear();
			TargetStrategy sts = new TargetStrategy(bean.getSuperclass());
			for (Field field : fields) {
				if (field.getName().equals("serialVersionUID")) {
					list.add(field.getName());
				} else {
					for (Class _ExClass : exClasses) {
						if (field.getAnnotation(_ExClass) != null) {
							list.add(field.getName());
							break;
						}
					}
				}
			}
			sts.setFields(list.toArray(new String[0]));
			tStrategies.add(sts);
		}
		return tStrategies;
	}

	public static List<TargetStrategy> getExcludeFieldsNoIncludeOneToOne(Class<?> bean) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		TargetStrategy ts = new TargetStrategy(bean);
		List<String> _Class = new ArrayList<String>();
		for (Field field : fields) {
			if (field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null
					|| field.getAnnotation(ManyToOne.class) != null || field.getName().equals("serialVersionUID")) {
				list.add(field.getName());
			}
			if (field.getAnnotation(OneToOne.class) != null && !_Class.contains(field.getType().getName())) {
				_Class.add(field.getType().getName());
				tStrategies.addAll(getIncludeFieldsNoOneToOne(field.getType()));
			}
		}
		ts.setFields(list.toArray(new String[0]));
		tStrategies.add(ts);
		if (bean.getSuperclass() != null) {
			fields = bean.getSuperclass().getDeclaredFields();
			list.clear();
			TargetStrategy sts = new TargetStrategy(bean.getSuperclass());
			for (Field field : fields) {
				if (field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null
						|| field.getAnnotation(ManyToOne.class) != null

						|| field.getName().equals("serialVersionUID")) {
					list.add(field.getName());
				}
				if (field.getAnnotation(OneToOne.class) != null && !_Class.contains(field.getType().getName())) {
					_Class.add(field.getType().getName());
					tStrategies.addAll(getIncludeFieldsNoOneToOne(field.getType()));
				}
			}
			sts.setFields(list.toArray(new String[0]));
			tStrategies.add(sts);
		}
		return tStrategies;
	}

	public static List<TargetStrategy> getExcludeFields(Class<?> bean, String... excfields) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		TargetStrategy ts = new TargetStrategy(bean);
		ts.setFields(excfields);
		tStrategies.add(ts);
		return tStrategies;
	}

	public static List<TargetStrategy> getIncludeFields(Class<?> bean, String... incfields) {
		List<TargetStrategy> tStrategies = new ArrayList<TargetStrategy>();
		TargetStrategy ts = new TargetStrategy(bean);
		Field[] fields = bean.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		for (Field field : fields) {
			boolean f = true;
			for (String incfield : incfields) {
				if (incfield.equals(field.getName())) {
					f = false;
				}
			}
			if (f) {
				list.add(field.getName());
			}
		}
		ts.setFields(list.toArray(new String[0]));
		ts.setReverse(true);
		tStrategies.add(ts);
		return tStrategies;
	}
}
