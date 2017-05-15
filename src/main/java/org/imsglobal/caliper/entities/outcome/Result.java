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

package org.imsglobal.caliper.entities.outcome;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.entities.AbstractEntity;
import org.imsglobal.caliper.entities.EntityType;
import org.imsglobal.caliper.entities.CaliperGeneratable;
import org.imsglobal.caliper.entities.agent.CaliperAgent;
import org.imsglobal.caliper.entities.resource.Attempt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Result extends AbstractEntity implements CaliperGeneratable {

    @JsonProperty("attempt")
    private Attempt attempt;

    @JsonProperty("normalScore")
    private double normalScore;

    @JsonProperty("penaltyScore")
    private double penaltyScore;

    @JsonProperty("extraCreditScore")
    private double extraCreditScore;

    @JsonProperty("totalScore")
    private double totalScore;

    @JsonProperty("curvedTotalScore")
    private double curvedTotalScore;

    @JsonProperty("curveFactor")
    private double curveFactor;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("scoredBy")
    private CaliperAgent scoredBy;

    /**
     * @param builder apply builder object properties to the object.
     */
    protected Result(Builder<?> builder) {
        super(builder);

        this.attempt = builder.attempt;
        this.normalScore = builder.normalScore;
        this.penaltyScore = builder.penaltyScore;
        this.extraCreditScore = builder.extraCreditScore;
        this.totalScore = builder.totalScore;
        this.curvedTotalScore = builder.curvedTotalScore;
        this.curveFactor = builder.curveFactor;
        this.comment = builder.comment;
        this.scoredBy = builder.scoredBy;
    }

    /**
     * @return attempt associated with the response;
     */
    @Nonnull
    public Attempt getAttempt() {
        return attempt;
    }

    /**
     * @return the normalScore
     */
    @Nullable
    // @JsonSerialize(using=DoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getNormalScore() {
        return normalScore;
    }

    /**
     * @return the penaltyScore
     */
    @Nullable
    // @JsonSerialize(using=DoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getPenaltyScore() {
        return penaltyScore;
    }

    /**
     * @return the extraCreditScore
     */
    @Nullable
    // @JsonSerialize(using=DoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getExtraCreditScore() {
        return extraCreditScore;
    }

    /**
     * @return the totalScore
     */
    @Nullable
    // @JsonSerialize(using=DoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * @return the curvedTotalScore
     */
    @Nullable
    // @JsonSerialize(using=DoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getCurvedTotalScore() {
        return curvedTotalScore;
    }

    /**
     * @return the curveFactor
     */
    @Nullable
    // @JsonSerialize(using=DoubleSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getCurveFactor() {
        return curveFactor;
    }

    /**
     * @return the comment
     */
    @Nullable
    public String getComment() {
        return comment;
    }

    /**
     * @return the agent who scored the result
     */
    @Nullable
    public CaliperAgent getScoredBy() {
        return scoredBy;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder.
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEntity.Builder<T> {
        private Attempt attempt;
        private double normalScore;
        private double penaltyScore;
        private double extraCreditScore;
        private double totalScore;
        private double curvedTotalScore;
        private double curveFactor;
        private String comment;
        private CaliperAgent scoredBy;

        /**
         * Constructor
         */
        public Builder() {
            super.type(EntityType.RESULT);
        }

        /**
         * @param attempt
         * @return builder.
         */
        public T attempt(Attempt attempt) {
            this.attempt = attempt;
            return self();
        }

        /**
         * @param normalScore
         * @return normal score.
         */
        public T normalScore(double normalScore) {
            this.normalScore = normalScore;
            return self();
        }

        /**
         * @param penaltyScore
         * @return penalty score.
         */
        public T penaltyScore(double penaltyScore) {
            this.penaltyScore = penaltyScore;
            return self();
        }

        /**
         * @param extraCreditScore
         * @return extra credit score.
         */
        public T extraCreditScore(double extraCreditScore) {
            this.extraCreditScore = extraCreditScore;
            return self();
        }

        /**
         * @param totalScore
         * @return total score.
         */
        public T totalScore(double totalScore) {
            this.totalScore = totalScore;
            return self();
        }

        /**
         * @param curvedTotalScore
         * @return curved total score.
         */
        public T curvedTotalScore(double curvedTotalScore) {
            this.curvedTotalScore = curvedTotalScore;
            return self();
        }

        /**
         * @param curveFactor
         * @return curve factor.
         */
        public T curveFactor(double curveFactor) {
            this.curveFactor = curveFactor;
            return self();
        }

        /**
         * @param comment
         * @return comment.
         */
        public T comment(String comment) {
            this.comment = comment;
            return self();
        }

        /**
         * @param scoredBy
         * @return agent who scored the result.
         */
        public T scoredBy(CaliperAgent scoredBy) {
            this.scoredBy = scoredBy;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of the Result.
         */
        public Result build() {
            return new Result(this);
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