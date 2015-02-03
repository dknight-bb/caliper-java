package org.imsglobal.caliper.entities.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A Video object embedded in a web page.
 */
@JsonPropertyOrder({
    "@id",
    "@type",
    "name",
    "objectType",
    "alignedLearningObjective",
    "keywords",
    "isPartOf",
    "dateCreated",
    "dateModified",
    "datePublished",
    "duration" })
public class VideoObject extends MediaObject implements org.imsglobal.caliper.entities.schemadotorg.VideoObject {

    @JsonProperty("@type")
    private final String type;

    /**
     * @param builder apply builder object properties to the VideoObject object.
     */
    protected VideoObject(Builder<?> builder) {
        super(builder);
        this.type = builder.type;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends MediaObject.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.  Required if builder().type() is not set by user.
         */
        public Builder() {
            type(MediaObject.Type.VIDEO_OBJECT.uri());
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of VideoObject.
         */
        public VideoObject build() {
            return new VideoObject(this);
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