package org.aksw.mag.model;

public class MagEntity implements Comparable<MagEntity> {

	private String namedEntity;
	private int start;
	private int end;
	private int offset = -1;
	private String disambiguatedURL;

	public MagEntity() {

	}

	public MagEntity(MagEntity n) {
		this.namedEntity = n.getNamedEntity();
		this.start = n.getStart();
		this.end = n.getEnd();
		this.offset = n.getOffset();
		this.disambiguatedURL = n.getDisambiguatedURL();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MagEntity [namedEntity=");
		builder.append(namedEntity);
		builder.append(", start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append(", offset=");
		builder.append(offset);
		builder.append(", disambiguatedURL=");
		builder.append(disambiguatedURL);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disambiguatedURL == null) ? 0 : disambiguatedURL.hashCode());
		result = prime * result + end;
		result = prime * result + ((namedEntity == null) ? 0 : namedEntity.hashCode());
		result = prime * result + offset;
		result = prime * result + start;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagEntity other = (MagEntity) obj;
		if (disambiguatedURL == null) {
			if (other.disambiguatedURL != null)
				return false;
		} else if (!disambiguatedURL.equals(other.disambiguatedURL))
			return false;
		if (end != other.end)
			return false;
		if (namedEntity == null) {
			if (other.namedEntity != null)
				return false;
		} else if (!namedEntity.equals(other.namedEntity))
			return false;
		if (offset != other.offset)
			return false;
		if (start != other.start)
			return false;
		return true;
	}

	public String getNamedEntity() {
		return namedEntity;
	}

	public void setNamedEntity(String namedEntity) {
		this.namedEntity = namedEntity;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getDisambiguatedURL() {
		return disambiguatedURL;
	}

	public void setDisambiguatedURL(String disambiguatedURL) {
		this.disambiguatedURL = disambiguatedURL;
	}

	@Override
	public int compareTo(MagEntity o) {
		if (this.start < o.start) {
			return -1;
		} else if (this.start > o.start) {
			return 1;
		}
		return 0;
	}

}