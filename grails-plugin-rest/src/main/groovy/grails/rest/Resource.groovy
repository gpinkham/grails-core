/*
 * Copyright 2013 the original author or authors.
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

package grails.rest

import org.codehaus.groovy.grails.web.mime.MimeType
import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 *
 * Meta Annotation to applied to a domain class if it is a REST resource
 *
 * @author Graeme Rocher
 * @since 2.3
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.TYPE])
@GroovyASTTransformationClass("org.grails.plugins.web.rest.transform.ResourceTransform")
public @interface Resource {

    /**
     * @return Whether this is a read-only resource (one that doesn't allow DELETE, POST or PUT requests)
     */
    boolean readOnly() default false

    /**
     * @return The allowed response formats
     */
    String[] responseFormats() default ["xml", 'json']
}