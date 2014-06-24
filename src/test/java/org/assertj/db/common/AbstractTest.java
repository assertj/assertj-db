package org.assertj.db.common;

import javax.sql.DataSource;

import org.assertj.db.configuration.TestsConfiguration;
import org.assertj.db.type.Source;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Parent for all the tests. It contains the variables like a {@code DataSource} and a {@code Source}.
 * 
 * @author Régis Pouiller
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestsConfiguration.class })
@Transactional
public abstract class AbstractTest {

  @Autowired(required = true)
  protected DataSource dataSource;

  protected Source source = new Source("jdbc:h2:mem:test", "sa", "");
}
