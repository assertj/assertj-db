package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the exceptions of Change
 * 
 * @author Régis Pouiller
 * 
 */
public class Change_Exception_Test extends AbstractTest {

  /**
   * This method should fail because the data name must be not null.
   */
  @Test
  public void should_fail_because_dataname_must_be_not_null() {
    try {
      getChange(null, ChangeType.CREATION, getRow(Arrays.asList(""), Arrays.asList(""), Arrays.asList()),
          getRow(Arrays.asList(""), Arrays.asList(""), Arrays.asList()));

      fail("Une Erreur doit être levée");
    } catch (Exception exception) {
      assertThat(exception.getCause().getLocalizedMessage()).isEqualTo("The name of the data must be not null");
    }
  }
}
