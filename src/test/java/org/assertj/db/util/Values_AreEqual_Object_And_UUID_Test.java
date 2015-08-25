package org.assertj.db.util;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Otoniel Isidoro
 */
public class Values_AreEqual_Object_And_UUID_Test {

    /**
     * This method tests the {@code areEqual} method for {@code UUID}s.
     */
    @Test
    public void test_are_equal_for_UUID() {
        assertThat(Values.areEqual(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                   UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))).isTrue();
        assertThat(Values.areEqual(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                   UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"))).isFalse();
    }

}
