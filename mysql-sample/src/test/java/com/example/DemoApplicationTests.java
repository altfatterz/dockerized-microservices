package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private JdbcTemplate template;

	@Test
	public void testDefaultSettings() throws Exception {
		assertEquals(new Integer(2), this.template.queryForObject("SELECT COUNT(*) FROM customer", Integer.class));
	}

}
