/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.networking.cache;



import com.base.networking.annotation.CachePolicy;
import com.base.networking.annotation.CacheTimeout;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Request;

public class ResponseCache {

    private final Map<String, CacheEntry> cache = new HashMap<>();

    public static ResponseCache create() {
        return new ResponseCache();
    }

    private ResponseCache() {
    }

    public Observable<?> cache(final Request request, final ObservableFactory<?> observableFactory) {
        return cache(request, observableFactory, CachePolicy.RETURN_CACHED_AND_QUERY);
    }

    public Observable<?> cache(final Request request, final ObservableFactory<?> observableFactory, final CachePolicy policy) {
        return cache(request, observableFactory, policy, CacheTimeout.NEVER);
    }

    public Observable<?> cache(final Request request, final ObservableFactory<?> observableFactory, final CachePolicy policy, final long timeout) {
        Object cached = getCachedValue(request, timeout);
        if (cached != null && policy != null && policy != CachePolicy.NO_CACHING) {
            if (policy == CachePolicy.RETURN_CACHED) {
                return returnCachedObservable(cached);
            } else if (policy == CachePolicy.RETURN_CACHED_AND_QUERY) {
                return Observable.merge(
                        returnCachedObservable(cached),
                        cacheResult(request, observableFactory.create())
                );
            }
        }
        return cacheResult(request, observableFactory.create());
    }

    private String getKey(final Request request) {
        return request.url().toString();
    }

    private Observable<?> returnCachedObservable(final Object cached) {
        return Observable.defer(() -> Observable.just(cached));
    }

    private Observable<?> cacheResult(final Request request, final Observable<?> observable) {
        return observable.doOnNext(value -> cache.put(getKey(request), new CacheEntry(value)));
    }

    private Object getCachedValue(final Request request, final long timeout) {
        CacheEntry entry = cache.get(getKey(request));
        if (entry != null) {
            if (timeout == CacheTimeout.NEVER) {
                return entry.value;
            } else {
                long elapsed = System.currentTimeMillis() - entry.timestamp;
                if (elapsed <= timeout) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public static interface ObservableFactory<T> {
        Observable<T> create();
    }

    private static class CacheEntry {
        Object value;
        long timestamp;

        public CacheEntry(Object value) {
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
