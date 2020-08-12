/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.domain;


import io.reactivex.Observable;

public abstract class VoidUseCase<R> extends UseCase<Void, R> {

    protected VoidUseCase(final UseCaseComposer useCaseComposer) {
        super(useCaseComposer);
    }

    protected abstract Observable<R> createUseCaseObservable();

    @Override
    protected final Observable<R> createUseCaseObservable(final Void param) {
        return createUseCaseObservable();
    }

    public final Observable<R> execute() {
        return execute(null);
    }

}
