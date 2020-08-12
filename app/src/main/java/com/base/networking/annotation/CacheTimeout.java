/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.networking.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CacheTimeout {

    public static final long NEVER = 0L;

    long value() default NEVER;
}
