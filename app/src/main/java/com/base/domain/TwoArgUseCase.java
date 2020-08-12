/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.domain;


import java.util.Map;

import io.reactivex.Observable;

public abstract class TwoArgUseCase<A,B,R> extends UseCase<TwoArgUseCase.Pair<A,B>,R> {

    public TwoArgUseCase(final UseCaseComposer composer) {
        super(composer);
    }

    protected abstract Observable<R> createUseCaseObservable(A first, B second);

    public Observable<R> execute(final A first, final B second) {
        return super.execute(new Pair<>(first, second));
    }

    public boolean isRunningForParam(final A param) {
        if (param == null) {
            return false;
        }
        for (Pair<A,B> key : observablesMap.keySet()) {
            if (key.first.equals(param)) {
                return true;
            }
        }
        return false;
    }

    public B getSecondParam(final A firstParam) {
        if (firstParam == null) {
            return null;
        }
        for (Map.Entry<Pair<A,B>, Observable<R>> entry : observablesMap.entrySet()) {
            if (entry.getKey().first.equals(firstParam)) {
                return entry.getKey().second;
            }
        }
        return null;
    }


    @Override
    protected final Observable<R> createUseCaseObservable(final Pair<A,B> param) {
        return createUseCaseObservable(param.first, param.second);
    }

    protected static class Pair<F,S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }
    }
}
