package org.imsglobal.caliper.entities.reading;

import org.imsglobal.caliper.entities.CaliperDigitalResource;
import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;

/**
 * Representation of an EPUB 3 Volume
 * 
 * A major sub-division of a chapter
 * http://www.idpf.org/epub/vocab/structure/#subchapter
 */
public class EPubSubChapter extends CaliperDigitalResource implements CreativeWork {

    private final String type;

    /**
     * @param builder apply builder object properties to the EPubSubChapter object.
     */
    protected EPubSubChapter(Builder<?> builder) {
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
    public static abstract class Builder<T extends Builder<T>> extends CaliperDigitalResource.Builder<T>  {
        private String type;

        /**
         * Initialize type with default value.
         */
        public Builder() {
            type(CaliperDigitalResource.Type.EPUB_SUB_CHAPTER.uri());
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
         * @return a new instance of EPubSubChapter.
         */
        public EPubSubChapter build() {
            return new EPubSubChapter(this);
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