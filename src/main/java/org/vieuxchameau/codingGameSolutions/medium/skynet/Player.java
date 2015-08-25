package org.vieuxchameau.codingGameSolutions.medium.skynet;

import java.util.*;
import java.util.Map.Entry;

class Player {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfNodes = in.nextInt(); // the total number of nodes in the level, including the gateways
		final int numberOfLinks = in.nextInt(); // the number of links
		final int numberOfExits = in.nextInt(); // the number of exit gateways

		final Map<Integer, Node> nodes = new HashMap<>(numberOfNodes);
		for (int i = 0; i < numberOfLinks; i++) {
			final Node firstNode = getOrAdd(nodes, in.nextInt()); // N1 and N2 defines a link between these nodes
			final Node secondNode = getOrAdd(nodes, in.nextInt());

			firstNode.addLink(secondNode);
		}

		final Set<Node> exitNodes = new HashSet<>(numberOfExits);
		for (int i = 0; i < numberOfExits; i++) {
			final Node exitNode = getOrAdd(nodes, in.nextInt()); // the index of a gateway node
			exitNodes.add(exitNode);
		}

		while (true) {
			final Node skynetNode = nodes.get(in.nextInt()); // The index of the node on which the Skynet agent is positioned this turn

			Optional<Node> linkWithExitNode = exitNodes.stream().filter(node -> node.linkedNodes.contains(skynetNode)).findFirst();
			if (linkWithExitNode.isPresent()) {
				removeLink(linkWithExitNode.get(), skynetNode);
			} else {
				final Node mostLinkedNode = findMostLinkedNode(exitNodes);
				final Node linkedNode = mostLinkedNode.linkedNodes.iterator().next();
				removeLink(mostLinkedNode, linkedNode);
			}
			cleanIsolatedNodes(nodes, exitNodes);
		}
	}

	static Node findMostLinkedNode(final Set<Node> exitNodes) {
		int max = Integer.MIN_VALUE;
		Node mostLinked = null;
		for (final Node exitNode : exitNodes) {
			if (exitNode.getNumberOfLinks() > max) {
				mostLinked = exitNode;
				max = exitNode.getNumberOfLinks();
			}
		}
		return mostLinked;
	}

	private static void cleanIsolatedNodes(final Map<Integer, Node> nodes, final Set<Node> exitNodes) {
		exitNodes.removeIf(Node::hasNoLink);

		final Iterator<Entry<Integer, Node>> nodeIterator = nodes.entrySet().iterator();
		while (nodeIterator.hasNext()) {
			final Entry<Integer, Node> entry = nodeIterator.next();
			if (entry.getValue().hasNoLink()) {
				nodeIterator.remove();
			}
		}
	}

	private static void removeLink(final Node node, final Node linkedNode) {
		node.removeLink(linkedNode);
		System.out.println(node.id + " " + linkedNode.id);
	}

	static Node getOrAdd(final Map<Integer, Node> nodes, final int nodeId) {
		Node node = nodes.get(nodeId);
		if (node == null) {
			node = new Node(nodeId);
			nodes.put(nodeId, node);
		}
		return node;
	}

	static class Node {
		private final int id;
		private final Set<Node> linkedNodes = new HashSet<>();

		Node(int id) {
			this.id = id;
		}

		int getNumberOfLinks() {
			return linkedNodes.size();
		}

		boolean hasNoLink() {
			return linkedNodes.isEmpty();
		}

		void addLink(final Node linkedNode) {
			linkedNodes.add(linkedNode);
			linkedNode.linkedNodes.add(this);
		}

		void removeLink(final Node linkedNode) {
			linkedNodes.remove(linkedNode);
			linkedNode.linkedNodes.remove(this);
		}
	}
}
