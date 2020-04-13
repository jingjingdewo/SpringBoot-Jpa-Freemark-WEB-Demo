package com.demo.framework.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class EhcacheHandler {

	public static final String CACHE = "baseCache";

	public static final String CACHE_CMS = "cmsCache";

	public static final String CACHE_PERM = "permissonCache";

	@Autowired
	protected CacheManager cacheManager;

	protected Cache getCache(String name) {
		return cacheManager.getCache(name);
	}

	protected Cache getCache() {
		return getCache(CACHE);
	}

	protected Element getCacheElement(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		if (cache == null) {
			return null;
		} else {
			return cache.get(key);
		}
	}

	protected Element getCacheElement(String key) {
		return getCacheElement(CACHE, key);
	}

	protected Object getCacheData(String cacheName, String key) {
		Element element = getCacheElement(cacheName, key);
		if (element == null) {
			return null;
		} else {
			return element.getObjectValue();
		}
	}

	protected Object getCacheData(String key) {
		return getCacheData(CACHE, key);
	}

	@SuppressWarnings("unchecked")
	protected Object getSubCacheData(String cacheName, String key, String subKey) {
		Map<String, Object> data = (Map<String, Object>) getCacheData(cacheName, key);
		if (data != null) {
			return data.get(subKey);
		}
		return null;
	}

	protected Object getSubCacheData(String key, String subKey) {
		return getSubCacheData(CACHE, key, subKey);
	}

	protected Object getSubCacheData(String cacheName, EhcacheKey key, String subKey) {
		return getSubCacheData(cacheName, key.toString(), subKey);
	}

	protected Object getSubCacheData(EhcacheKey key, String subKey) {
		return getSubCacheData(key.toString(), subKey);
	}

	protected void setCacheElement(String cacheName, Element element) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cache.put(element);
		}
	}

	protected void setCacheElement(Element element) {
		setCacheElement(CACHE, element);
	}

	protected void setSubCacheData(String key, Object val) {
		setCacheElement(CACHE, key, val);
	}

	protected void setCacheElement(String cacheName, String key, Object val) {
		Element element = new Element(key, val);
		setCacheElement(cacheName, element);
	}

	protected void setSubCacheData(EhcacheKey key, String subKey, Object val) {
		setSubCacheData(key.toString(), subKey, val);
	}

	protected void setSubCacheData(String key, String subKey, Object val) {
		setSubCacheData(CACHE, key, subKey, val);
	}

	@SuppressWarnings("unchecked")
	protected void setSubCacheData(String cacheName, String key, String subKey, Object val) {
		Map<String, Object> data = (Map<String, Object>) getCacheData(cacheName, key);
		if (data == null) {
			data = new HashMap<>();
		}
		data.put(subKey, val);
		setCacheElement(cacheName, new Element(key, data));
	}

	protected void setSubCacheData(String cacheName, EhcacheKey key, String subKey, Object val) {
		setSubCacheData(cacheName, key.toString(), subKey, val);
	}

	protected void removeCacheElement(String key) {
		removeCacheElement(CACHE, key);
	}

	protected void removeCacheElement(EhcacheKey key) {
		removeCacheElement(CACHE, key.toString());
	}

	protected void removeCacheElement(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cache.remove(key);
		}
	}

	protected void removeCacheElement(String cacheName, EhcacheKey key) {
		removeCacheElement(cacheName, key.toString());
	}

}
