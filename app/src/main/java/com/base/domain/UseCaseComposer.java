/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.domain;


import io.reactivex.ObservableTransformer;

public interface UseCaseComposer {

    <T> ObservableTransformer<T, T> apply();

}
