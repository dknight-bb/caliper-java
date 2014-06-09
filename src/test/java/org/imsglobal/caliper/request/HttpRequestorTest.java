package org.imsglobal.caliper.request;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.imsglobal.caliper.Options;
import org.imsglobal.caliper.TestUtils;
import org.imsglobal.caliper.events.CaliperEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.CharStreams;

/**
 * Test HttpRequestor
 * 
 * @author pnayak
 * @author yoganandp
 */
public class HttpRequestorTest {

	CaliperEvent caliperEvent;
	Options options;
	HttpRequestor httpRequestor;

	@Before
	public void setup() {

		caliperEvent = new CaliperEvent();
		caliperEvent.setAction("FAKE-ACTION");
		caliperEvent.setStartedAt(1402337902000l);

		httpRequestor = new HttpRequestor(TestUtils.getTestingOptions());
	}

	@Test
	public void testGeneratePayload() {

		StringEntity payload = null;

		try {
			payload = httpRequestor.generatePayload(caliperEvent);

			String expectedContentType = "Content-Type: application/json";
			assertEquals(expectedContentType, payload.getContentType()
					.toString());

			String actualContent = CharStreams.toString(new InputStreamReader(
					payload.getContent(), "UTF-8"));

			String expectedContent = "{\"action\":\"FAKE-ACTION\",\"startedAt\":1402337902000}";
			assertEquals(expectedContent, actualContent);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void teardown() {
		caliperEvent = null;
		options = null;
	}

}
