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

package info.gianlucacosta.arcontes.graphs.metainfo.agents;

import info.gianlucacosta.arcontes.graphs.Graph;
import info.gianlucacosta.arcontes.graphs.GraphContext;
import info.gianlucacosta.arcontes.graphs.Link;
import info.gianlucacosta.arcontes.graphs.metainfo.DefaultNameInfo;
import info.gianlucacosta.arcontes.graphs.metainfo.GraphContextBasedMetaInfoAgent;
import info.gianlucacosta.arcontes.graphs.metainfo.NameInfo;
import info.gianlucacosta.helios.metainfo.MetaInfoException;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

import java.util.Objects;

/**
 * Associates a unique NameInfo metainfo to any link passed to its act() method
 * <p>
 * The name is split into two parts:
 * <ol>
 * <li>a name root, passed into the agent's constructor</li>
 * <li>an integer, ensuring that the name is unique</li>
 * </ol>
 */
public class ProgressiveLinkNameInfoAgent extends GraphContextBasedMetaInfoAgent<Link> {

    private final String nameRoot;

    public ProgressiveLinkNameInfoAgent(GraphContext graphContext) {
        this(graphContext, "L");

    }

    public ProgressiveLinkNameInfoAgent(GraphContext graphContext, String nameRoot) {
        super(false, graphContext);
        this.nameRoot = nameRoot;
    }

    @Override
    protected boolean doAct(MetaInfoRepository metaInfoRepository, Link link) {
        Graph graph = getGraphContext().getGraph();

        for (int linkNumber = graph.getLinks().size() + 1; ; linkNumber++) {
            String linkName = String.format("%s%d", nameRoot, linkNumber);

            boolean nameAlreadyExisting = false;

            for (Link currentLink : graph.getLinks()) {
                String currentLinkName;

                try {
                    currentLinkName = metaInfoRepository.getMetaInfo(currentLink, NameInfo.class).getName();
                } catch (MetaInfoException ex) {
                    continue;
                }

                if (Objects.equals(currentLinkName, linkName)) {
                    nameAlreadyExisting = true;
                    break;
                }
            }

            if (!nameAlreadyExisting) {
                metaInfoRepository.putMetaInfo(link, new DefaultNameInfo(linkName));
                break;
            }
        }

        return true;
    }

}
