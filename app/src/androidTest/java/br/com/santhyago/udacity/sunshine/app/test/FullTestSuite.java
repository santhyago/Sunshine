package br.com.santhyago.udacity.sunshine.app.test;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * Created by san on 11/23/14.
 */
public class FullTestSuite {
	public static Test suite() {
		return new TestSuiteBuilder(FullTestSuite.class)
				.includeAllPackagesUnderHere().build();
	}

	public FullTestSuite() {
		super();
	}
}
