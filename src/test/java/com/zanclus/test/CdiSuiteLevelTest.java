package com.zanclus.test;

import com.zanclus.test.data.ToDoDAOTest;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestSuiteRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(CdiTestSuiteRunner.class)
@Suite.SuiteClasses({ExampleRestTest.class, ToDoDAOTest.class})
public class CdiSuiteLevelTest {
}