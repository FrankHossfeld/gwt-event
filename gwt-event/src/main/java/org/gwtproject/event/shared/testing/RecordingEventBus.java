/*
 * Copyright 2011 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.event.shared.testing;

import java.util.HashSet;
import java.util.Set;
import org.gwtproject.event.shared.Event;
import org.gwtproject.event.shared.Event.Type;
import org.gwtproject.event.shared.EventBus;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.event.shared.SimpleEventBus;

/** Wraps an {@link EventBus} implementation to keep track of fired events. */
public class RecordingEventBus extends EventBus {

  private final Set<Event<?>> firedEvents = new HashSet<>();
  private final EventBus wrapped;

  public RecordingEventBus() {
    this(new SimpleEventBus());
  }

  public RecordingEventBus(EventBus wrapped) {
    this.wrapped = wrapped;
  }

  @Override
  public <H> HandlerRegistration addHandler(Type<H> type, H handler) {
    return wrapped.addHandler(type, handler);
  }

  @Override
  public <H> HandlerRegistration addHandlerToSource(Type<H> type, Object source, H handler) {
    return wrapped.addHandlerToSource(type, source, handler);
  }

  @Override
  public void fireEvent(Event<?> event) {
    wrapped.fireEvent(event);
    firedEvents.add(event);
  }

  @Override
  public void fireEventFromSource(Event<?> event, Object source) {
    firedEvents.add(event);
    wrapped.fireEventFromSource(event, source);
  }

  /** Clears the remembered list of fired events. */
  public void clearFiredEvents() {
    firedEvents.clear();
  }

  /** Returns {@code true} if the specified event was fired. */
  public boolean wasEventFired(Event<?> event) {
    return firedEvents.contains(event);
  }
}
