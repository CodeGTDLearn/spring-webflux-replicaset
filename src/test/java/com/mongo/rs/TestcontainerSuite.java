package com.mongo.rs;


import com.mongo.rs.modules.TestcontainerCacheTest;
import com.mongo.rs.modules.TestcontainerTest;
import com.mongo.rs.modules.TestcontainerTransactionsTest;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Testcontainer-Suite")
@SelectClasses({
     TestcontainerTest.class,
     TestcontainerTransactionsTest.class,
     TestcontainerCacheTest.class
})
@IncludeTags({"testcontainer"})
public class TestcontainerSuite {
}