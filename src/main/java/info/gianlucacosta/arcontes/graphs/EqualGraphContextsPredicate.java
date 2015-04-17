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

import info.gianlucacosta.helios.collections.general.SameTypePair;
import info.gianlucacosta.helios.predicates.Predicate;

import java.util.Objects;

/**
 * Returns true if two graph contexts are equal
 * <p>
 * Two graph contexts are equal if and only if their ids are equal.
 */
public class EqualGraphContextsPredicate implements Predicate<SameTypePair<GraphContext>> {

    @Override
    public boolean evaluate(SameTypePair<GraphContext> graphContexts) {
        GraphContext leftGraphContext = graphContexts.getLeft();
        GraphContext rightGraphContext = graphContexts.getRight();

        return Objects.equals(leftGraphContext.getId(), rightGraphContext.getId());
    }

}
