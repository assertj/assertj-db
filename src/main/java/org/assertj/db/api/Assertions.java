package org.assertj.db.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;

/**
 * Entry point of all the assertions.
 * 
 * <p>
 * Example with a {@link Source} and a {@link Table} with test on the content on the first row of the {@code movie}
 * table that the {@code title} column contains "Alien" like text and the next column contains 1979 like number :
 * </p>
 * 
 * <pre>
 * Source source = new Source(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;);
 * Table table = new Table(source, &quot;movie&quot;);
 * assertThat(table)
 *     .row()
 *        .value("title")
 *            .isEqualTo("Alien")
 *        .returnToRow()
 *        .value()
 *            .isEqualTo(1979);
 * </pre>
 * 
 * @author Régis Pouiller
 * 
 */
public final class Assertions {

  /**
   * Private constructor of the entry point.
   */
  private Assertions() {
    // empty
  }

  /**
   * Creates a new instance of <code>{@link TableAssert}</code>.
   * 
   * @param table The table to assert on.
   * @return The created assertion object.
   */
  public static TableAssert assertThat(Table table) {
    return new TableAssert(table);
  }

  /**
   * Creates a new instance of <code>{@link RequestAssert}</code>.
   * 
   * @param request The request to assert on.
   * @return The created assertion object.
   */
  public static RequestAssert assertThat(Request request) {
    return new RequestAssert(request);
  }

  /**
   * Reads the bytes from an {@link InputStream}.
   * 
   * @param inputStream The {@link InputStream}
   * @return A array of {@code byte}
   */
  private static byte[] read(InputStream inputStream) {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

      int byteInt = inputStream.read();
      while (byteInt != -1) {
        byteArrayOutputStream.write(byteInt);
        byteInt = inputStream.read();
      }

      byte[] values = byteArrayOutputStream.toByteArray();
      return values;
    } catch (IOException e) {
      throw new AssertJDBException(e);
    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
        // Mute exception
      }
    }
  }

  /**
   * Reads the bytes from a file and returns them.
   * 
   * @param file The {@link File}
   * @return The bytes of the file.
   * @throws NullPointerException
   * @throws AssertJDBException
   */
  public static byte[] bytesContentOf(File file) {
    if (file == null) {
      throw new NullPointerException("File must be not null");
    }

    try {
      InputStream inputStream = new FileInputStream(file);
      return read(inputStream);
    } catch (FileNotFoundException e) {
      throw new AssertJDBException(e);
    }
  }

  /**
   * Reads the bytes from a file in the classpath and returns them.
   * 
   * @param resource The name of the file in the classpath.
   * @return The bytes of the file.
   * @throws NullPointerException
   * @throws AssertJDBException
   */
  public static byte[] bytesContentFromClassPathOf(String resource) {
    if (resource == null) {
      throw new NullPointerException("Resource must be not null");
    }

    ClassLoader classLoader = Assertions.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(resource);
    if (inputStream == null) {
      throw new AssertJDBException("Resource %s not found in the classpath", resource);
    }

    return read(inputStream);
  }
}
