package edu.iit.network.dijkstra.model;

public class Node implements Comparable<Node> {

	private String id;

	public Node(String id)
	{
		super();
		this.id = id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (obj == this)
		{
			return true;
		}
		if (obj.getClass() != getClass())
		{
			return false;
		}
		Node rhs = (Node) obj;
		if (id == null)
		{
			return false;
		}
		return id.equals(rhs.getId());
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return id;
	}

	@Override
	public int compareTo(Node o)
	{
		return id.compareToIgnoreCase(o.getId());
	}

	/* GETTERS AND SETTERS */
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
