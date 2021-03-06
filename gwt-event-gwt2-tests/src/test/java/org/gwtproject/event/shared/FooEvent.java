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
package org.gwtproject.event.shared;

/** For {@link EventBus} tests. */
public class FooEvent extends Event<FooEvent.Handler> {

  public static final Type<Handler> TYPE = new Type<>();

  public static HandlerRegistration register(EventBus bus, Handler handler) {
    return bus.addHandler(TYPE, handler);
  }

  public static HandlerRegistration register(EventBus bus, Object source, Handler handler) {
    return bus.addHandlerToSource(TYPE, source, handler);
  }

  @Override
  public org.gwtproject.event.shared.Event.Type<Handler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(Handler handler) {
    handler.onFoo(this);
  }

  /** The handler for the event. */
  public interface Handler {

    void onFoo(FooEvent e);
  }
}
