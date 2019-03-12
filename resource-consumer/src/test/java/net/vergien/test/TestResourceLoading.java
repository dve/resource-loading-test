package net.vergien.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestResourceLoading
{

   @BeforeEach
   void setUp() throws Exception
   {
   }

   @Test
   @DisplayName("Read from root of dependency")
   void test001()
   {
      InputStream resourceAsStream = TestResourceLoading.class
            .getResourceAsStream("/test.txt");

      assertEquals("This is a resource", isToString(resourceAsStream));
   }

   @Test
   @DisplayName("Read from path of dependency")
   void test002()
   {
      InputStream resourceAsStream = TestResourceLoading.class
            .getResourceAsStream("/in-a-path/test.txt");

      assertEquals("This is a resource in a path",
            isToString(resourceAsStream));
   }

   @Test
   @DisplayName("Read from root of test-jar dependency")
   void test003()
   {
      InputStream resourceAsStream = TestResourceLoading.class
            .getResourceAsStream("/test2.txt");

      assertEquals("This is a resource inside a test jar",
            isToString(resourceAsStream));
   }

   @Test
   @DisplayName("Read from path of test-jar dependency")
   void test004()
   {
      InputStream resourceAsStream = TestResourceLoading.class
            .getResourceAsStream("/in-a-path/test2.txt");

      assertEquals("This is a resource in a path inside a test jar",
            isToString(resourceAsStream));
   }
   public static String isToString(InputStream is)
   {
      char[] buffer = new char[200];
      StringBuilder out = new StringBuilder();
      try (Reader in = new InputStreamReader(is, "UTF-8"))
      {
         for (;;)
         {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
            {
               break;
            }
            out.append(buffer, 0, rsz);
         }
      } catch (Exception ex)
      {
         // just testing
      }

      return out.toString();
   }
}
