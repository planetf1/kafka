/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.kafka.streams.kstream.internals;

import org.apache.kafka.streams.kstream.Window;

/**
 * A session window covers a closed time interval with its start and end timestamp both being an inclusive boundary.
 *
 * @see TimeWindow
 * @see UnlimitedWindow
 * @see org.apache.kafka.streams.kstream.SessionWindows
 */
public final class SessionWindow extends Window {

    /**
     * Create a new window for the given start time and end time (both inclusive).
     *
     * @param start  the start timestamp of the window
     * @param end    the end timestamp of the window
     */
    public SessionWindow(final long startMs, final long endMs) {
        super(startMs, endMs);
    }

    /**
     * Check if the given window overlaps with this window.
     *
     * @param other  another window
     * @return       {@code true} if {@code other} overlaps with this window&mdash;{@code false} otherwise
     * @throws IllegalArgumentException if the {@code other} window has a different type than {@link this} window
     */
    public boolean overlap(final Window other) throws IllegalArgumentException {
        if (getClass() != other.getClass()) {
            throw new IllegalArgumentException("Cannot compare windows of different type. Other window has type "
                + other.getClass());
        }
        final SessionWindow otherWindow = (SessionWindow) other;
        return !(otherWindow.endMs < startMs || endMs < otherWindow.startMs);
    }

}