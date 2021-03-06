/*
 * Copyright 2013 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.async

import groovy.transform.CompileStatic
import org.grails.async.decorator.PromiseDecorator
import org.grails.async.factory.SynchronousPromiseFactory
import org.grails.async.factory.gpars.GparsPromiseFactory

/**
 * Factory class for working with {@link Promise} instances
 *
 * @author Graeme Rocher
 * @since 2.3
 */
@CompileStatic
class Promises {

    static PromiseFactory promiseFactory

    static {
        if (GparsPromiseFactory.isGparsAvailable()) {
            promiseFactory = new GparsPromiseFactory()
        }
        /*else if (ReactorPromiseFactory.isReactorAvailable()) {
            promiseFactory = new ReactorPromiseFactory()
        }*/
        else {
            promiseFactory = new SynchronousPromiseFactory()
        }
    }

    static PromiseFactory getPromiseFactory() {
        if (promiseFactory == null) {
            if (GparsPromiseFactory.isGparsAvailable()) {
                promiseFactory = new GparsPromiseFactory()
            }
            else {
                promiseFactory = new SynchronousPromiseFactory()
            }
        }
        return promiseFactory
    }

    static void setPromiseFactory(PromiseFactory promiseFactory) {
        Promises.@promiseFactory = promiseFactory
    }

    /**
     * @see PromiseFactory#waitAll(grails.async.Promise[])
     */
    static<T> List<T> waitAll(Promise<T>...promises) {
        getPromiseFactory().waitAll(promises)
    }

    /**
     * @see PromiseFactory#waitAll(java.util.List)
     */
    static<T> List<T> waitAll(List<Promise<T>> promises) {
        getPromiseFactory().waitAll(promises)
    }

    /**
     * @see PromiseFactory#onComplete(java.util.List, groovy.lang.Closure)
     */
    static<T> Promise<List<T>> onComplete(List<Promise<T>> promises, Closure callable ) {
        getPromiseFactory().onComplete(promises, callable)
    }
    /**
     * @see PromiseFactory#onError(java.util.List, groovy.lang.Closure)
     */
    static<T> Promise<List<T>> onError(List<Promise<T>> promises, Closure callable ) {
        getPromiseFactory().onError(promises, callable)
    }
    /**
     * @see PromiseFactory#createPromise(java.util.Map)
     */
    static<K,V> Promise<Map<K,V>> createPromise(Map<K, Object> map) {
        getPromiseFactory().createPromise(map)
    }
    /**
     * @see PromiseFactory#createPromise(groovy.lang.Closure[])
     */
    static<T> Promise<T> createPromise(Closure<T>... c) {
        getPromiseFactory().createPromise(c)
    }

    /**
     * @see PromiseFactory#createPromise(java.util.Map)
     */
    static<K,V> Promise<Map<K,V>> tasks(Map<K, Object> map) {
        getPromiseFactory().createPromise(map)
    }
    /**
     * @see PromiseFactory#createPromise(groovy.lang.Closure[])
     */
    static<T> Promise<T> task(Closure<T> c) {
        getPromiseFactory().createPromise(c)
    }
    /**
     * @see PromiseFactory#createPromise(groovy.lang.Closure[])
     */
    static<T> Promise<T> tasks(Closure<T>... c) {
        getPromiseFactory().createPromise(c)
    }
    /**
     * @see PromiseFactory#createPromise(groovy.lang.Closure[])
     */
    static<T> Promise<List<T>> tasks(List<Closure<T>> closures) {
        getPromiseFactory().createPromise(closures)
    }

    /**
     * @see PromiseFactory#createPromise(groovy.lang.Closure, java.util.List)
     */
    static<T> Promise<T> createPromise(Closure<T> c, List<PromiseDecorator> decorators) {
        getPromiseFactory().createPromise(c, decorators)
    }
    /**
     * @see PromiseFactory#createPromise(java.util.List, java.util.List)
     */
    static<T> Promise<List<T>> createPromise(List<Closure<T>> closures, List<PromiseDecorator> decorators) {
        getPromiseFactory().createPromise(closures, decorators)
    }
    /**
     * @see PromiseFactory#createPromise(grails.async.Promise[])
     */
    static <T> Promise<List<T>> createPromise(Promise<T>...promises) {
        getPromiseFactory().createPromise(promises)
    }

    /**
     * @see PromiseFactory#createBoundPromise(java.lang.Object)
     */
    static<T> Promise<T> createBoundPromise(T value) {
        getPromiseFactory().createBoundPromise(value)
    }
}
