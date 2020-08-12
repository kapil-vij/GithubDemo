/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.networking.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    CachePolicy value() default CachePolicy.RETURN_CACHED_AND_QUERY;
}
