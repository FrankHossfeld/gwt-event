/*
 * Copyright 2013 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.event.shared;

import com.google.j2cl.junit.apt.J2clTestInput;
import junit.framework.TestCase;
import org.junit.Test;

/** Unit test for {@link HandlerRegistrations}. */
@J2clTestInput(HandlerRegistrationsTest.class)
public class HandlerRegistrationsTest extends TestCase {

  private static class MockHandlerRegistration implements HandlerRegistration {

    private int removeHandlerCallCount = 0;

    @Override
    public void removeHandler() {
      removeHandlerCallCount++;
    }
  }

  @Test
  public void testHandlerRegistrationCollectionRemove() {
    MockHandlerRegistration hr1 = new MockHandlerRegistration();
    MockHandlerRegistration hr2 = new MockHandlerRegistration();

    HandlerRegistration hrc = HandlerRegistrations.compose(hr1, hr2);
    hrc.removeHandler();

    // every handler registration needs to be called once
    assertEquals(1, hr1.removeHandlerCallCount);
    assertEquals(1, hr2.removeHandlerCallCount);

    hrc.removeHandler();
    // should not be called again
    assertEquals(1, hr1.removeHandlerCallCount);
    assertEquals(1, hr2.removeHandlerCallCount);
  }
}
