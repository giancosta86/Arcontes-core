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

package info.gianlucacosta.arcontes.graphs.conversions.linkconverters;

import info.gianlucacosta.arcontes.graphs.Link;
import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.arcontes.graphs.analysis.OrientedGraphAnalyzer;
import info.gianlucacosta.helios.conversions.CollectionToCollectionConverter;

/**
 * Converts a collection of (oriented) links to a collection of their tails
 */
public class LinksToTailsConverter extends CollectionToCollectionConverter<Link, Vertex> {

    private final OrientedGraphAnalyzer graphAnalyzer;

    public LinksToTailsConverter(OrientedGraphAnalyzer graphAnalyzer) {
        this.graphAnalyzer = graphAnalyzer;
    }

    @Override
    protected Vertex convertItem(Link link) {
        return graphAnalyzer.getTail(link);
    }

}
