/**
 * Copyright (c) 2011-2012 Zauber S.A. <http://www.zaubersoftware.com/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zaubersoftware.gnip4j.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zaubersoftware.gnip4j.api.model.Activity;

/**
 * TODO: Description of the class, Comments in english by default  
 * 
 * 
 * @author Juan F. Codagnone
 * @param <T>
 * @since Oct 3, 2013
 */
public abstract class PowertrackStreamBuilder<T> {
    protected String type;
    private int streamDefaultWorkers = Runtime.getRuntime().availableProcessors();
    protected ExecutorService executorService = Executors.newFixedThreadPool(streamDefaultWorkers);
    protected String account;
    protected StreamNotification<T> observer;
    
    /**  if your EDC URL starts with http://acme.gnip.com  then this value is acme*/
    public final PowertrackStreamBuilder withAccount(final String account) {
        this.account = account;
        return this;
    }
    
    public final PowertrackStreamBuilder withObserver(final StreamNotification<T> observer) {
        this.observer = observer;
        return this;
    }
    
    public final PowertrackStreamBuilder withExecutorService(final ExecutorService executor) {
        this.executorService = executor;
        return this;
    }

    /** twitter | automattic | ... */
    public final PowertrackStreamBuilder withType(final String type) {
        this.type = type;
        return this;
    }
    
    
    public GnipStream build() {
        if(account == null) {
            throw new IllegalArgumentException("you must set the account");
        }
        
        if(type == null) {
            throw new IllegalArgumentException("you must set the dataCollector");
        }
        
        if(observer == null) {
            throw new IllegalArgumentException("you must set the observer");
        }
        
        return buildStream();
    }


    protected abstract GnipStream buildStream();
}
