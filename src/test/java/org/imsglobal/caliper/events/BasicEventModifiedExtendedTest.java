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

package org.imsglobal.caliper.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.context.JsonldContext;
import org.imsglobal.caliper.context.JsonldStringContext;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.resource.Document;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.yammer.dropwizard.testing.JsonHelpers.jsonFixture;

@Category(org.imsglobal.caliper.UnitTest.class)
public class BasicEventModifiedExtendedTest {
    private JsonldContext context;
    private String id;
    private Person actor;
    private Document object;
    private ObjectNode extensionsNode;
    private Event event;

    private static final String BASE_IRI = "https://example.edu";
    private static final String SECTION_IRI = BASE_IRI.concat("/terms/201601/courses/7/sections/1");

    @Before
    public void setUp() throws Exception {
        context = JsonldStringContext.getDefault();

        id = "urn:uuid:5973dcd9-3126-4dcc-8fd8-8153a155361c";

        actor = Person.builder().id(BASE_IRI.concat("/users/554433")).build();

        object = Document.builder()
            .id(SECTION_IRI.concat("/resources/123?version=3"))
            .name("Course Syllabus")
            .dateCreated(new DateTime(2016, 11, 12, 7, 15, 0, 0, DateTimeZone.UTC))
            .dateModified(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .version("3")
            .build();

        extensionsNode = createExtensionsNode();

        // Build event
        event = buildEvent(Action.MODIFIED);
    }

    @Test
    public void caliperEventSerializesToJSON() throws Exception {
        ObjectMapper mapper = TestUtils.createCaliperObjectMapper();
        String json = mapper.writeValueAsString(event);

        String fixture = jsonFixture("fixtures/caliperEventBasicModifiedExtended.json");
        JSONAssert.assertEquals(fixture, json, JSONCompareMode.NON_EXTENSIBLE);
    }

    @After
    public void teardown() {
        event = null;
    }

    /**
     * Build basic event.
     * @param action
     * @return event
     */
    private Event buildEvent(Action action) {
        return Event.builder()
            .context(context)
            .id(id)
            .actor(actor)
            .action(action)
            .object(object)
            .eventTime(new DateTime(2016, 11, 15, 10, 15, 0, 0, DateTimeZone.UTC))
            .extensions(extensionsNode)
            .build();
    }

    /**
     * Create faux extensions
     * @return
     */
    private ObjectNode createExtensionsNode() {
        Document doc2 = Document.builder()
            .id(SECTION_IRI.concat("/resources/123?version=2"))
            .dateCreated(new DateTime(2016, 11, 12, 7, 15, 0, 0, DateTimeZone.UTC))
            .dateModified(new DateTime(2016, 11, 13, 11, 0, 0, 0, DateTimeZone.UTC))
            .version("2")
            .build();

        Document doc1 = Document.builder()
            .id(SECTION_IRI.concat("/resources/123?version=1"))
            .dateCreated(new DateTime(2016, 11, 12, 7, 15, 0, 0, DateTimeZone.UTC))
            .version("1")
            .build();

        ObjectMapper mapper = TestUtils.createCaliperObjectMapper();

        ArrayNode array = mapper.createArrayNode();
        array.addPOJO(doc2);
        array.addPOJO(doc1);

        ObjectNode extensions = mapper.createObjectNode();
        extensions.putArray("archive").addAll(array);

        return extensions;
    }
}