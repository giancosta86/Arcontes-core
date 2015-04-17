/*§
  ===========================================================================
  Arcontes - Core
  ===========================================================================
  Copyright (C) 2013-2015 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.arcontes.graphs;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DefaultLinkTest {

    private Vertex firstVertex;
    private Vertex secondVertex;
    private Link link;

    @Before
    public void setUp() {
        firstVertex = new DefaultVertex();
        secondVertex = new DefaultVertex();

        link = new DefaultLink(firstVertex, secondVertex);
    }

    @Test(expected = GraphConsistencyException.class)
    public void constructor_shouldPreventRecursion() {
        link = new DefaultLink(firstVertex, firstVertex);
    }

    @Test
    public void constructor_shouldAssignId() {
        assertThat(link.getId(), notNullValue());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getAnchors_shouldReturnAnUnmodifiableSet() {
        link.getAnchors().add(new DefaultVertex());
    }

}
