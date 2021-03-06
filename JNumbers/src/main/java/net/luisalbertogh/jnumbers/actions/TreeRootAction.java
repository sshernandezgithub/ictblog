/**
 * Jwire is an advanced GUI for the crawler WIRE. It launches, manages, and configures WIRE for its use.
 * It also includes an advanced toolset of data visualization tools based on the most common and well-known
 * visualization techniques.
 * Copyright (C) 2010 Luis Alberto Garcia Hernandez
 */
/**
 * This file is part of Jwire.

 Jwire is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Jwire is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Jwire. If not, see <http://www.gnu.org/licenses/>.
 */
package net.luisalbertogh.jnumbers.actions;

import java.util.Iterator;

import prefuse.Visualization;
import prefuse.action.GroupAction;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.tuple.TupleSet;

/**
 * Switch the root of the tree by requesting a new spanning tree at the desired root
 */
public class TreeRootAction extends GroupAction {
    /**
     * TODO Description
     * 
     * @param graphGroup
     */
    public TreeRootAction(String graphGroup) {
        super(graphGroup);
    }

    /**
     * @see prefuse.action.GroupAction#run(double)
     */
    @Override
    public void run(double frac) {
        TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
        if (focus == null || focus.getTupleCount() == 0) {
            return;
        }

        Graph g = (Graph) m_vis.getGroup(m_group);
        Node f = null;
        Iterator tuples = focus.tuples();
        while (tuples.hasNext() && !g.containsTuple(f = (Node) tuples.next())) {
            f = null;
        }
        if (f == null) {
            return;
        }
        g.getSpanningTree(f);
    }
}
