/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.entities.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.EntityType;

import javax.annotation.Nullable;

/**
 * A CourseOffering is the occurrence of a course in a specific term, semester, etc.  A Caliper
 * CourseOffering provides a subset of the CourseOffering properties specified in the IMS LTI 2.0
 * specification, which in turn, draws inspiration from the IMS LIS 1.0 specification.
 */
public class CourseOffering extends AbstractOrganization {

    @JsonProperty("courseNumber")
    private final String courseNumber;

    @JsonProperty("academicSession")
    private final String academicSession;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected CourseOffering(Builder<?> builder) {
        super(builder);
        this.courseNumber = builder.courseNumber;
        this.academicSession = builder.academicSession;
    }

    /**
     * The course number, such as "Biology 101". In general, this number is not simply a numeric value.
     * @return the course number.
     */
    @Nullable
    public String getCourseNumber() {
        return courseNumber;
    }

    /**
     * @return academic session
     */
    @Nullable
    public String getAcademicSession() {
        return academicSession;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractOrganization.Builder<T> {
        private String courseNumber;
        private String academicSession;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.COURSE_OFFERING);
        }

        /**
         * @param courseNumber
         * @return builder.
         */
        public T courseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
            return self();
        }

        /**
         * @param academicSession
         * @return builder.
         */
        public T academicSession(String academicSession) {
            this.academicSession = academicSession;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the CourseOffering.
         */
        public CourseOffering build() {
            return new CourseOffering(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}